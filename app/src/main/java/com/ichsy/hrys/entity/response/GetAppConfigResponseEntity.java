package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.AppConfigEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能： 获取配置信息的请求返回实体
 * ＊创建者：赵然 on 16/5/29 22:52
 * ＊
 */
public class GetAppConfigResponseEntity extends  BaseResponse{
    private List<AppConfigEntity> artConfiGuration;

    public List<AppConfigEntity> getArtConfiGuration() {
        if (null == artConfiGuration) artConfiGuration = new ArrayList<>();
        return artConfiGuration;
    }

    public void setArtConfiGuration(List<AppConfigEntity> artConfiGuration) {
        this.artConfiGuration = artConfiGuration;
    }
}
