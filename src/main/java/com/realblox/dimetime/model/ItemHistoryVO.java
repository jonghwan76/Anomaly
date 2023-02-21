package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("ItemHistoryVO")
public class ItemHistoryVO {
    private String idx;
    private String item_id;
    private String user_id;
    private String reg_dt;
}