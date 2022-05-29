package com.me.courses_selection.controller;


import com.me.courses_selection.config.AccessLimit.AccessLimit;
import com.me.courses_selection.pojo.SUser;
import com.me.courses_selection.pojo.TUser;
import com.me.courses_selection.rabbitmq.MQSender;
import com.me.courses_selection.service.ICoursesService;
import com.me.courses_selection.utils.JsonUtil;
import com.me.courses_selection.utils.ThymeleafViewUtil;
import com.me.courses_selection.vo.MQMessage.OrdersMessage;
import com.me.courses_selection.vo.pojo.FullList;
import com.me.courses_selection.vo.pojo.CoursesVo;
import com.me.courses_selection.vo.Return.RespBean;
import com.me.courses_selection.vo.Return.RespBeanEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;


/**
 * 抢课页面重定向
 * 可至：抢课页面
 * June
 */
@Slf4j
@Controller
@RequestMapping("/Courses")
public class CoursesController implements InitializingBean {

    @Autowired
    private ICoursesService coursesService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MQSender mqSender;

    private Map<Long, FullList> Choosaile = new HashMap<>();

    @Autowired
    private ThymeleafViewUtil thymeleafViewUtil;

    /**
     * 重定向至抢课课程列表
     * June
     */
    @RequestMapping(value = "/toCoursesList", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toCoursesList(Model model, SUser sUser, HttpServletRequest request, HttpServletResponse response) {
        String name="CoursesList";
        model.addAttribute("SUser", sUser);
        int year=Calendar.getInstance().get(Calendar.YEAR);
        model.addAttribute("Year",year);
        String showPage= thymeleafViewUtil.doThymeleafView(name,model,request,response,0);
        return showPage;
    }

    @AccessLimit(second=5,maxCount=50,needLogin=true)
    @RequestMapping(value = "/getCoursesList", method = RequestMethod.POST)
    @ResponseBody
    public List<CoursesVo> getCoursesList(SUser sUser){

        List<CoursesVo> coursesVos =new ArrayList<>();
        for(Long key: Choosaile.keySet()){
            CoursesVo coursesVo =new CoursesVo();
            coursesVo =(CoursesVo)(redisTemplate.opsForValue().get("Courses:"+key));
            Object o = redisTemplate.opsForValue().get("CoursesOrder:"+sUser.getSid()+":"+key);
            if(o!=null){
                coursesVo.setFlag(true);
            }
            coursesVo.setNowStudents(coursesVo.getMaxStudents()-(Integer)(redisTemplate.opsForValue().get("CoursesLast:"+key)));
            coursesVos.add(coursesVo);
        }
        return coursesVos;
    }

    @RequestMapping(value = "/getCoursesListByOneTUser", method = RequestMethod.POST)
    @ResponseBody
    public List<CoursesVo> getCoursesListByOneTUser(TUser tUser){

        List<CoursesVo> coursesVos=coursesService.getCoursesVosByTid(tUser.getTid());
        return coursesVos;
    }
    /**
     * 抢课按钮
     * June
     */
    @AccessLimit(second=5,maxCount=50,needLogin=true)
    @RequestMapping(value = "/doChoose/{cid}", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doChoose(SUser sUser, @PathVariable Long cid) {
        if (sUser == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        ValueOperations valueOperations = redisTemplate.opsForValue();
        //内存标记，减少Redis的访同
        if (Choosaile.get(cid)==null) {
            return RespBean.error(RespBeanEnum.UNABLE_CHOOSE);
        }
        //判断是否重复订单
        String CoursesOrderJson = (String) valueOperations.get("CoursesOrder:" + sUser.getSid() + ":" + cid);
        if (!StringUtils.isEmpty(CoursesOrderJson)) {
            return RespBean.error(RespBeanEnum.BE_CHOSEN);
        }
        //预减库存
        Long stock = valueOperations.decrement("CoursesLast:" + cid);
        if (stock < 0) {
            Choosaile.get(cid).setNow(Choosaile.get(cid).getNow()+1);
            Choosaile.get(cid).setFlags(false);
            valueOperations.increment("CoursesLast:" + cid);
            return RespBean.error(RespBeanEnum.BE_CHOSEN);
        }
        valueOperations.set("CoursesOrder:"+sUser.getSid()+":"+cid,1,5,TimeUnit.DAYS);
        OrdersMessage ordersMessage = new OrdersMessage(sUser.getSid(), cid,true);
        mqSender.sendCoursesChooseMessage(JsonUtil.ObjectToJsonStr(ordersMessage));
        return RespBean.success(0);
    }

    @RequestMapping("/doAddCourses")
    @ResponseBody
    public RespBean doAddCourses(TUser tUser, CoursesVo coursesVo, HttpServletRequest request, HttpServletResponse response){
        if (tUser == null) {
            return RespBean.error(RespBeanEnum.NOT_LOGIN_ERROR);
        }
        coursesVo.setTid(tUser.getTid());
        coursesVo.setTname(tUser.getName());
        mqSender.sendAddCoursesMessage(JsonUtil.ObjectToJsonStr(coursesVo));
        return RespBean.success(0);
    }

    /**
     * 抢课按钮
     * June
     */
    @RequestMapping(value = "/doDrop/{cid}", method = RequestMethod.POST)
    @ResponseBody
    public RespBean doDrop(SUser sUser, @PathVariable Long cid) {
        if (sUser == null) {
            return RespBean.error(RespBeanEnum.SESSION_ERROR);
        }
        if(Choosaile.get(cid)==null) {
            return RespBean.error(RespBeanEnum.UNABLE_CHOOSE);
        }
        //预减库存
        Long stock = redisTemplate.opsForValue().increment("CoursesLast:" + cid);
        if (stock< Choosaile.get(cid).getMax()) {
            Choosaile.get(cid).setNow(Choosaile.get(cid).getNow()-1);
            Choosaile.get(cid).setFlags(true);
        }
        redisTemplate.delete("CoursesOrder:"+sUser.getSid()+":"+cid);
        OrdersMessage ordersMessage = new OrdersMessage(sUser.getSid(), cid,false);
        mqSender.sendCoursesChooseMessage(JsonUtil.ObjectToJsonStr(ordersMessage));
        return RespBean.success(0);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<CoursesVo> list = coursesService.getAllCoursesVo();
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        list.forEach(coursesVo -> {
            redisTemplate.opsForValue().set("Courses:"+coursesVo.getCid(),coursesVo);
            redisTemplate.opsForValue().set("CoursesLast:" + coursesVo.getCid(), (coursesVo.getMaxStudents() - coursesVo.getNowStudents()));
            FullList fullList=new FullList();
            fullList.setNow(coursesVo.getNowStudents());
            fullList.setMax(coursesVo.getMaxStudents());
            fullList.setFlags(coursesVo.getNowStudents()<coursesVo.getMaxStudents());
            Choosaile.put(coursesVo.getCid(),fullList);
        });
    }
}
