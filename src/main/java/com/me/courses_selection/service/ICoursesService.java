package com.me.courses_selection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.me.courses_selection.pojo.Courses;
import com.me.courses_selection.vo.pojo.CoursesVo;

import java.util.List;

/**
 *
 * June
 */
public interface ICoursesService extends IService<Courses> {

    /**
     * 获取所有课程列表
     */
    List<CoursesVo> getAllCoursesVo();

    /**
     * 通过cid获取课程
     */
    CoursesVo getOneCoursesVoByCid(Long cid);

    Courses AddCourses(CoursesVo coursesVo);

    List<CoursesVo> getCoursesVosByTid(Long tid);

}
