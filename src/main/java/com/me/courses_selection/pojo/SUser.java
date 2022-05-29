package com.me.courses_selection.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 *
 * June
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("sUser")
public class SUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 学号
     */
    @TableId(value = "sid", type = IdType.AUTO)
    private Long sid;

    private String name;

    private String password;

    private String salt;


}
