package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("UserPotHistoryVO")
public class UserPotHistoryVO {
    private String pot_idx;
    private String pot_type;
    private String pot_desc;
    private String amount;
    private String user_id;
    private String reg_dt;
}