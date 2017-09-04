package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtPageResults;
import com.ichsy.hrys.entity.ArtUserOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2017/3/9 18:20
 * email: hesen@ichsy.com
 */

public class ArtGetUserOfferListResult extends BaseResponse {

    /**
     * 分页
     */
    public ArtPageResults pageresults = new ArtPageResults();
    /**
     * 达人报价列表
     */
    public List<ArtUserOffer> userOfferList = new ArrayList<>();

}
