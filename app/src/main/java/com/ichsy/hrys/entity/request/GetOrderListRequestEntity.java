package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.PageOption;

/**
 * 功能： 获取订单列表请求实体
 * ＊创建者：赵然 on 16/5/24 15:39
 * ＊
 */
public class GetOrderListRequestEntity extends  BaseRequest {
    /**
     * {
     "orderStatus": "0",
     "pageOption": {
     "itemCount": 10,
     "pageNum": 0
     },
     "zoo": {
     "key": "tesetkey",
     "token": " "
     }
     }
     */
    /**
     * 订单状态,0:全部状态,1:待付款,2:已付款,3:已发货,4:交易成功，5：交易失败 ,
     */
    public String orderStatus = "0";
    public PageOption pageOption = new PageOption();

}
