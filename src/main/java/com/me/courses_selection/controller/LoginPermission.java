package com.me.courses_selection.controller;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author June
 * @since 2022-05-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LoginPermission implements Serializable {

    private Long id;
    private String role;
}
