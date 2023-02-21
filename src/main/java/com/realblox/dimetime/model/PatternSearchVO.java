package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("PatternSearchVO")
public class PatternSearchVO {
    private String user_id;
    private String startDate;
    private String endDate;
}