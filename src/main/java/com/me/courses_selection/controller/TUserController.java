package com.me.courses_selection.controller;


import com.me.courses_selection.pojo.TUser;
import com.me.courses_selection.service.ITUserService;
import com.me.courses_selection.utils.ThymeleafViewUtil;
import com.me.courses_selection.vo.Return.RespBean;
import com.me.courses_selection.vo.pojo.RegistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


/**
 *
 * June
 */
@Controller
@RequestMapping("/TUser")
public class TUserController {


    @Autowired
    private ITUserService tUserService;

    @Autowired
    private ThymeleafViewUtil thymeleafViewUtil;



    @RequestMapping(value = "/getTUser", method = RequestMethod.POST)
    @ResponseBody
    public TUser getTUser(TUser tUser) { return tUser; }

    @RequestMapping(value="/doAddOneTUser",method = RequestMethod.POST)
    @ResponseBody
    public RespBean doAddOneTUser(@Valid RegistVo registVo){
        return tUserService.doAddOneTUser(registVo);
    }

    @RequestMapping(value="/doDeleteOneTUser/{tid}",method = RequestMethod.POST)
    @ResponseBody
    public RespBean doAddOneTUser(@PathVariable Long tid){
        return tUserService.doDeleteOneTUser(tid);
    }

    @RequestMapping(value="/toTUserHome",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toTUserHome(Model model,TUser tUser, HttpServletRequest request, HttpServletResponse response) {
        String name = "TUserHome";
        model.addAttribute("TUser", tUser);
        String Page = thymeleafViewUtil.doThymeleafView(name, model, request, response, 60);
        return Page;
    }



}
