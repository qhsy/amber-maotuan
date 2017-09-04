package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtGoodsInfo;
import com.ichsy.hrys.entity.ArtShareModel;
import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.GoodsAddress;

/**
 * 帖子详情实体（H5页）
 * author: ihesen on 2016/5/11 11:51
 * email: hesen@ichsy.com
 */
public class WebPostDetailResponseEntity extends BaseResponse {

    /**
     * 发帖人信息
     */
    public ArtUserInfo postUserInfo;
    public long postTime;
    public ArtShareModel shareModel = new ArtShareModel();
    public ArtGoodsInfo goodsInfo = new ArtGoodsInfo();
    public boolean collected;
    public int attentionStatus;
    public int commentNum;

    /**
     * 默认地址
     */
    public GoodsAddress defaultAdress;
    /**
     * 页面链接
     */
    public String postDetailUrl;
    /**帖子绑定的任务编号，用于统计*/
    public String taskCode;
}
