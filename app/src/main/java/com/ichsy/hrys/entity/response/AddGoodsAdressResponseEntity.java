package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.GoodsAddress;

/**
 * 功能： 添加收货地址的请求返回实体
 * ＊创建者：赵然 on 16/5/23 14:24
 * ＊
 */
public class AddGoodsAdressResponseEntity extends  BaseResponse{

    public GoodsAddress goodsAddress = new GoodsAddress();
}
