package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("RiskVO")
public class RiskVO {
    private String idx;
    private String user_id;
    private String gps_variation;
    private String time_amount;
    private String stat_dt;
    private String reg_dt;
}