package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtMaterialInfo;
import com.ichsy.hrys.entity.ArtPageResults;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2016/12/8 17:32
 * email: hesen@ichsy.com
 */

public class ArtGetMaterialContentResult extends BaseResponse {

    /**
     * 素材实体
     */
    public List<ArtMaterialInfo> materialInfoList = new ArrayList<>();
    /**
     * 分页信息 ,
     */
    public ArtPageResults pageResults = new ArtPageResults();


}
