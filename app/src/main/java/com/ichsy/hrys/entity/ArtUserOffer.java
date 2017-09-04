package com.ichsy.hrys.entity;

import java.io.Serializable;

/**
 * author: ihesen on 2017/3/9 16:13
 * email: hesen@ichsy.com
 */

public class ArtUserOffer implements Serializable {

    /**
     * 服务报价上限
     */
    public String endOffer;
    /**
     * 报价编号
     */
    public String offerCode;
    /**
     * 服务说明
     */
    public String serviceExplain;
    /**
     * 服务类型
     * dzsd4029100100390001:线上推广
     * dzsd4029100100390002:互动直播
     * dzsd4029100100390003:平面拍摄
     * dzsd4029100100390004:线下活动 ,
     */
    public String serviceType;

    public String serviceTypeName;

    /**
     * 服务报价下限
     */
    public String startOffer;
    /**
     * 用户编号
     */
    public String userCode;

    public boolean showEditLayer = false;

}
