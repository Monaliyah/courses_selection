package com.me.courses_selection.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.me.courses_selection.mapper.SInfStaticMapper;
import com.me.courses_selection.pojo.SInfStatic;
import com.me.courses_selection.service.ISInfStaticService;
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
public class SInfStaticService extends ServiceImpl<SInfStaticMapper, SInfStatic> implements ISInfStaticService {

    @Autowired
    @Resource
    private SInfStaticMapper sInfStaticMapper;

    @Override
    public SInfStatic getSInfStatic(Long sid) {
        return sInfStaticMapper.getOneSInfStaticBySid(sid);
    }
}
