package com.realblox.dimetime.service;

import com.realblox.dimetime.dao.AccountMapper;
import com.realblox.dimetime.dao.NftMapper;
import com.realblox.dimetime.model.AccountVO;
import com.realblox.dimetime.model.NftVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountService {
    Logger _logger = LoggerFactory.getLogger( AccountService.class );

    @Autowired
    AccountMapper accountMapper;

    public AccountVO getLoginInfo(AccountVO accountVO) { return accountMapper.getLoginInfo(accountVO); }

    public AccountVO getAccountInfo(AccountVO accountVO) { return accountMapper.getAccountInfo(accountVO); }
    public AccountVO getAccountInfoById(String user_id) { return accountMapper.getAccountInfoById(user_id); }

    public int insertAccountInfo(AccountVO accountVO) {
        return accountMapper.insertAccountInfo(accountVO);
    }

    public int updateAccountToken(AccountVO accountVO) {
        return accountMapper.updateAccountToken(accountVO);
    }
    public int updateUserPot(AccountVO accountVO) {
        return accountMapper.updateUserPot(accountVO);
    }
    public int updateUserDtc(AccountVO accountVO) {
        return accountMapper.updateUserDtc(accountVO);
    }
    public int updateUserSlot(AccountVO accountVO) {
        return accountMapper.updateUserSlot(accountVO);
    }
}