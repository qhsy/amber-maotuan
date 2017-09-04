package com.ichsy.hrys.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品实体
 * author: ihesen on 2016/5/10 14:02
 * email: hesen@ichsy.com
 */
public class ArtGoodsInfo {

    public String goodsCode;
    private String goodsFacePic;
    public String goodsMarketPrice;
    public List<ArtPic> goodsArtPicList = new ArrayList<>();
    public List<ArtgoodsPropertyInfoList> goodsPropertyInfoList = new ArrayList<>();
    public String goodsTittle;
    public String minGoodsPrice;
    public ArtShareModel shareModel = new ArtShareModel();
    public ArtShareModel shareModle = new ArtShareModel();
    public List<ArtSkuList> skulist = new ArrayList<>();
    /** 默认收货地址 **/
    public GoodsAddress defaultAddress;

    public String getGoodsFacePic() {
        if (null == goodsFacePic) {
            goodsFacePic = "";
        }
        return goodsFacePic;
    }

    public void setGoodsFacePic(String goodsFacePic) {
        this.goodsFacePic = goodsFacePic;
    }

    /**
     * 商品默认返利 
     */
    public String defultRebatePrice;
}
