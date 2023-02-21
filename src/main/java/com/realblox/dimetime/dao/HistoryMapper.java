package com.realblox.dimetime.dao;

import com.realblox.dimetime.model.*;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HistoryMapper {
    int insUserPotHistory(UserPotHistoryVO userPotHistoryVO);
    int insUserDtcHistory(UserDtcHistoryVO userDtcHistoryVO);
    int insUserFocusTimeHistory(UserTimeHistoryVO userTimeHistoryVO);
    int insItemHistory(ItemHistoryVO itemHistoryVO);
    int insRepairHistory(RepairHistoryVO repairHistoryVO);
}