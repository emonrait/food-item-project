package com.ivac.app.ivacbillcollection.model;

public class TokenResponse {
    String utility_auth_key;
    String utility_secret_key;

    String outCode;
    String outMessage;

    public String getUtility_auth_key() {
        return utility_auth_key;
    }

    public void setUtility_auth_key(String utility_auth_key) {
        this.utility_auth_key = utility_auth_key;
    }

    public String getUtility_secret_key() {
        return utility_secret_key;
    }

    public void setUtility_secret_key(String utility_secret_key) {
        this.utility_secret_key = utility_secret_key;
    }

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
}
