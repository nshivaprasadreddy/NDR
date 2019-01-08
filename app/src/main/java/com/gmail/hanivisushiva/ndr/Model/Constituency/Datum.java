package com.gmail.hanivisushiva.ndr.Model.Constituency;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("txt_constituency")
    @Expose
    private String txtConstituency;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Datum{" +
                "id='" + id + '\'' +
                ", txtConstituency='" + txtConstituency + '\'' +
                '}';
    }

    public String getTxtConstituency() {
        return txtConstituency;
    }

    public void setTxtConstituency(String txtConstituency) {
        this.txtConstituency = txtConstituency;
    }

}