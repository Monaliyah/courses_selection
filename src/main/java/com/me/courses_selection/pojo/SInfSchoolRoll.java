package com.me.courses_selection.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author June
 * @since 2022-03-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SInfSchoolRoll implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long sid;

    /**
     * 年级
     */
    private Integer grade;

    /**
     * 学院名称
     */
    private String collegeName;

    /**
     * 专业名称
     */
    private String professionalName;

    /**
     * 班级
     */
    private String className;

    /**
     * 是否报到
     */
    private String registration;

    /**
     * 本科或专科
     */
    private Integer category;


}
