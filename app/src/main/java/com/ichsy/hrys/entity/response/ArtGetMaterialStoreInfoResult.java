package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtMaterialInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2016/12/8 15:13
 * email: hesen@ichsy.com
 */

public class ArtGetMaterialStoreInfoResult extends BaseResponse {

    /**
     * 商品编号字段（用于相关文字，内容列表数据获取）
     */
    public String productCode = "";
    public List<ArtMaterialInfo> materialInfoList = new ArrayList<>();
}
