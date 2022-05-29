package com.me.courses_selection.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.me.courses_selection.pojo.Courses;
import com.me.courses_selection.vo.pojo.CoursesVo;

import java.util.List;

/**
 *
 * June
 */
public interface CoursesMapper extends BaseMapper<Courses> {

    /**
     * 获取所有课程列表
     */
    List<CoursesVo> getAllCoursesVo();

    /**
     * 通过cid获取课程
     */
    CoursesVo getOneCoursesVoByCid(Long cid);

    Long getNewCid();

    List<CoursesVo> getCoursesVosByTid(Long tid);

}
