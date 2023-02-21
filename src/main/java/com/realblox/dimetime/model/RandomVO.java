package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("RandomVO")
public class RandomVO {
    private String nft_idx;
    private String capacity;
    private String speed;
    private String stablility;
    private String worthiness;

}