package com.ichsy.hrys.entity.response;

import com.ichsy.hrys.entity.ArtPageResults;
import com.ichsy.hrys.entity.ArtVideoNotifactionMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * author: zhu on 2017/8/25 15:46
 * email: mackkilled@gmail.com
 */
// 获取消息列表
public class ArtGetVideoNotifactionMessagResult extends BaseResponse{
    /**
     * 分页
     */
    public ArtPageResults pageResults = new ArtPageResults();

    /**
     * 消息列表
     */
    public List<ArtVideoNotifactionMessage> videoNotifactionMessageList = new ArrayList<>();
}
