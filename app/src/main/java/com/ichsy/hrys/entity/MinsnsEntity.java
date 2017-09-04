package com.ichsy.hrys.entity;

import android.text.TextUtils;

/**
 * 功能：微公社账户信息
 * ＊创建者：赵然 on 16/5/23 21:42
 * ＊
 */
public class MinsnsEntity {

    /**
     * 账户余额
     */
    private String accountMoney;
    /**
     * 二维码信息
     */
    private String qrCodeUrl;
    /**
     * 返现金额
     */
    private String returnMoney;
    /**
     * 交易额
     */
    private String tradingVolume;

    /**
     * 提现链接
     */

    private String withdrawalsUrl;
    /**
     *累计提现金额
     */
    private String cumulativeWithdrawa;

    /**
     *预返利金额
     */
    private String preRebateBalance;


    public String getAccountMoney() {
        if (TextUtils.isEmpty(accountMoney)) accountMoney = "0.00";
        return accountMoney;
    }

    public void setAccountMoney(String accountMoney) {
        this.accountMoney = accountMoney;
    }

    public String getQrCodeUrl() {
        if (null  == qrCodeUrl) qrCodeUrl = "";
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public String getReturnMoney() {
        if (TextUtils.isEmpty(returnMoney)) returnMoney = "0.00";

        return returnMoney;
    }

    public void setReturnMoney(String returnMoney) {

        this.returnMoney = returnMoney;
    }

    public String getTradingVolume() {
        if (TextUtils.isEmpty(tradingVolume)) tradingVolume = "0.00";
        return tradingVolume;
    }

    public void setTradingVolume(String tradingVolume) {
        this.tradingVolume = tradingVolume;
    }

    public String getWithdrawalsUrl() {
        if (null  == withdrawalsUrl) withdrawalsUrl = "";
        return withdrawalsUrl;
    }

    public void setWithdrawalsUrl(String withdrawalsUrl) {
        this.withdrawalsUrl = withdrawalsUrl;
    }

    public String getCumulativeWithdrawa() {
        if (TextUtils.isEmpty(cumulativeWithdrawa)){
            cumulativeWithdrawa = "0.00";
        }
        return cumulativeWithdrawa;
    }

    public void setCumulativeWithdrawa(String cumulativeWithdrawa) {
        this.cumulativeWithdrawa = cumulativeWithdrawa;
    }

    public String getPreRebateBalance() {
        if (TextUtils.isEmpty(preRebateBalance)){
            preRebateBalance = "0.00";
        }
        return preRebateBalance;
    }

    public void setPreRebateBalance(String preRebateBalance) {
        this.preRebateBalance = preRebateBalance;
    }
}
