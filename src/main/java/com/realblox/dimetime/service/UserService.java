package com.realblox.dimetime.service;

import com.realblox.dimetime.dao.PatternMapper;
import com.realblox.dimetime.dao.UserMapper;
import com.realblox.dimetime.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    Logger _logger = LoggerFactory.getLogger( UserService.class );

    @Autowired
    UserMapper userMapper;

    public List<UserRiskVO> getUserList(UserRiskVO userRiskVO) { return userMapper.getUserList(userRiskVO); }
}