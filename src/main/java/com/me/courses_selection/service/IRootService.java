package com.me.courses_selection.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.me.courses_selection.pojo.Root;
import com.me.courses_selection.vo.Return.RespBean;
import com.me.courses_selection.vo.pojo.LoginVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author June
 * @since 2022-04-19
 */
public interface IRootService extends IService<Root> {

    RespBean doRLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response);

    Root getRootByCookie(String rTicket, HttpServletRequest request, HttpServletResponse response);
}
