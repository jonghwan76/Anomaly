package com.realblox.dimetime.dao;

import com.realblox.dimetime.model.PatternSearchVO;
import com.realblox.dimetime.model.PatternVO;
import com.realblox.dimetime.model.RiskOrderVO;
import com.realblox.dimetime.model.RiskVO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface PatternMapper {
    List<PatternVO> getPatternList(PatternSearchVO patternSearchVO);
    int insertPattern(PatternVO patternVO);
    int insertHighAnomaly(RiskVO riskVO);
    int insertMidAnomaly(RiskVO riskVO);
    int deleteRiskOrder(String stat_dt);
    int insertRiskOrder(RiskOrderVO riskOrderVO);
}