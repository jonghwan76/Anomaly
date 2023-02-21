package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("WallpaperVO")
public class WallpaperVO {
    private String wallpaper_idx;
    private String wallpaper_name;
    private String wallpaper_cost;
    private String wallpaper_url;
    private String reg_dt;
}