package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.BankCardInfoEntity;
import com.ichsy.hrys.entity.IdentifyCardInfoEntity;

/**
 * 功能：提现请求实体
 * ＊创建者：赵然 on 16/9/19 16:37
 * ＊
 */
public class WithDrawRequestEntity extends  BaseRequest {

//    "bindingDocuments": "string",
//            "cashWithdrawalAmount": 0,
//            "collectionAccount": "string",
//            "withdrawalAmount": 0,

    /**
     * 绑定的证件信息
     */
    private IdentifyCardInfoEntity bindingDocuments;
    /**
     *可提现金额
     */
    public double cashWithdrawalAmount;
    /**
     * 提现金额
     */
    public double withdrawalAmount;
    /**
     * 收款账户
     */
    private BankCardInfoEntity collectionAccount;

    public IdentifyCardInfoEntity getBindingDocuments() {
        return bindingDocuments;
    }

    public void setBindingDocuments(IdentifyCardInfoEntity bindingDocuments) {
        this.bindingDocuments = bindingDocuments;
    }

    public double getCashWithdrawalAmount() {
        return cashWithdrawalAmount;
    }

    public void setCashWithdrawalAmount(double cashWithdrawalAmount) {
        this.cashWithdrawalAmount = cashWithdrawalAmount;
    }

    public BankCardInfoEntity getCollectionAccount() {
        return collectionAccount;
    }

    public void setCollectionAccount(BankCardInfoEntity collectionAccount) {
        this.collectionAccount = collectionAccount;
    }
}
