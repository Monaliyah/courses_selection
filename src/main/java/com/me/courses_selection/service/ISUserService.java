package com.me.courses_selection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.me.courses_selection.pojo.SUser;
import com.me.courses_selection.vo.Return.RespBean;
import com.me.courses_selection.vo.pojo.LoginVo;
import com.me.courses_selection.vo.pojo.RegistVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * June
 */
public interface ISUserService extends IService<SUser> {

    /**
     * 验证登录
     */
    RespBean doSLogin(LoginVo sLoginVo, HttpServletRequest request, HttpServletResponse response);
    /**
     * 从Cookie获取用户
     */
    SUser getSUserByCookie(String sUserTicket,HttpServletRequest request,HttpServletResponse response);

    SUser getSUserBySid(Long id);

    RespBean doAddOneSUser(RegistVo registVo);

    RespBean doDeleteOneSUser(Long sid);
}
