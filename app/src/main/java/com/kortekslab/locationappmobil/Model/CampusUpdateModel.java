package com.kortekslab.locationappmobil.Model;


import com.kortekslab.locationappmobil.Response.CampusResponse;

public class CampusUpdateModel {


    private Integer id;
    private String name;
    private Object campusSites;
    private Boolean active;
    private CampusResponse campusResponse;

    public CampusUpdateModel(String name, Object campusSites, Boolean active,Integer id) {
        this.name = name;
        this.id=id;
        this.campusSites = campusSites;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getCampusSites() {
        return campusSites;
    }

    public void setCampusSites(Object campusSites) {
        this.campusSites = campusSites;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public CampusResponse getCampusResponse() {
        return campusResponse;
    }

    public void setCampusResponse(CampusResponse campusResponse) {
        this.campusResponse = campusResponse;
    }
}


