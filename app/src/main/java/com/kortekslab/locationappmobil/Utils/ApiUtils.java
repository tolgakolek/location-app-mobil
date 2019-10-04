package com.kortekslab.locationappmobil.Utils;


import com.kortekslab.locationappmobil.Dao.CampusDaoInterface;
import com.kortekslab.locationappmobil.RetrofitClient;

public class ApiUtils {
    public static final String BASE_URL="http://192.168.1.5:8080/";

    /*public static final FilmlerDaoInterface getFilmlerDaoInterface(){
        return RetrofitClient.getClient(BASE_URL).create(FilmlerDaoInterface.class);
    }*/
    public static final CampusDaoInterface getCampusDaoInterface(){
        return RetrofitClient.getClient(BASE_URL).create(CampusDaoInterface.class);
    }
}
