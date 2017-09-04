package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.AdvertiseMent;
import com.ichsy.hrys.entity.HomePageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2016/5/17 14:18
 * email: hesen@ichsy.com
 */
public class ApiHomePageResult extends BaseResponse {
    /**
     * 首页顶部广告位
     */
    public AdvertiseMent adv = new AdvertiseMent();
    /**
     * 首页展示实体数组
     */
    public List<HomePageModel> list = new ArrayList<>();
    /**
     * 是否还有下一页
     */
    public boolean nextflag;
}
