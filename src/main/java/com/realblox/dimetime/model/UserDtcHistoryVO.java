package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserDtcHistoryVO")
public class UserDtcHistoryVO {
    private String dtc_idx;
    private String dtc_type;
    private String dtc_desc;
    private String amount;
    private String user_id;
    private String reg_dt;
}