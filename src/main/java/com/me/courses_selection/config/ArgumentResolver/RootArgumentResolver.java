package com.me.courses_selection.config.ArgumentResolver;

import com.me.courses_selection.pojo.Root;
import com.me.courses_selection.service.IRootService;
import com.me.courses_selection.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *自定义参数
 *June
 */
@Component
public class RootArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private IRootService rootService;
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> clazz=parameter.getParameterType();
        return clazz== Root.class;
    }
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        String rTicket = CookieUtil.getCookieValue(request, "RootTicket");
        if (StringUtils.isEmpty(rTicket)) {
            return null;
        }
        return rootService.getRootByCookie(rTicket, request, response);
    }
}

