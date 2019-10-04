package com.kortekslab.locationappmobil.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.kortekslab.locationappmobil.Model.CampusModel;

public class CampusCrudResponse {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("isSuccess")
    @Expose
    private Boolean isSuccess;
    @SerializedName("campus")
    @Expose
    private CampusModel campus;
    @SerializedName("campuses")
    @Expose
    private Object campuses;
    @SerializedName("success")
    @Expose
    private Boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public CampusModel getCampus() {
        return campus;
    }

    public void setCampus(CampusModel campus) {
        this.campus = campus;
    }

    public Object getCampuses() {
        return campuses;
    }

    public void setCampuses(Object campuses) {
        this.campuses = campuses;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }
}
