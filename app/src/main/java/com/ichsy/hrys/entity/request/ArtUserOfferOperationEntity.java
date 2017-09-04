package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.ArtUserOffer;

/**
 * author: ihesen on 2017/3/9 18:21
 * email: hesen@ichsy.com
 */

public class ArtUserOfferOperationEntity extends BaseRequest {

    /**
     * 操作类型 0:添加 1:修改 2:删除 ,
     */
    public String operateType;
    public ArtUserOffer userOffer = new ArtUserOffer();
}
