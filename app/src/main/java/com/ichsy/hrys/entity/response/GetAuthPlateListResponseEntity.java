package com.ichsy.hrys.entity.response;

import com.ichsy.hrys.entity.AuthPlateEntity;

import java.util.List;

/**
 * 功能：获取可认证平台接口的返回实体
 * ＊创建者：赵然 on 2016/12/1 17:38
 * ＊
 */

public class GetAuthPlateListResponseEntity extends BaseResponse {
    /**
     * 平台信息列表
     */
    private List<AuthPlateEntity> systemDefine;

    public List<AuthPlateEntity> getSystemDefine() {
        return systemDefine;
    }

    public void setSystemDefine(List<AuthPlateEntity> systemDefine) {
        this.systemDefine = systemDefine;
    }
}
