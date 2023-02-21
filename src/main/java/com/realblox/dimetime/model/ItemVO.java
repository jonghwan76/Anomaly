package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("ItemVO")
public class ItemVO {
    private String item_id;
    private String item_name;
    private String item_cost;
    private String item_desc;
    private String item_cnt;
    private String item_url;
    private String reg_dt;
}