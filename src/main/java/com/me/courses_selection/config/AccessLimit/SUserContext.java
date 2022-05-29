package com.me.courses_selection.config.AccessLimit;


import com.me.courses_selection.pojo.SUser;

/**
 * June
 */
public class SUserContext {

    private static ThreadLocal<SUser> sUserThreadLocal=new ThreadLocal<SUser>();

    public static void setUser(SUser sUser){
        sUserThreadLocal.set(sUser);
    }

    public static SUser getUser(){
        return sUserThreadLocal.get();
    }

}