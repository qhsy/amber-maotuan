package com.ichsy.hrys.entity;

import java.io.Serializable;

/**
 * 推广要求实体
 * author: ihesen on 2016/11/30 10:59
 * email: hesen@ichsy.com
 */

public class ArtTaskClaimInfo  implements Serializable {
    /**
     * 要求文案
     */
    private String claimContent;
    /**
     * 要求名称
     */
    private String claimName;
    /**
     * 推广要求icon
     */
    private String claimPic;

    public String getClaimContent() {
        return claimContent;
    }

    public void setClaimContent(String claimContent) {
        this.claimContent = claimContent;
    }

    public String getClaimName() {
        return claimName;
    }

    public void setClaimName(String claimName) {
        this.claimName = claimName;
    }

    public String getClaimPic() {
        if(claimPic == null){
            claimPic = "";
        }
        return claimPic;
    }

    public void setClaimPic(String claimPic) {
        this.claimPic = claimPic;
    }
}
