package com.realblox.dimetime.service;

import com.realblox.dimetime.dao.NftMapper;
import com.realblox.dimetime.dao.StatMapper;
import com.realblox.dimetime.model.NftVO;
import com.realblox.dimetime.model.RandomVO;
import com.realblox.dimetime.model.UserNftVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
@Transactional
public class StatService {
    Logger _logger = LoggerFactory.getLogger( StatService.class );

    @Autowired
    StatMapper statMapper;

    public HashMap getResultTimer(String user_id) { return statMapper.getResultTimer(user_id); }
    public List<HashMap> getResultCateogry(String user_id) { return statMapper.getResultCateogry(user_id); }
    public List<HashMap> getDayDTC(String user_id) { return statMapper.getDayDTC(user_id); }
    public List<HashMap> getMonthDTC(String user_id) { return statMapper.getMonthDTC(user_id); }
    public List<HashMap> getYearDTC(String user_id) { return statMapper.getYearDTC(user_id); }
    public List<HashMap> getDayPOT(String user_id) { return statMapper.getDayPOT(user_id); }
    public List<HashMap> getMonthPOT(String user_id) { return statMapper.getMonthPOT(user_id); }
    public List<HashMap> getYearPOT(String user_id) { return statMapper.getYearPOT(user_id); }
    public List<HashMap> getFavoriteNFT(String user_id) { return statMapper.getFavoriteNFT(user_id); }


}