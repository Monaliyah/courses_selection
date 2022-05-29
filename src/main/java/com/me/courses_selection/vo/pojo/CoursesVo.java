package com.me.courses_selection.vo.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 抢课课程对象
 * June
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursesVo {

    private Long cid;
    private String cname;
    private Long tid;
    private String tname;
    private Integer subtype;
    private String lastedDate;
    private Integer maxStudents;
    private Integer nowStudents;
    private BigDecimal credits;
    private Integer duration;
    private Integer session;
    private boolean flag=false;

}
