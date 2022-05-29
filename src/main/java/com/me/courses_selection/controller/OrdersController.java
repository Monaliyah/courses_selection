package com.me.courses_selection.controller;


import com.me.courses_selection.pojo.Orders;
import com.me.courses_selection.pojo.SUser;
import com.me.courses_selection.service.IOrdersService;
import com.me.courses_selection.vo.pojo.PointOfCourses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * June
 */
@Controller
@RequestMapping("/Orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    private Map<Long, PointOfCourses> PointList = new HashMap<>();

    @RequestMapping(value="/getPointList", method= RequestMethod.POST)
    @ResponseBody
    public List<PointOfCourses> getOrdersList(SUser sUser) {
        List<PointOfCourses> pointListBySid=ordersService.getPointListBySid(sUser.getSid());
        System.out.println(pointListBySid);
        return pointListBySid;
    }
}
