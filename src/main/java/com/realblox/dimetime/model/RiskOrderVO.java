package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("RiskOrderVO")
public class RiskOrderVO {
    private String idx;
    private String user_id;
    private String user_order;
    private String stat_dt;
    private String reg_dt;
}