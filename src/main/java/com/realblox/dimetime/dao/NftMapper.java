package com.realblox.dimetime.dao;

import com.realblox.dimetime.model.NftVO;
import com.realblox.dimetime.model.RandomVO;
import com.realblox.dimetime.model.UserNftVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface NftMapper {
    List<NftVO> getUserNftList(String user_id);

    NftVO getNftInfo(String nft_idx);

    HashMap getNftLevelUpInfo(String nft_idx);

    String getNftBreedingInfo(HashMap hashMap);

    RandomVO getNftBaseRandomInfo(String nft_idx);
    RandomVO getNftGradeInfo(String grade);
    RandomVO getNftLevelUpRandomInfo(String nft_idx);

    NftVO getNftEditionInfo(String nft_edition);

    int insNftInfo(NftVO nftVO);
    int insUserNftInfo(UserNftVO userNftVO);

    int updateNftInfo(NftVO nftVO);
    int updateNftRandomInfo(RandomVO randomVO);
    int updateNftDynamicInfo(RandomVO randomVO);

    int updateNftSlot(UserNftVO userNftVO);
}