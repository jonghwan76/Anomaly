package com.realblox.dimetime.service;

import com.realblox.dimetime.dao.WallpaperMapper;
import com.realblox.dimetime.model.WallpaperVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WallpaperService {
    Logger _logger = LoggerFactory.getLogger( WallpaperService.class );

    @Autowired
    WallpaperMapper wallpaperMapper;

    public List<WallpaperVO> getUserWallpaperList(String user_id) { return wallpaperMapper.getUserWallpaperList(user_id); }

    public List<WallpaperVO> getWallpaperList() { return wallpaperMapper.getWallpaperList(); }
}