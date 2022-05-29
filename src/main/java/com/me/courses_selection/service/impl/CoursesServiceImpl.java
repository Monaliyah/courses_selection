package com.me.courses_selection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.me.courses_selection.mapper.CoursesMapper;
import com.me.courses_selection.pojo.Courses;
import com.me.courses_selection.service.ICoursesService;
import com.me.courses_selection.vo.pojo.CoursesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 * June
 */
@Service
public class CoursesServiceImpl extends ServiceImpl<CoursesMapper, Courses> implements ICoursesService {

    @Autowired
    @Resource
    private CoursesMapper coursesMapper;

    /**
     * 获取所有课程列表
     */
    @Override
    public List<CoursesVo> getAllCoursesVo() {
        return coursesMapper.getAllCoursesVo();
    }

    /**
     * 通过cid获取课程
     */
    @Override
    public CoursesVo getOneCoursesVoByCid(Long cid) {
        return coursesMapper.getOneCoursesVoByCid(cid);
    }

    @Override
    public Courses AddCourses(CoursesVo coursesVo) {

        Courses courses = new Courses();

        courses.setCid(coursesMapper.getNewCid() + 1);
        courses.setCname(coursesVo.getCname());
        courses.setTid(coursesVo.getTid());
        courses.setTname(coursesVo.getTname());
        courses.setSubtype(coursesVo.getSubtype());
        courses.setMaxStudents(coursesVo.getMaxStudents());
        courses.setCredits(coursesVo.getCredits());
        courses.setSession(coursesVo.getSession());
        coursesMapper.insert(courses);
        return courses;
    }

    @Override
    public List<CoursesVo> getCoursesVosByTid(Long tid) {
        return coursesMapper.getCoursesVosByTid(tid);
    }
}

