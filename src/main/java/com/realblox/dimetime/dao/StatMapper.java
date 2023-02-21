package com.realblox.dimetime.dao;

import com.realblox.dimetime.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface StatMapper {
    HashMap getResultTimer(String user_id);
    List<HashMap> getResultCateogry(String user_id);

    List<HashMap> getDayDTC(String user_id);
    List<HashMap> getMonthDTC(String user_id);
    List<HashMap> getYearDTC(String user_id);
    List<HashMap> getDayPOT(String user_id);
    List<HashMap> getMonthPOT(String user_id);
    List<HashMap> getYearPOT(String user_id);
    List<HashMap> getFavoriteNFT(String user_id);

}