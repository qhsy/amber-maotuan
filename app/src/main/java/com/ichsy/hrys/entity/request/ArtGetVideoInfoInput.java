package com.ichsy.hrys.entity.request;

import com.ichsy.hrys.entity.PageOption;

/**
 * Created by zhu on 2017/8/26.
 */

public class ArtGetVideoInfoInput extends BaseRequest{
    /** 分页*/
    public PageOption pageOption = new PageOption();

    public String videoNumber;
}
