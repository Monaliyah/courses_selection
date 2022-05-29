package com.me.courses_selection.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.me.courses_selection.pojo.Orders;

import java.util.List;

/**
 *
 * June
 */
public interface OrdersMapper extends BaseMapper<Orders> {

    Long getNewOid();
}
