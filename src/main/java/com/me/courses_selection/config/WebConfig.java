package com.me.courses_selection.config;

import com.me.courses_selection.config.AccessLimit.AccessLimitIntercepor;
import com.me.courses_selection.config.ArgumentResolver.*;
import com.me.courses_selection.vo.pojo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * MVC控制类
 * June
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AccessLimitIntercepor accessLimitIntercepor;
    @Autowired
    private CoursesArgumentResolver coursesArgumentResolver;
    @Autowired
    private LoginVoArgumentResolver loginVoArgumentResolver;
    @Autowired
    private RegistVoArgumentResolver registVoArgumentResolver;
    @Autowired
    private SUserArgumentResolver sUserArgumentResolver;
    @Autowired
    private TUserArgumentResolver tUserArgumentResolver;
    @Autowired
    private RootArgumentResolver rootArgumentResolver;

    /**
     * 为了防止拦截器拦截static资源
     * June
     */
    private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
            "classpath:/META-INF/resources/", "classpath:/resources/",
            "classpath:/static/", "classpath:/public/", "classpath:/templates/htm/"};

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/webjars/**").addResourceLocations(
                    "classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry.addResourceHandler("/**").addResourceLocations(
                    CLASSPATH_RESOURCE_LOCATIONS);
        }

    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(accessLimitIntercepor);
//    }

    /**
     * 添加自定义注解类
     * June
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers){
        resolvers.add(coursesArgumentResolver);
        resolvers.add(loginVoArgumentResolver);
        resolvers.add(registVoArgumentResolver);
        resolvers.add(sUserArgumentResolver);
        resolvers.add(tUserArgumentResolver);
        resolvers.add(rootArgumentResolver);
    }

}
