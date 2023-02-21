package com.realblox.dimetime.service;

import com.realblox.dimetime.dao.ItemMapper;
import com.realblox.dimetime.dao.TimerMapper;
import com.realblox.dimetime.model.ItemVO;
import com.realblox.dimetime.model.TimerVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class ItemService {
    Logger _logger = LoggerFactory.getLogger( ItemService.class );

    @Autowired
    ItemMapper itemMapper;

    public List<ItemVO> getUserItemList(HashMap hashMap) { return itemMapper.getUserItemList(hashMap); }
    public List<ItemVO> getItemList() { return itemMapper.getItemList(); }
    public ItemVO getItemInfo(String item_id) { return itemMapper.getItemInfo(item_id); }
    public ItemVO getItemRateInfo(String rate) { return itemMapper.getItemRateInfo(rate); }
    public int updateUserItemCnt(HashMap hashMap) { return itemMapper.updateUserItemCnt(hashMap); }

}