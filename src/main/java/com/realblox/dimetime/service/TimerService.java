package com.realblox.dimetime.service;

import com.realblox.dimetime.dao.TimerMapper;
import com.realblox.dimetime.model.NftVO;
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
public class TimerService {
    Logger _logger = LoggerFactory.getLogger( TimerService.class );

    @Autowired
    TimerMapper timerMapper;

    public TimerVO getUserRecentTimerInfo(String user_id) { return timerMapper.getUserRecentTimerInfo(user_id); }

    public TimerVO getUserTimerInfo(String timer_idx) { return timerMapper.getUserTimerInfo(timer_idx); }

    public int insTimerInfo(TimerVO timerVO) {
        return timerMapper.insTimerInfo(timerVO);
    }

    public int insUserTimerItem(HashMap hashMap) {
        return timerMapper.insUserTimerItem(hashMap);
    }

    public int updateStartTimerInfo(TimerVO timerVO) {
        return timerMapper.updateStartTimerInfo(timerVO);
    }

    public int updateEndTimerInfo(TimerVO timerVO) {
        return timerMapper.updateEndTimerInfo(timerVO);
    }
}