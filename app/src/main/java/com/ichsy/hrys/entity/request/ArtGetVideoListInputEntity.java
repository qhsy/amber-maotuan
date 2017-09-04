package com.ichsy.hrys.entity.request;

/**
 * 首页视频列表
 */

public class ArtGetVideoListInputEntity extends OnlyPageRequestEntity {
    /**
     * 类型：1:推荐 2:最新 3:最热 ,
     */
    public String type;
}
