package com.me.courses_selection.vo.MQMessage;

import com.me.courses_selection.pojo.SUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * June
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersMessage {
    private Long sid;
    private Long cid;
    /**
     * True是选课
     * False是退课
     */
    private Boolean flag;
}
