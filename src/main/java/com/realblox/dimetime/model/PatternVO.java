package com.realblox.dimetime.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;
@Data
@Alias("PatternVO")
public class PatternVO {
    private String user_id;
    private String reg_dt;
    private String gps_lat;
    private String gps_lon;
    private String gyroscope_x;
    private String gyroscope_y;
    private String gyroscope_z;
    private String Timestamp;
    private String ext2;
    private String ext3;
}