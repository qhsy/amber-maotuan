package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.GoodsAddress;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：获取收货地址的返回实体
 * ＊创建者：赵然 on 16/5/23 13:20
 * ＊
 */
public class GetGoodsAdressResponseEntiy extends  BaseResponse{
    /**
     * 收货地址列表  最多五条
     */

    public List<GoodsAddress> goodsAddressList = new ArrayList<>();


}
