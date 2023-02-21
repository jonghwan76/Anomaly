package com.realblox.dimetime.service;

import com.realblox.dimetime.dao.NftMapper;
import com.realblox.dimetime.model.NftVO;
import com.realblox.dimetime.model.PatternVO;
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
public class NftService {
    Logger _logger = LoggerFactory.getLogger( NftService.class );

    @Autowired
    NftMapper nftMapper;

    public List<NftVO> getUserNftList(String user_id) { return nftMapper.getUserNftList(user_id); }

    public NftVO getNftInfo(String nft_idx) { return nftMapper.getNftInfo(nft_idx); }

    public HashMap getNftLevelUpInfo(String nft_idx) { return nftMapper.getNftLevelUpInfo(nft_idx); }

    public String getNftBreedingInfo(HashMap hashMap) { return nftMapper.getNftBreedingInfo(hashMap); }

    public RandomVO getNftBaseRandomInfo(String nft_idx) { return nftMapper.getNftBaseRandomInfo(nft_idx); }
    public RandomVO getNftGradeInfo(String grade) { return nftMapper.getNftGradeInfo(grade); }

    public RandomVO getNftLevelUpRandomInfo(String nft_idx) { return nftMapper.getNftLevelUpRandomInfo(nft_idx); }

    public NftVO getNftEditionInfo(String nft_edition) { return nftMapper.getNftEditionInfo(nft_edition); }

    public int insNftInfo(NftVO nftVO) {
        return nftMapper.insNftInfo(nftVO);
    }
    public int insUserNftInfo(UserNftVO userNftVO) {
        return nftMapper.insUserNftInfo(userNftVO);
    }

    public int updateNftInfo(NftVO nftVO) {
        return nftMapper.updateNftInfo(nftVO);
    }
    public int updateNftRandomInfo(RandomVO randomVO) {
        return nftMapper.updateNftRandomInfo(randomVO);
    }
    public int updateNftDynamicInfo(RandomVO randomVO) {
        return nftMapper.updateNftDynamicInfo(randomVO);
    }

    public int updateNftSlot(UserNftVO userNftVO) {
        return nftMapper.updateNftSlot(userNftVO);
    }
}