package com.me.courses_selection.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * June
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("courses")
public class Courses implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程id
     */
    @TableId(value = "cid", type = IdType.AUTO)
    private Long cid;

    private String cname;

    /**
     * 授课教师
     */
    private Long tid;

    private String tname;

    /**
     * 1体育类2艺术类3理工类4文史类5教育类
     */
    private Integer subtype;

    /**
     * 几周到几周
     */
    private String lastedDate;

    /**
     * 学生数
     */
    private Integer maxStudents;

    private Integer nowStudents;

    /**
     * 学分
     */
    private BigDecimal credits;

    /**
     * 课时
     */
    private Integer duration;

    /**
     * 学年
     */
    private Integer session;
}
