package com.ivac.app.ivacbillcollection.model;

import java.util.ArrayList;

public class IvacCenterResponse {
    private String code = "";
    private String status = "";
    private String outCode = "";
    private String outMessage = "";
    private ArrayList<VisaCenter> centerList;
    private ArrayList<AppointmentType> appointmentTypesList;

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

    public ArrayList<VisaCenter> getCenterList() {
        return centerList;
    }

    public void setCenterList(ArrayList<VisaCenter> centerList) {
        this.centerList = centerList;
    }

    public ArrayList<AppointmentType> getAppointmentTypesList() {
        return appointmentTypesList;
    }

    public void setAppointmentTypesList(ArrayList<AppointmentType> appointmentTypesList) {
        this.appointmentTypesList = appointmentTypesList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
