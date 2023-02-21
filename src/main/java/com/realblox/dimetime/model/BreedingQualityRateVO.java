package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("BreedingQualityRateVO")
public class BreedingQualityRateVO {
    private String quality_grade1;
    private String quality_grade2;
    private String common;
    private String uncommon;
    private String rare;
    private String epic;
    private String legendary;
}