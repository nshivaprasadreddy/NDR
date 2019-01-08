package com.gmail.hanivisushiva.ndr.Model.Post;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    public static class Posts {

        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("data")
        @Expose
        private List<Datum> data = null;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public List<Datum> getData() {
            return data;
        }

        public void setData(List<Datum> data) {
            this.data = data;
        }

    }
}