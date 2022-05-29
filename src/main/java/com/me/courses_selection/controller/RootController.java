package com.me.courses_selection.controller;


import com.me.courses_selection.pojo.Root;
import com.me.courses_selection.utils.ThymeleafViewUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author June
 * @since 2022-04-19
 */
@Controller
@RequestMapping("/Root")
public class RootController {
    @Autowired
    private ThymeleafViewUtil thymeleafViewUtil;


    @RequestMapping(value="/getRoot", method= RequestMethod.POST)
    @ResponseBody
    public Root getRoot(Root root) { return root; }

    @RequestMapping(value="/toRootHome",produces = "text/html;charset=utf-8")
    @ResponseBody
    public String toRootHome(Model model, Root root, HttpServletRequest request, HttpServletResponse response){
        String name="RootHome";
        model.addAttribute("Root",root);
        String Page=thymeleafViewUtil.doThymeleafView(name,model,request,response,60);
        return Page;
    }
}
