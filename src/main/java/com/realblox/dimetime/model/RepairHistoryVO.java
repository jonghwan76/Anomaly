package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("RepairHistoryVO")
public class RepairHistoryVO {
    private String idx;
    private String nft_idx;
    private String user_id;
    private String reg_dt;
}