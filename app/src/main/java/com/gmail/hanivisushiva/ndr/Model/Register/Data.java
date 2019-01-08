package com.gmail.hanivisushiva.ndr.Model.Register;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("txt_id")
    @Expose
    private String txtId;
    @SerializedName("txt_email")
    @Expose
    private String txtEmail;
    @SerializedName("txt_password")
    @Expose
    private String txtPassword;
    @SerializedName("txt_name")
    @Expose
    private String txtName;
    @SerializedName("txt_mobile")
    @Expose
    private String txtMobile;
    @SerializedName("txt_constituency")
    @Expose
    private String txtConstituency;
    @SerializedName("txt_status")
    @Expose
    private String txtStatus;
    @SerializedName("txt_date")
    @Expose
    private String txtDate;
    @SerializedName("txt_otp")
    @Expose
    private String txtOtp;

    public String getTxtId() {
        return txtId;
    }

    public void setTxtId(String txtId) {
        this.txtId = txtId;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getTxtPassword() {
        return txtPassword;
    }

    public void setTxtPassword(String txtPassword) {
        this.txtPassword = txtPassword;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtMobile() {
        return txtMobile;
    }

    public void setTxtMobile(String txtMobile) {
        this.txtMobile = txtMobile;
    }

    public String getTxtConstituency() {
        return txtConstituency;
    }

    public void setTxtConstituency(String txtConstituency) {
        this.txtConstituency = txtConstituency;
    }

    public String getTxtStatus() {
        return txtStatus;
    }

    public void setTxtStatus(String txtStatus) {
        this.txtStatus = txtStatus;
    }

    public String getTxtDate() {
        return txtDate;
    }

    public void setTxtDate(String txtDate) {
        this.txtDate = txtDate;
    }

    public String getTxtOtp() {
        return txtOtp;
    }

    public void setTxtOtp(String txtOtp) {
        this.txtOtp = txtOtp;
    }

}