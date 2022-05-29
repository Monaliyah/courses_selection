package com.me.courses_selection.mapper;

import com.baomidou.mybatisplus.core.mapper.Mapper;
import com.me.courses_selection.vo.pojo.PointOfCourses;

import java.util.List;

public interface PointListMapper extends Mapper<PointListMapper> {

    List<PointOfCourses> getPointListBySid(Long sid);
}
