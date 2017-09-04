package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtSaleTaskOrderInfo;
import com.ichsy.hrys.entity.ArtSimpleGoodsInfo;
import com.ichsy.hrys.entity.PageResults;

import java.util.List;

/**
 * author: ihesen on 2016/11/3 15:28
 * email: hesen@ichsy.com
 */

public class ArtSaleTaskOrderResult extends BaseResponse {

    public PageResults pageResults;
    /**
     * 销售任务订单实体
     */
    public List<ArtSaleTaskOrderInfo> artSaleTaskOrderInfo;

    /**
     * 商品简讯
     */
    public ArtSimpleGoodsInfo artTaskGoodsInfo;
}
