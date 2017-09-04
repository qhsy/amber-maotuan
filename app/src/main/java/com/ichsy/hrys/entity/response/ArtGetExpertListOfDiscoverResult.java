package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtPageResults;
import com.ichsy.hrys.entity.ArtUserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2016/5/17 18:00
 * email: hesen@ichsy.com
 */
public class ArtGetExpertListOfDiscoverResult extends BaseResponse {

    /**
     * 分页信息
     */
    public ArtPageResults pageResults = new ArtPageResults();
    /**
     * 用户消息
     */
    public List<ArtUserInfo> userList = new ArrayList<>();
}
