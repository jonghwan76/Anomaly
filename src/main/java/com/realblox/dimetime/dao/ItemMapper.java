package com.realblox.dimetime.dao;

import com.realblox.dimetime.model.ItemVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ItemMapper {
    List<ItemVO> getUserItemList(HashMap hashMap);
    List<ItemVO> getItemList();
    ItemVO getItemInfo(String item_id);
    ItemVO getItemRateInfo(String rate);
    int updateUserItemCnt(HashMap hashMap);
}