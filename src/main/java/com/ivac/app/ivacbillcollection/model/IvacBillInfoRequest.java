package com.ivac.app.ivacbillcollection.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class IvacBillInfoRequest {
    //Token
    String authKey = "";
    String stkCode = "";

    //Ivac Info
    public String utilityAuthKey = "";
    public String utilitySecretKey = "";
    public String transactinId = "";
    public String ivacId = "";
    public String webfileId = "";
    public String passportNo = "";
    public String appointType = "";
    public String appointDate = "";
    public String mobileNo = "";
    public String email = "";

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public String getStkCode() {
        return stkCode;
    }

    public void setStkCode(String stkCode) {
        this.stkCode = stkCode;
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

    public String getTransactinId() {
        return transactinId;
    }

    public void setTransactinId(String transactinId) {
        this.transactinId = transactinId;
    }

    public String getIvacId() {
        return ivacId;
    }

    public void setIvacId(String ivacId) {
        this.ivacId = ivacId;
    }

    public String getWebfileId() {
        return webfileId;
    }

    public void setWebfileId(String webfileId) {
        this.webfileId = webfileId;
    }

    public String getPassportNo() {
        return passportNo;
    }

    public void setPassportNo(String passportNo) {
        this.passportNo = passportNo;
    }

    public String getAppointType() {
        return appointType;
    }

    public void setAppointType(String appointType) {
        this.appointType = appointType;
    }

    public String getAppointDate() {
        return appointDate;
    }

    public void setAppointDate(String appointDate) {
        this.appointDate = appointDate;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
