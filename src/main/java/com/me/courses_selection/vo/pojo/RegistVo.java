package com.me.courses_selection.vo.pojo;

import com.me.courses_selection.validator.isId;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * June
 */
@Data
public class RegistVo {

    @NotNull
    @isId
    private String id;

    @NotNull
    private String name;

    @NotNull
    @Length(min=32)
    private String password;
}