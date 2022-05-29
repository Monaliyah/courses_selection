package com.me.courses_selection.vo.Return;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 公共返回对象
 * 枚举
 * June
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespBeanEnum {

    /**
     * 通用
     */
    SUCCESS(200, "SUCCESS"),
    ERROR(500, "服务端异常"),

    /**
     * 登录
     */
    LOGIN_ERROR(500210,"用户名或密码错误"),
    SID_ERROR(500211,"学号格式错误"),
    BIND_ERROR(500212,"参数校验异常"),
    SESSION_ERROR(500213,"用户不存在"),
    NOT_LOGIN_ERROR(500214,"用户未登录"),
    TID_ERROR(500221,"教师编号格式错误"),
    ACCESS_LIMIT_REACHED(500503,"访问过于频繁"),
    /**
     * 抢课
     */
    UNABLE_CHOOSE(500500,"该课程人数已满"),
    BE_CHOSEN(500501,"已经选过该课"),
    REQUEST_ILLEGAL(500502,"请求非法，请重新尝试"),


    CHOOSE_SUCCESS(200201,"选课成功！"),
    ;

    private final Integer code;
    private final String massage;
}
