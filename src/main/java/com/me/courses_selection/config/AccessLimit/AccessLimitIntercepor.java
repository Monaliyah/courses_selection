package com.me.courses_selection.config.AccessLimit;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.me.courses_selection.pojo.SUser;
import com.me.courses_selection.service.ISUserService;
import com.me.courses_selection.utils.CookieUtil;
import com.me.courses_selection.vo.Return.RespBean;
import com.me.courses_selection.vo.Return.RespBeanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * June
 */
@Component
public class AccessLimitIntercepor implements HandlerInterceptor {

    @Autowired
    private ISUserService sUserService;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws Exception{
        if(handler instanceof HandlerMethod) {
            SUser sUser=getSUser(request,response);
            SUserContext.setUser(sUser);
            HandlerMethod hm = (HandlerMethod) handler;
            AccessLimit accessLimit = hm.getMethodAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int second = accessLimit.second();
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            String key = request.getRequestURI();
            if (needLogin){
                if(sUser==null){
                    render(response, RespBeanEnum.SESSION_ERROR);
                    return false;
                }
                key+=":"+sUser.getSid();
            }
            ValueOperations valueOperations=redisTemplate.opsForValue();
            Integer count = (Integer)valueOperations.get(key);
            if (count==null){
                valueOperations.set(key, 1,second, TimeUnit.SECONDS);
            }else if (count<maxCount){
                valueOperations.increment(key);
            }else {
                render(response,RespBeanEnum.ACCESS_LIMIT_REACHED);
                return false;
            }

        }
        return true;
    }

    private void render(HttpServletResponse response, RespBeanEnum respBeanEnum) throws IOException {
        response.setContentType ( "application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        RespBean respBean = RespBean.error(respBeanEnum);
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();
    }

    private SUser getSUser(HttpServletRequest request, HttpServletResponse response) {
        String ticket= CookieUtil.getCookieValue(request,"SUserTicket");
        if(StringUtils.isEmpty(ticket)){
            return null;
        }
        return sUserService.getSUserByCookie(ticket,request,response);
    }
}
