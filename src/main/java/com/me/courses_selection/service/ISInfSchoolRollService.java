package com.me.courses_selection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.me.courses_selection.pojo.SInfSchoolRoll;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author June
 * @since 2022-03-13
 */
public interface ISInfSchoolRollService extends IService<SInfSchoolRoll> {

    SInfSchoolRoll getSInfSchoolRoll(Long sid);
}
