package com.realblox.dimetime.service;

import com.realblox.dimetime.dao.StoreMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;

@Service
@Transactional
public class StoreService {
    Logger _logger = LoggerFactory.getLogger( StoreService.class );

    @Autowired
    StoreMapper storeMapper;

    public int buyItem(HashMap hashMap) { return storeMapper.buyItem(hashMap); }

    public int updateUserPot(HashMap hashMap) { return storeMapper.updateUserPot(hashMap); }
}