package com.ichsy.hrys.entity.response;

/**
 * 功能：获取验证码接口返回实体
 * ＊创建者：赵然 on 16/5/25 19:29
 * ＊
 */
public class GetVerifyCodeResponseEntity extends BaseResponse{
    private String verifyNumber;

    public String getVerifyNumber() {
        if (null == verifyNumber){
            verifyNumber = "";
        }
        return verifyNumber;
    }

    public void setVerifyNumber(String verifyNumber) {
        this.verifyNumber = verifyNumber;
    }
}
