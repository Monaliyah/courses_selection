package com.me.courses_selection.config.ArgumentResolver;

import com.me.courses_selection.pojo.SUser;
import com.me.courses_selection.pojo.TUser;
import com.me.courses_selection.service.IRootService;
import com.me.courses_selection.service.ISUserService;
import com.me.courses_selection.service.ITUserService;
import com.me.courses_selection.utils.CookieUtil;
import com.me.courses_selection.utils.toBean;
import com.me.courses_selection.vo.pojo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *自定义参数
 *June
 */
@Component
public class TUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private ITUserService tUserService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz=parameter.getParameterType();
        return clazz== TUser.class;
    }
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String tTicket = CookieUtil.getCookieValue(request, "TUserTicket");
        if (StringUtils.isEmpty(tTicket)) {
            return null;
        }
        return tUserService.getTUserByCookie(tTicket, request, response);
    }
}

