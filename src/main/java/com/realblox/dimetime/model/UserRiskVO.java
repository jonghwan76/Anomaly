package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserRiskVO")
public class UserRiskVO {
    private String stat_dt;
    private String risk_dt;
    private String user_order;
    private String user_id;
    private String user_name;
    private String app_token;
    private String login_time;
    private String online_yn;
    private String total_pot;
    private String total_dtc;
    private String wallet_address;
    private String rest_yn;
    private String nft_list;
    private String reg_dt;

    private String startDate;
    private String endDate;

}