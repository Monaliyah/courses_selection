package com.me.courses_selection.config.ArgumentResolver;

import com.me.courses_selection.pojo.SUser;
import com.me.courses_selection.service.ISUserService;
import com.me.courses_selection.utils.CookieUtil;
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
public class SUserArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private ISUserService sUserService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz=parameter.getParameterType();
        return clazz== SUser.class;
    }
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String sTicket = CookieUtil.getCookieValue(request, "SUserTicket");
        if (StringUtils.isEmpty(sTicket)) {
            return null;
        }
        return sUserService.getSUserByCookie(sTicket, request, response);
    }
}

