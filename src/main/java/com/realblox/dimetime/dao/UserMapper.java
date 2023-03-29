package com.realblox.dimetime.dao;

import com.realblox.dimetime.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<UserRiskVO> getUserList(UserRiskVO userRiskVO);

}