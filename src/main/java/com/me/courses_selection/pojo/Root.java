package com.me.courses_selection.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author June
 * @since 2022-04-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("root")
public class Root implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * order id
     */
    @TableId(value = "rid", type = IdType.AUTO)
    private Long rid;

    private String name;

    private String password;

    private String salt;


}
