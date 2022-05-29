package com.me.courses_selection.controller;


import com.me.courses_selection.pojo.SInfStatic;
import com.me.courses_selection.pojo.SUser;
import com.me.courses_selection.service.ISInfStaticService;
import com.me.courses_selection.service.impl.SInfStaticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author June
 * @since 2022-03-13
 */
@Controller
@RequestMapping("/SInfStatic")
public class SInfStaticController {

    @Autowired
    private ISInfStaticService sInfStaticService;

    @RequestMapping(value = "/getSInfStatic", method = RequestMethod.POST)
    @ResponseBody
    public SInfStatic getSInfStatic(SUser sUser){
        return sInfStaticService.getSInfStatic(sUser.getSid());
    }


}
