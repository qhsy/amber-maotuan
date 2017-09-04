package com.ichsy.hrys.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 达人服务报价实体
 * author: ihesen on 2017/3/9 16:10
 * email: hesen@ichsy.com
 */

public class ArtUserOfferInfo {

    public ArtPageResults pageresults = new ArtPageResults();
    /**
     * 达人报价列表
     */
    public List<ArtUserOffer> userOfferList = new ArrayList();
}
