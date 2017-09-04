package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtPageResults;
import com.ichsy.hrys.entity.ArtPopTaskInfo;
import com.ichsy.hrys.entity.ArtSimpleUserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2017/1/9 15:08
 * email: hesen@ichsy.com
 */

public class ArtExpertUserPageResult extends BaseResponse {

    /**
     * 轮播红人实体
     */
    public List<ArtPopTaskInfo> popUserList = new ArrayList<>();
    /**
     * 今日红人实体
     */
    public List<ArtSimpleUserInfo> todayExpertUserList = new ArrayList<>();
    /**
     * 更多红人实体
     */
    public List<ArtSimpleUserInfo> moreExpertUserList = new ArrayList<>();
    /**
     * 分页
     */
    public ArtPageResults pageResults = new ArtPageResults();

}
