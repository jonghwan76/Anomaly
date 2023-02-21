package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserTimeHistoryVO")
public class UserTimeHistoryVO {
    private String idx;
    private String timer_idx;
    private String time_type;
    private String tag_category;
    private String time_desc;
    private String amount;
    private String pot_amount;
    private String dtc_amount;
    private String gps_variation;
    private String start_time;
    private String end_time;
    private String user_id;
    private String reg_dt;
}