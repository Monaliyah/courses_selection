package com.me.courses_selection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.me.courses_selection.pojo.TUser;
import com.me.courses_selection.vo.Return.RespBean;
import com.me.courses_selection.vo.pojo.LoginVo;
import com.me.courses_selection.vo.pojo.RegistVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 验证登录
 */
public interface ITUserService extends IService<TUser> {

    /**
     * 验证登录
     */
    RespBean doTLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    /**
     * 从Cookie获取用户
     */
    TUser getTUserByCookie(String tUserTicket,HttpServletRequest request,HttpServletResponse response);

    TUser getTUserByTid(Long id);

    RespBean doAddOneTUser(RegistVo registVo);

    RespBean doDeleteOneTUser(Long tid);
}
