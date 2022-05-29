package com.me.courses_selection.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.me.courses_selection.pojo.SInfStatic;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author June
 * @since 2022-03-13
 */
public interface SInfStaticMapper extends BaseMapper<SInfStatic> {

    SInfStatic getOneSInfStaticBySid(Long sid);
}
