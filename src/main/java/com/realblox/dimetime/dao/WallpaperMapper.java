package com.realblox.dimetime.dao;

import com.realblox.dimetime.model.WallpaperVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WallpaperMapper {
    List<WallpaperVO> getUserWallpaperList(String user_id);

    List<WallpaperVO> getWallpaperList();
}