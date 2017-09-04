package com.ichsy.hrys.entity;


import com.ichsy.hrys.entity.baserecyclerview.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2016/12/8 15:14
 * email: hesen@ichsy.com
 */

public class ArtMaterialInfo extends MultiItemEntity {

    public ArtMaterialInfo(int itemType) {
        super(itemType);
    }

    public static final int PORDUCT_TYPE = 0;
    public static final int POST_TYPE = PORDUCT_TYPE + 1;
    public static final int MATERIAL_TYPE = POST_TYPE + 1;
    public static final int PAY_CODE_TYPE = MATERIAL_TYPE + 1;
    public static final int MATERIAL_NOTITLE_TYPE = PAY_CODE_TYPE + 1;

    /**
     * 是否有多条
     */
    public boolean hasMore;
    /**
     * 素材code
     */
    public String materialCode;
    /**
     * 素材内容实体
     */
    public List<ArtMaterialContent> materialInfoList = new ArrayList<>();
    /**
     * 素材类型0:商品 1：文章 2：素材内容  3：付款码
     */
    public String materialType;


}
