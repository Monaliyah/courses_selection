package com.me.courses_selection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.me.courses_selection.exception.GlobalException;
import com.me.courses_selection.mapper.SUserMapper;
import com.me.courses_selection.pojo.SUser;
import com.me.courses_selection.service.ISUserService;
import com.me.courses_selection.utils.CookieUtil;
import com.me.courses_selection.utils.MD5Util;
import com.me.courses_selection.utils.UUIDUtil;
import com.me.courses_selection.vo.Return.RespBean;
import com.me.courses_selection.vo.Return.RespBeanEnum;
import com.me.courses_selection.vo.pojo.LoginVo;
import com.me.courses_selection.vo.pojo.RegistVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 *
 * June
 */
@Service
public class SUserServiceImpl extends ServiceImpl<SUserMapper, SUser> implements ISUserService {

    @Autowired
    @Resource
    private SUserMapper sUserMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 验证登录
     */
    @Override
    public RespBean doSLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        String sid=loginVo.getId();
        String password=loginVo.getPassword();
        SUser sUser=sUserMapper.selectById(sid);
        //根据学号获取用户
        if(null==sUser){
            throw new GlobalException((RespBeanEnum.LOGIN_ERROR));
        }
        //判断密码
        if(!MD5Util.SecondCode(password,sUser.getSalt()).equals(sUser.getPassword())) {
            throw new GlobalException((RespBeanEnum.LOGIN_ERROR));
        }
        //生成Cookie
        String sTicket= UUIDUtil.uuid();
        //学生信息存入redis
        redisTemplate.opsForValue().set("SUser:"+sTicket,sUser,1, TimeUnit.DAYS);
        CookieUtil.setCookie(request,response,"SUserTicket",sTicket);
        return RespBean.success("SUser/toSUserHome");
    }

    /**
     * 从Cookie获取用户
     */
    @Override
    public SUser getSUserByCookie(String sUserTicket,HttpServletRequest request,HttpServletResponse response) {
        if(StringUtils.isEmpty(sUserTicket)) {
            return null;
        }
        SUser sUser=(SUser)redisTemplate.opsForValue().get("SUser:"+sUserTicket);
        if(sUser!=null){
            CookieUtil.setCookie(request,response,"SUserTicket",sUserTicket);
        }
        return sUser;
    }

    @Override
    public SUser getSUserBySid(Long id){
        SUser sUser=sUserMapper.selectById(id);
        return sUser;
    }

    @Override
    public RespBean doAddOneSUser(RegistVo registVo){
        SUser sUser=new SUser();
        sUser.setSid(Long.valueOf(registVo.getId()));
        sUser.setName(registVo.getName());
        sUser.setPassword(MD5Util.SecondCode(registVo.getPassword(),"J1u0n6e2"));
        sUser.setSalt("J1u0n6e2");
        sUserMapper.insert(sUser);
        return RespBean.success();
    }

    @Override
    public RespBean doDeleteOneSUser(Long sid){
        sUserMapper.delete(new QueryWrapper<SUser>().eq("sid",sid));
        return RespBean.success();
    }
}