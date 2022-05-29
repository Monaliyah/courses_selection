package com.me.courses_selection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.me.courses_selection.pojo.Orders;
import com.me.courses_selection.vo.pojo.PointOfCourses;

import java.util.List;

/**
 *
 * June
 */
public interface IOrdersService extends IService<Orders> {
    /**
     * 抢课成功写数据库
     */
    Orders WriteToOrders(Long sid, Long cid);

    void DropFromOrders(Long sid, Long cid);

    List<PointOfCourses> getPointListBySid(Long sid);
}
