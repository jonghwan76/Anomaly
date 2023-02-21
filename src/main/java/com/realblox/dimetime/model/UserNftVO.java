package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserNftVO")
public class UserNftVO {
    private String nft_idx;
    private String slot_no;
    private String order_no;
    private String reg_dt;
    private String user_id;
}