package com.ivac.app.ivacbillcollection.model;

public class TokenRequestModel {
    String authKey = "";
    String stkCode = "";

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
}
