package com.me.courses_selection.controller;



import com.me.courses_selection.pojo.SUser;
import com.me.courses_selection.service.ISUserService;
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
@RequestMapping("/SUser")
public class SUserController {
    @Autowired
    private ISUserService sUserService;

    @Autowired
    private ThymeleafViewUtil thymeleafViewUtil;


    @RequestMapping(value="/getSUser", method= RequestMethod.POST)
    @ResponseBody
    public SUser getSUser(SUser sUser)
    {
        return sUser;
    }

    @RequestMapping(value="/doAddOneSUser",method = RequestMethod.POST)
    @ResponseBody
    public RespBean doAddOneSUser(@Valid RegistVo registVo){
        return sUserService.doAddOneSUser(registVo);
    }

    @RequestMapping(value="/doDeleteOneSUser/{sid}",method = RequestMethod.POST)
    @ResponseBody
    public RespBean doAddOneSUser(@PathVariable Long sid){
        return sUserService.doDeleteOneSUser(sid);
    }

    @RequestMapping(value="/toSUserHome",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toSUserHome(Model model,SUser sUser, HttpServletRequest request, HttpServletResponse response){
        String name="SUserHome";
        model.addAttribute("SUser",sUser);
        String Page=thymeleafViewUtil.doThymeleafView(name,model,request,response,60);
        return Page;
    }
}
