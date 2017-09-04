package com.ichsy.hrys.entity;

/**
 * 功能：银行卡信息实体
 * ＊创建者：赵然 on 16/9/18 17:11
 * ＊
 */
public class BankCardInfoEntity {
    /**
     createTime (string, optional),
     delTime (string, optional),
     flagEnable (string, optional),
     userCode (string, optional),
     */
    private String cardName;
    private String cardNumber;
    private String cardType;
    //1 为默认
    private String defaultCard;
    private String userName;
    private String userPhone;

    /**
     * 开户行信息
     */
    private String bankAccount;

    public String getCardName() {
        if (null == cardName) cardName = "";
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardType() {
        if (null == cardType) cardType = "";
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        if (null == cardType) cardType = "";
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDefaultCard() {
        if (null == defaultCard) defaultCard = "";
        return defaultCard;
    }

    public void setDefaultCard(String defaultCard) {
        this.defaultCard = defaultCard;
    }

    public String getUserName() {
        if (null == userName) userName = "";
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        if (null == userPhone) userPhone = "";
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getBankAccount() {
        if(null  == bankAccount){
            bankAccount = "";
        }
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
