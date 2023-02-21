package com.realblox.dimetime.dao;

import com.realblox.dimetime.model.AccountVO;
import com.realblox.dimetime.model.NftVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AccountMapper {
    AccountVO getLoginInfo(AccountVO accountVO);
    AccountVO getAccountInfo(AccountVO accountVO);
    AccountVO getAccountInfoById(String user_id);

    int insertAccountInfo(AccountVO accountVO);
    int updateAccountToken(AccountVO accountVO);
    int updateUserPot(AccountVO accountVO);
    int updateUserDtc(AccountVO accountVO);
    int updateUserSlot(AccountVO accountVO);
}