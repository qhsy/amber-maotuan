package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.UserImageInfoEntity;

import java.util.List;

/**
 * 功能：修改用户信息的请求实体
 * ＊创建者：赵然 on 16/5/24 12:28
 * ＊
 */
public class ModifyUserInfoRequestEntity extends BaseRequest{

    public ArtUserInfo userInfo = new ArtUserInfo();

    /**
     * 完善资料类型 0完善基本资料，1完善个人形象，2全部
     */
    public int perfectType;
    /**
     * 个人形象资料
     */
    public UserImageInfoEntity artPersonalImage;

    public List<String> domainLabel;
    public ArtUserInfo getUserInfo() {
        return userInfo;
    }

}
