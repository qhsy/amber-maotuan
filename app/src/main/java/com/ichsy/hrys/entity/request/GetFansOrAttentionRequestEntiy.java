package com.ichsy.hrys.entity.request;

import com.ichsy.hrys.entity.PageOption;

/**
 * 功能： 获取粉丝或者关注的接口的请求实体
 * ＊创建者：赵然 on 16/5/23 18:58
 * ＊
 */
public class GetFansOrAttentionRequestEntiy extends  BaseRequest {

    /**
     * {
     "operationType": 0,
     "pageOption": {
     "itemCount": 0,
     "pageNum": 0
     },
     "zoo": {
     "key": "tesetkey",
     "token": " "
     }
     }
     */
    /**
     * 请求类型 0：粉丝 1：关注
     */
    public  int operationType;
    /**
     * 分页信息
     */
    public PageOption pageOption = new PageOption();
    public String userCode;
}
