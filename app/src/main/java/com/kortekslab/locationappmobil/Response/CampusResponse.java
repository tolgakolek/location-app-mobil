package com.kortekslab.locationappmobil.Response;


import com.kortekslab.locationappmobil.Model.BaseModel;
import com.kortekslab.locationappmobil.Model.CampusModel;

public class CampusResponse  extends BaseModel {

    private CampusModel campus;
    private Object campuses;
    private Boolean success;

    public CampusResponse(CampusModel campus) {
        this.campus = campus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


