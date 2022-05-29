package com.me.courses_selection.vo.pojo;

import com.me.courses_selection.validator.isId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 学生登录对象
 * June
 */
@Data
public class LoginVo {

    @NotNull
    @isId
    private String id;

    @NotNull
    @Length(min=32)
    private String password;
}