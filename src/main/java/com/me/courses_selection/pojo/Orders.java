package com.me.courses_selection.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * June
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("orders")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * order id
     */
    @TableId(value = "oid", type = IdType.AUTO)
    private Long oid;

    private Long sid;

    private Long cid;

    private BigDecimal totalScore;

    private BigDecimal regularScore;

    private BigDecimal termScore;


}
