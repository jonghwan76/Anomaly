package com.realblox.dimetime.service;

import com.realblox.dimetime.dao.PatternMapper;
import com.realblox.dimetime.model.PatternSearchVO;
import com.realblox.dimetime.model.PatternVO;
import com.realblox.dimetime.model.RiskVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatternService {
    Logger _logger = LoggerFactory.getLogger( PatternService.class );

    @Autowired
    PatternMapper patternMapper;

    public List<PatternVO> getPatternList(PatternSearchVO patternSearchVO) { return patternMapper.getPatternList(patternSearchVO); }

    public int insertPattern(PatternVO patternVO) {
        return patternMapper.insertPattern(patternVO);
    }
    public int insertHighAnomaly(RiskVO riskVO) {
        return patternMapper.insertHighAnomaly(riskVO);
    }

    public int insertMidAnomaly(RiskVO riskVO) {
        return patternMapper.insertMidAnomaly(riskVO);
    }
}