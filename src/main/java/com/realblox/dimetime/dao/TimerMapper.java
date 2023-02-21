package com.realblox.dimetime.dao;

import com.realblox.dimetime.model.TimerVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface TimerMapper {
    TimerVO getUserRecentTimerInfo(String user_id);
    TimerVO getUserTimerInfo(String timer_idx);

    int insTimerInfo(TimerVO timerVO);
    int insUserTimerItem(HashMap hashMap);
    int updateStartTimerInfo(TimerVO timerVO);
    int updateEndTimerInfo(TimerVO timerVO);
}