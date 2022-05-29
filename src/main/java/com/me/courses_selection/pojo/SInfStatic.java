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
public class SInfStatic implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long sid;

    /**
     * 0为女，1为男
     */
    private Integer sex;

    /**
     * 0为身份证，
     */
    private Integer documentType;

    private String documentId;

    private Integer nationality;


}
