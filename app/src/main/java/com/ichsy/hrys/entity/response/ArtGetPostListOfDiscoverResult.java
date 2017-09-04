package com.ichsy.hrys.entity.response;

import com.ichsy.hrys.entity.ArtPageResults;
import com.ichsy.hrys.entity.ArtPostTypeInfo;
import com.ichsy.hrys.entity.ArtSimplePost;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2016/5/18 12:17
 * email: hesen@ichsy.com
 */
public class ArtGetPostListOfDiscoverResult extends BaseResponse{

    public List<ArtPostTypeInfo> categoryList = new ArrayList<>();
    public List<ArtSimplePost> homeContentList = new ArrayList<>();
    public ArtPageResults pageResults = new ArtPageResults();
}
