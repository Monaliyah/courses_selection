package com.me.courses_selection.rabbitmq;

import com.me.courses_selection.service.ICoursesService;
import com.me.courses_selection.service.IOrdersService;
import com.me.courses_selection.utils.JsonUtil;
import com.me.courses_selection.vo.pojo.CoursesVo;
import com.me.courses_selection.vo.MQMessage.OrdersMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * 消息队列接收接口
 * June
 */
@Service
@Slf4j
public class MQReceiver {

    @Autowired
    private ICoursesService coursesService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IOrdersService ordersService;

    /**
     * 接收消息并调用函数写入mysql
     */
    @RabbitListener(queues = "CoursesChooseQueue")
    public void Receive_CoursesChoose(String message) {
        log.info("接收到信息：" + message);
        OrdersMessage ordersMessage = JsonUtil.JsonStrToObject(message, OrdersMessage.class);
        Long cid = ordersMessage.getCid();
        Long sid = ordersMessage.getSid();
        Boolean flag= ordersMessage.getFlag();
        if(flag){
            CoursesVo coursesVo = coursesService.getOneCoursesVoByCid(cid);
            if (coursesVo.getMaxStudents()- coursesVo.getNowStudents()<1) {
                return;
            }
            String tempOrderJson = (String) redisTemplate.opsForValue().get("Order:" + sid + ":" + cid);
            if (!StringUtils.isEmpty(tempOrderJson)) {
                return;
            }
            ordersService.WriteToOrders(sid,cid);
        }else {
            ordersService.DropFromOrders(sid, cid);
        }
    }




    @RabbitListener(queues = "AddCoursesQueue")
    public void Receive_AddCourses(String message) {
        log.info("接收到信息：" + message);
        CoursesVo coursesVo = JsonUtil.JsonStrToObject(message, CoursesVo.class);
        coursesService.AddCourses(coursesVo);
    }
}