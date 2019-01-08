package com.gmail.hanivisushiva.ndr.Model.Event;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("txt_id")
    @Expose
    private String txtId;
    @SerializedName("txt_title")
    @Expose
    private String txtTitle;
    @SerializedName("txt_description")
    @Expose
    private String txtDescription;
    @SerializedName("txt_feature_image")
    @Expose
    private String txtFeatureImage;
    @SerializedName("txt_location")
    @Expose
    private String txtLocation;
    @SerializedName("txt_status")
    @Expose
    private String txtStatus;
    @SerializedName("txt_date")
    @Expose
    private String txtDate;

    public String getTxtId() {
        return txtId;
    }

    public void setTxtId(String txtId) {
        this.txtId = txtId;
    }

    public String getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(String txtTitle) {
        this.txtTitle = txtTitle;
    }

    public String getTxtDescription() {
        return txtDescription;
    }

    public void setTxtDescription(String txtDescription) {
        this.txtDescription = txtDescription;
    }

    public String getTxtFeatureImage() {
        return txtFeatureImage;
    }

    public void setTxtFeatureImage(String txtFeatureImage) {
        this.txtFeatureImage = txtFeatureImage;
    }

    public String getTxtLocation() {
        return txtLocation;
    }

    public void setTxtLocation(String txtLocation) {
        this.txtLocation = txtLocation;
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

}