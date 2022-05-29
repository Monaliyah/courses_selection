package com.me.courses_selection.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.me.courses_selection.exception.GlobalException;
import com.me.courses_selection.utils.CookieUtil;
import com.me.courses_selection.utils.MD5Util;
import com.me.courses_selection.utils.UUIDUtil;
import com.me.courses_selection.vo.Return.RespBean;
import com.me.courses_selection.vo.Return.RespBeanEnum;
import com.me.courses_selection.vo.pojo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import com.me.courses_selection.mapper.RootMapper;
import com.me.courses_selection.pojo.Root;
import com.me.courses_selection.service.IRootService;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author June
 * @since 2022-04-19
 */
@Service
public class RootServiceImpl extends ServiceImpl<RootMapper, Root> implements IRootService {

    @Autowired
    @Resource
    private RootMapper rootMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 验证登录
     */
    @Override
    public RespBean doRLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        String rid=loginVo.getId();
        String password=loginVo.getPassword();
        Root root=rootMapper.selectById(rid);
        System.out.println(root.toString());
        //根据学号获取用户
        if(null==root){
            throw new GlobalException((RespBeanEnum.LOGIN_ERROR));
        }
        //判断密码
        if(!MD5Util.SecondCode(password,root.getSalt()).equals(root.getPassword())) {
            throw new GlobalException((RespBeanEnum.LOGIN_ERROR));
        }
        //生成Cookie
        String rTicket= UUIDUtil.uuid();
        //学生信息存入redis
        redisTemplate.opsForValue().set("Root:"+rTicket,root,1, TimeUnit.DAYS);
        CookieUtil.setCookie(request,response,"RootTicket",rTicket);
        return RespBean.success("Root/toRootHome");
    }

    /**
     * 从Cookie获取用户
     */
    @Override
    public Root getRootByCookie(String rootTicket,HttpServletRequest request,HttpServletResponse response) {
        if(StringUtils.isEmpty(rootTicket)) {
            return null;
        }
        Root root=(Root)redisTemplate.opsForValue().get("Root:"+rootTicket);
        if(root!=null){
            CookieUtil.setCookie(request,response,"RootTicket",rootTicket);
        }
        return root;
    }
}
