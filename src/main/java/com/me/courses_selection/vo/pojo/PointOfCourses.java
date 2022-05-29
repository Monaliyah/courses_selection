package com.me.courses_selection.vo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * June
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointOfCourses {

    private Long cid;
    private String cname;
    private Long tid;
    private String tname;
    private BigDecimal totalScore;
    private BigDecimal regularScore;
    private BigDecimal termScore;
}
