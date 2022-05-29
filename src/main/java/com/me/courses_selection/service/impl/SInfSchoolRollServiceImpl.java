package com.me.courses_selection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.me.courses_selection.mapper.SInfSchoolRollMapper;
import com.me.courses_selection.pojo.SInfSchoolRoll;
import com.me.courses_selection.service.ISInfSchoolRollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author June
 * @since 2022-03-13
 */
@Service
public class SInfSchoolRollServiceImpl extends ServiceImpl<SInfSchoolRollMapper, SInfSchoolRoll> implements ISInfSchoolRollService {

    @Autowired
    @Resource
    private SInfSchoolRollMapper sInfSchoolRollMapper;

    @Override
    public SInfSchoolRoll getSInfSchoolRoll(Long sid){
        return sInfSchoolRollMapper.getOneSInfSchoolRollBySid(sid);
    }
}
