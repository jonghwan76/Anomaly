package com.realblox.dimetime.service;

import com.realblox.dimetime.dao.HistoryMapper;
import com.realblox.dimetime.dao.ItemMapper;
import com.realblox.dimetime.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HistoryService {
    Logger _logger = LoggerFactory.getLogger( HistoryService.class );

    @Autowired
    HistoryMapper historyMapper;

    public int insUserPotHistory(UserPotHistoryVO userPotHistoryVO) { return historyMapper.insUserPotHistory(userPotHistoryVO); }

    public int insUserDtcHistory(UserDtcHistoryVO userDtcHistoryVO) { return historyMapper.insUserDtcHistory(userDtcHistoryVO); }
    public int insUserFocusTimeHistory(UserTimeHistoryVO userTimeHistoryVO) { return historyMapper.insUserFocusTimeHistory(userTimeHistoryVO); }

    public int insItemHistory(ItemHistoryVO itemHistoryVO) { return historyMapper.insItemHistory(itemHistoryVO); }

    public int insRepairHistory(RepairHistoryVO repairHistoryVO) { return historyMapper.insRepairHistory(repairHistoryVO); }
}