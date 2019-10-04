package com.kortekslab.locationappmobil.Dao;

import com.kortekslab.locationappmobil.Model.CampusCrudModel;
import com.kortekslab.locationappmobil.Model.CampusModel;
import com.kortekslab.locationappmobil.Model.CampusUpdateModel;
import com.kortekslab.locationappmobil.Response.CampusCrudResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface CampusDaoInterface {

    @GET("campus/list")
    Call<List<CampusCrudModel>> getCampus();

    @POST("campus/")
    Call<CampusCrudResponse> setCampus(@Body CampusModel campusModel);

    @PUT("campus/")
    Call<CampusCrudResponse>updateCampus(@Body CampusUpdateModel campusUpdateModel);
}
