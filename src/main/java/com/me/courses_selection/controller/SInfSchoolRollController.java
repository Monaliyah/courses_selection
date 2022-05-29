package com.me.courses_selection.controller;


import com.me.courses_selection.pojo.SInfSchoolRoll;
import com.me.courses_selection.pojo.SUser;
import com.me.courses_selection.service.ISInfSchoolRollService;
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
@RequestMapping("/SInfSchoolRoll")
public class SInfSchoolRollController {

    @Autowired
    private ISInfSchoolRollService schoolRollService;

    @RequestMapping(value = "/getSInfSchoolRoll", method = RequestMethod.POST)
    @ResponseBody
    public SInfSchoolRoll getSInfStatic(SUser sUser){
        return schoolRollService.getSInfSchoolRoll(sUser.getSid());
    }

}
