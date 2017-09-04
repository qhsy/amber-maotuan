package com.ichsy.hrys.entity.response;

import com.ichsy.hrys.entity.NotifactionMessageEntity;
import com.ichsy.hrys.entity.PageResults;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：获取消息列表的请求返回实体
 * ＊创建者：赵然 on 16/5/23 20:16
 * ＊
 */
public class GetNotifactionResponseEntity extends  BaseResponse {

    /**
     * {
     "notifactionMessageList": [
     {
     "notifactionContent": "string",
     "notifactionExtral": "string",
     "notifactionImgUrl": "string",
     "notifactionTittle": "string"
     }
     ],
     "pageResults": {
     "isMore": true
     },
     }
     */
    public PageResults pageResults = new PageResults();
    public List<NotifactionMessageEntity> notifactionMessageList = new ArrayList<>();
}
