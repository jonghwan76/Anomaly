package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("TimerVO")
public class TimerVO {
    private String timer_idx;
    private String tag_category;
    private String tag_time;
    private String start_time;
    private String end_time;
    private String sum_time;
    private String start_gps_pos_lat;
    private String start_gps_pos_lon;
    private String end_gps_pos_lat;
    private String end_gps_pos_lon;
    private String end_flag;
    private String nft_idx;
    private String reg_dt;
    private String item_id_list;
    private String item_name_list;
    private String user_id;
}