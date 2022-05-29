package com.me.courses_selection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.me.courses_selection.exception.GlobalException;
import com.me.courses_selection.mapper.TUserMapper;
import com.me.courses_selection.pojo.TUser;
import com.me.courses_selection.service.ITUserService;
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
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 *
 * June
 */
@Service
public class TUserServiceImpl extends ServiceImpl<TUserMapper, TUser> implements ITUserService {


    @Autowired
    @Resource
    private TUserMapper tUserMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 验证登录
     */
    @Override
    public RespBean doTLogin(LoginVo loginVo, HttpServletRequest request, HttpServletResponse response){
        String tid=loginVo.getId();
        String password=loginVo.getPassword();
        TUser tUser=tUserMapper.selectById(tid);
        //根据学号获取用户
        if(null==tUser){
            throw new GlobalException((RespBeanEnum.LOGIN_ERROR));
        }
        //判断密码
        if(!MD5Util.SecondCode(password,tUser.getSalt()).equals(tUser.getPassword())) {
            throw new GlobalException((RespBeanEnum.LOGIN_ERROR));
        }
        //生成Cookie
        String tTicket= UUIDUtil.uuid();
        //学生信息存入redis
        redisTemplate.opsForValue().set("TUser:"+tTicket,tUser,1, TimeUnit.DAYS);
        CookieUtil.setCookie(request,response,"TUserTicket",tTicket);
        return RespBean.success("TUser/toTUserHome");
    }
    /**
     * 从Cookie获取用户
     */
    @Override
    public TUser getTUserByCookie(String tUserTicket,HttpServletRequest request,HttpServletResponse response) {
        if(StringUtils.isEmpty(tUserTicket)) {
            return null;
        }
        TUser tUser=(TUser)redisTemplate.opsForValue().get("TUser:"+tUserTicket);
        if(tUser!=null){
            CookieUtil.setCookie(request,response,"TUserTicket",tUserTicket);
        }
        return tUser;
    }

    @Override
    public TUser getTUserByTid(Long id){
        TUser tUser=tUserMapper.selectById(id);
        return tUser;
    }

    @Override
    public RespBean doAddOneTUser(RegistVo registVo){
        TUser tUser=new TUser();
        tUser.setTid(Long.valueOf(registVo.getId()));
        tUser.setName(registVo.getName());
        tUser.setPassword(MD5Util.SecondCode(registVo.getPassword(),"J1u0n6e2"));
        tUser.setSalt("J1u0n6e2");
        tUserMapper.insert(tUser);
        return RespBean.success();
    }

    @Override
    public RespBean doDeleteOneTUser(Long tid){
        tUserMapper.delete(new QueryWrapper<TUser>().eq("tid",tid));
        return RespBean.success();
    }
}
