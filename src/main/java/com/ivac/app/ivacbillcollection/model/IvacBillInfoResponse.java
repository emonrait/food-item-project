package com.ivac.app.ivacbillcollection.model;

public class IvacBillInfoResponse {


    public String outCode = "";
    public String outMessage = "";
    public String ivac = "";
    public String billAmount = "";
    public String totalAmount = "";
    public String lid = "";
    public String transactionId = "";


    public String status = "";
    public String code = "";

    public String utilityAuthKey = "";
    public String utilitySecretKey = "";


    public String getOutCode() {
        return outCode;
    }

    public void setOutCode(String outCode) {
        this.outCode = outCode;
    }

    public String getOutMessage() {
        return outMessage;
    }

    public void setOutMessage(String outMessage) {
        this.outMessage = outMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUtilityAuthKey() {
        return utilityAuthKey;
    }

    public void setUtilityAuthKey(String utilityAuthKey) {
        this.utilityAuthKey = utilityAuthKey;
    }

    public String getUtilitySecretKey() {
        return utilitySecretKey;
    }

    public void setUtilitySecretKey(String utilitySecretKey) {
        this.utilitySecretKey = utilitySecretKey;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getIvac() {
        return ivac;
    }

    public void setIvac(String ivac) {
        this.ivac = ivac;
    }
}
