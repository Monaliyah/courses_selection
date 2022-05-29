package com.me.courses_selection.controller;

import com.me.courses_selection.config.AccessLimit.AccessLimit;
import com.me.courses_selection.pojo.SUser;
import com.me.courses_selection.pojo.TUser;
import com.me.courses_selection.service.IRootService;
import com.me.courses_selection.service.ISUserService;
import com.me.courses_selection.service.ITUserService;
import com.me.courses_selection.vo.Return.RespBean;
import com.me.courses_selection.vo.pojo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * 登录处理
 * June
 */
@Controller
@RequestMapping("/Login")
public class LoginController {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ISUserService sUserService;
    @Autowired
    private ITUserService tUserService;
    @Autowired
    private IRootService rootService;

    /**
     * 重定向至登录页面
     * June
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "Login";
    }

    /**
     * 接收登录信息并验证
     * 定向至抢课页面或返回
     * June
     */
//    @AccessLimit(second=5,maxCount=5,needLogin=false)
    @RequestMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginVo loginVo ,HttpServletRequest request, HttpServletResponse response){
        if(loginVo.getId().length()==10) {
            return sUserService.doSLogin(loginVo, request, response);
        }else if(loginVo.getId().charAt(0)=='1'){
            return rootService.doRLogin(loginVo,request,response);
        }else{
            return tUserService.doTLogin(loginVo,request,response);
        }
    }

    @RequestMapping("/doSearch/{id}")
    @ResponseBody
    public RespBean doSearch(@PathVariable Long id){
        if(id.toString().length()==10) {
            SUser sUser=sUserService.getSUserBySid(id);
            return RespBean.success(sUser);
        }else{
            TUser tUser=tUserService.getTUserByTid(id);
            return RespBean.success(tUser);
        }
    }

    @RequestMapping("/test")
    public String test(){
            LoginPermission permission=new LoginPermission();
            permission.setId(1908010108L);
            permission.setRole("admin");
            String token="123";
            redisTemplate.opsForValue().set("token:"+token,permission);
        System.out.println("ok");
        return "test";
    }
}