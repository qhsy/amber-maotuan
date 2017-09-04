package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.BankCardInfoEntity;
import com.ichsy.hrys.entity.IdentifyCardInfoEntity;

/**
 * 功能：提现界面获取信息
 * ＊创建者：赵然 on 16/9/19 16:48
 * ＊
 */
public class GetWithdrawDeaultResponseEntity extends BaseResponse {
//    artBankcardInformation (ArtBankcardInformation, optional),
//    artDocumentInformation (ArtDocumentInformation, optional),
//    cashWithdrawalAmount (number, optional),
    /**
     * 默认银行卡信息
     */
    private BankCardInfoEntity artBankcardInformation;
    /**
     * 证件信息
     */
    private IdentifyCardInfoEntity artDocumentInformation;
    /**
     * 可提现金额
     */
    private double cashWithdrawalAmount;
    /**
     * 当前是否有提现任务
     */
    private boolean presentExist;

    public BankCardInfoEntity getArtBankcardInformation() {
        return artBankcardInformation;
    }

    public void setArtBankcardInformation(BankCardInfoEntity artBankcardInformation) {
        this.artBankcardInformation = artBankcardInformation;
    }

    public IdentifyCardInfoEntity getArtDocumentInformation() {
        return artDocumentInformation;
    }

    public void setArtDocumentInformation(IdentifyCardInfoEntity artDocumentInformation) {
        this.artDocumentInformation = artDocumentInformation;
    }

    public double getCashWithdrawalAmount() {
        return cashWithdrawalAmount;
    }

    public void setCashWithdrawalAmount(double cashWithdrawalAmount) {
        this.cashWithdrawalAmount = cashWithdrawalAmount;
    }

    public boolean isPresentExist() {
        return presentExist;
    }

    public void setPresentExist(boolean presentExist) {
        this.presentExist = presentExist;
    }
}
