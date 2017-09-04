package com.ichsy.hrys.entity;


import com.ichsy.hrys.entity.baserecyclerview.MultiItemEntity;
import com.ichsy.hrys.entity.response.ArtGetPersonalInfoResult;

/**
 * 个人主页 实体
 * author: ihesen on 2017/1/6 14:29
 * email: hesen@ichsy.com
 */

public class PersonalInfoItemEntity extends MultiItemEntity {

    /**
     * 个人形象
     */
    public static final int PERSONAL_INFO = 0;
    /**
     * 影响力
     */
    public static final int PERSONAL_INFLUENCE = PERSONAL_INFO + 1;
    /**
     * 帖子标题
     */
    public static final int PERSONAL_POST_TITLE = PERSONAL_INFLUENCE + 1;
    /**
     * 帖子
     */
    public static final int PERSONAL_POST = PERSONAL_POST_TITLE + 1;
    /**
     * 空数据
     */
    public static final int PERSONAL_NODATA = PERSONAL_POST + 1;
    /**
     * 服务报价
     */
    public static final int PERSONAL_SERVICE_PRICE = PERSONAL_NODATA + 1;
    /**
     * 个人介绍
     */
    public static final int PERSONAL_INSTRUCT= PERSONAL_SERVICE_PRICE + 1;



    public ArtGetPersonalInfoResult result = new ArtGetPersonalInfoResult();

    /**
     * 帖子item使用
     */
    public ArtSimplePost artSimplePost = new ArtSimplePost();

    /**
     * 服务报价
     */
    public ArtUserOfferInfo artUserOfferInfo = new ArtUserOfferInfo();

    public PersonalInfoItemEntity(int itemType) {
        super(itemType);
    }
}
