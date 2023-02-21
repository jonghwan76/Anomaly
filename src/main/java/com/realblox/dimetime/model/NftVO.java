package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("NftVO")
public class NftVO {
    private String nft_idx;
    private String nft_name;
    private String nft_image;
    private String nft_json_url;
    private String nft_description;
    private String nft_edition;
    private String nft_rarity;
    private String nft_type;
    private String nft_level;
    private String nft_durability;
    private String nft_capacity;
    private String nft_speed;
    private String nft_stablility;
    private String nft_worthiness;
    private String nft_buildcount;
    private String mint_yn;
    private String init_yn;
    private String sail_time;
    private String rudder;
    private String flag;
    private String sail;
    private String body;
    private String bottom;
    private String background;

    private String rudder_url;
    private String flag_url;
    private String sail_url;
    private String body_url;
    private String bottom_url;
    private String background_url;
    private String breeding_cnt;

    private String order_no;
    private String slot_no;
    private String sale_yn;
    private String reg_dt;
}