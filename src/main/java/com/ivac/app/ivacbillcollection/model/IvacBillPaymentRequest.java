package com.ivac.app.ivacbillcollection.model;

public class IvacBillPaymentRequest {

    public String utility_auth_key = "";
    public String utility_secret_key = "";
    public String transaction_id = "";

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

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }
}
