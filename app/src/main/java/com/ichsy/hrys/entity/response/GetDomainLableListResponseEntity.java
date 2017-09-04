package com.ichsy.hrys.entity.response;

import com.ichsy.hrys.entity.ArtMasterFieldVo;
import com.ichsy.hrys.entity.MarsterFieldEntity;

import java.util.ArrayList;
import java.util.List;

;

/**
 * 功能：获取擅长领域标签集合的请求返回实体
 * ＊创建者：赵然 on 16/8/18 16:39
 * ＊
 */
public class GetDomainLableListResponseEntity  extends BaseResponse{

    private List<MarsterFieldEntity> artMasterField;

    public List<MarsterFieldEntity> getArtMasterField() {
        return artMasterField;
    }

    public void setArtMasterField(List<MarsterFieldEntity> artMasterField) {
        this.artMasterField = artMasterField;
    }

    public List<ArtMasterFieldVo> list = new ArrayList();

}
