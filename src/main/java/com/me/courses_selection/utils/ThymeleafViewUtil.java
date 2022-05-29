package com.me.courses_selection.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * June
 */
@Component
public class ThymeleafViewUtil {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ThymeleafViewResolver thymeleafViewResolver;

    public String doThymeleafView(String name, Model model, HttpServletRequest request, HttpServletResponse response,int time) {
        //redis获取页面，不为空返回页面
        String html = (String) redisTemplate.opsForValue().get(name);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
        //如果为空，手动渲染，存redis返回
        WebContext context = new WebContext(request, response, request.getServletContext(), request.getLocale(), model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process(name, context);
        if (!StringUtils.isEmpty(html)) {
            if(time!=0) {
                redisTemplate.opsForValue().set(name, html, time, TimeUnit.SECONDS);
            }
        }
        return html;
    }
}
