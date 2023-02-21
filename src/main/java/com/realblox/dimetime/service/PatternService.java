package com.realblox.dimetime.service;

import com.realblox.dimetime.dao.PatternMapper;
import com.realblox.dimetime.model.PatternSearchVO;
import com.realblox.dimetime.model.PatternVO;
import com.realblox.dimetime.model.UserTimeHistoryVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class PatternService {
    Logger _logger = LoggerFactory.getLogger( PatternService.class );

    @Autowired
    PatternMapper patternMapper;

    public List<PatternVO> getPatternList(PatternSearchVO patternSearchVO) { return patternMapper.getPatternList(patternSearchVO); }
    public List<HashMap> getUserTimerRewardHistory(HashMap hashMap) { return patternMapper.getUserTimerRewardHistory(hashMap); }
    public List<HashMap> getUserDTCHourlyList(HashMap hashMap) { return patternMapper.getUserDTCHourlyList(hashMap); }
    public List<HashMap> getUserDTCDailyList(HashMap hashMap) { return patternMapper.getUserDTCDailyList(hashMap); }
    public List<HashMap> getUserDTCRawList(HashMap hashMap) { return patternMapper.getUserDTCRawList(hashMap); }

    public int insertPattern(PatternVO patternVO) {
        return patternMapper.insertPattern(patternVO);
    }
    public int updateTimerVariation(UserTimeHistoryVO userTimeHistoryVO) {
        return patternMapper.updateTimerVariation(userTimeHistoryVO);
    }
}