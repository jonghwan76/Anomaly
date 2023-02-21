package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("AccountVO")
public class AccountVO {
    private String user_id;
    private String user_name;
    private String app_token;
    private String security_token;
    private String nickname;
    private String login_time;
    private String online_yn;
    private String total_pot;
    private String total_dtc;
    private String wallet_yn;
    private String slot_list;
    private String withdraw_yn;
    private String rest_yn;
    private String reg_dt;
    private String mod_dt;
}