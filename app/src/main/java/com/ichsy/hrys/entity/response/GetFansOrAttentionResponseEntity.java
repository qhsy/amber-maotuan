package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.PageResults;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：获取粉丝或者关注的请求返回实体
 * ＊创建者：赵然 on 16/5/23 19:02
 * ＊
 */
public class GetFansOrAttentionResponseEntity extends  BaseResponse{
    /**
     *
     */
    public PageResults pageResults;
    /**
     * 用户信息列表
     */
   public List<ArtUserInfo> userList = new ArrayList<>();
}
