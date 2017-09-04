package com.ichsy.hrys.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2016/12/8 15:16
 * email: hesen@ichsy.com
 */

public class ArtMaterialContent {

    /**
     * 素材描述
     */
    public String descriptionContent;
    /**
     * 主图url
     */
    public String mianPicUrl;
    /**
     * 资源集合
     */
    public List<ArtPic> picMaterialList = new ArrayList<>();
    /**
     * 分享信息实体
     */
    public ArtShareModel shareModel = new ArtShareModel();


}
