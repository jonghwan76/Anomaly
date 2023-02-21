package com.realblox.dimetime.dao;

import com.realblox.dimetime.model.WallpaperVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface StoreMapper {
    int buyItem(HashMap hashMap);
    int updateUserPot(HashMap hashMap);
}