package com.ivac.app.ivacbillcollection.model;


public class IvacBillPaymntResponse {

    public String outCode = "";
    public String outMessage = "";


    public String status = "";
    public String code = "";
    public String message = "";
    public String statusTitle = "";

    public String vrGuid = "";
    public String rechargeStatus = "";
    public String lid = "";


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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVrGuid() {
        return vrGuid;
    }

    public void setVrGuid(String vrGuid) {
        this.vrGuid = vrGuid;
    }

    public String getRechargeStatus() {
        return rechargeStatus;
    }

    public void setRechargeStatus(String rechargeStatus) {
        this.rechargeStatus = rechargeStatus;
    }

    public String getLid() {
        return lid;
    }

    public void setLid(String lid) {
        this.lid = lid;
    }

    public String getStatusTitle() {
        return statusTitle;
    }

    public void setStatusTitle(String statusTitle) {
        this.statusTitle = statusTitle;
    }


}
