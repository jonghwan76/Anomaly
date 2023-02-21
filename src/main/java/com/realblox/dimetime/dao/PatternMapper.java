package com.realblox.dimetime.dao;

import com.realblox.dimetime.model.PatternSearchVO;
import com.realblox.dimetime.model.PatternVO;
import com.realblox.dimetime.model.UserTimeHistoryVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PatternMapper {
    List<PatternVO> getPatternList(PatternSearchVO patternSearchVO);
    List<HashMap> getUserDTCHourlyList(HashMap hashMap);
    List<HashMap> getUserTimerRewardHistory(HashMap hashMap);
    List<HashMap> getUserDTCDailyList(HashMap hashMap);
    List<HashMap> getUserDTCRawList(HashMap hashMap);

    int insertPattern(PatternVO patternVO);
    int updateTimerVariation(UserTimeHistoryVO userTimeHistoryVO);
}