package com.ichsy.hrys.entity.response;

import com.ichsy.hrys.entity.OrderInfoEntity;
import com.ichsy.hrys.entity.PageResults;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：获取订单列表接口 返回实体
 * ＊创建者：赵然 on 16/5/24 15:41
 * ＊
 */
public class GetOrderListResponseEntity extends  BaseResponse {
    public List<OrderInfoEntity> orderInfoList = new ArrayList<>();

    public PageResults pageResults =  new PageResults();
}
