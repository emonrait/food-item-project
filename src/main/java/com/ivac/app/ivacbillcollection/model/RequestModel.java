package com.ivac.app.ivacbillcollection.model;

public class RequestModel {
    // Bill Info
    public String utility_auth_key = "";
    public String utility_secret_key = "";
    public String transaction_id = "";
    public String ivac_id = "";
    public String webfile_id = "";
    public String passport_no = "";
    public String appoint_type = "";
    public String appoint_date = "";
    public String mobile_no = "";
    public String email_address = "";

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

    public String getIvac_id() {
        return ivac_id;
    }

    public void setIvac_id(String ivac_id) {
        this.ivac_id = ivac_id;
    }

    public String getWebfile_id() {
        return webfile_id;
    }

    public void setWebfile_id(String webfile_id) {
        this.webfile_id = webfile_id;
    }

    public String getPassport_no() {
        return passport_no;
    }

    public void setPassport_no(String passport_no) {
        this.passport_no = passport_no;
    }

    public String getAppoint_type() {
        return appoint_type;
    }

    public void setAppoint_type(String appoint_type) {
        this.appoint_type = appoint_type;
    }

    public String getAppoint_date() {
        return appoint_date;
    }

    public void setAppoint_date(String appoint_date) {
        this.appoint_date = appoint_date;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }
}
