package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.PageOption;

/**
 * 功能：获取账户明细接口的请求实体
 * ＊创建者：赵然 on 2016/11/3 16:40
 * ＊
 */

public class GetAccountDetailListRequestEntity extends BaseRequest {
    /**
     * "accountType": "出账 (dzsd4029100100310001)，入账(dzsd4029100100310002)",
     "artPageOption"
     */
    public String accountType;
    public PageOption artPageOption = new PageOption();
}
