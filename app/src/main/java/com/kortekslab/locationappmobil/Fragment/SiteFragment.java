package com.kortekslab.locationappmobil.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.kortekslab.locationappmobil.Adapter.CustomFragmentAdminAdapter;
import com.kortekslab.locationappmobil.Adapter.CustomFragmentSiteAdapter;
import com.kortekslab.locationappmobil.Dao.CampusDaoInterface;
import com.kortekslab.locationappmobil.Model.CampusCrudModel;
import com.kortekslab.locationappmobil.R;
import com.kortekslab.locationappmobil.Utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SiteFragment extends Fragment {
    private RecyclerView siteRv;
    private Spinner spinnerCampusName;
    private ArrayList<String> campusNameList = new ArrayList<>();
    private ArrayList<Integer> campusIdList = new ArrayList<>();
    private ArrayList<Boolean> campusAktifList=new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private CampusDaoInterface campusDaoInterface;
    private CustomFragmentSiteAdapter customFragmentSiteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_site, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        spinnerCampusName = getView().findViewById(R.id.spinnerCampusName);
        siteRv = getView().findViewById(R.id.siteRv);

        siteRv.setHasFixedSize(true);
        siteRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        customFragmentSiteAdapter = new CustomFragmentSiteAdapter(getActivity(), campusNameList, campusAktifList,campusIdList);


        campusDaoInterface = ApiUtils.getCampusDaoInterface();

        campusNameList.add("kampus giriniz");
        campusIdList.add(0);
        getCampusList();
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_dropdown_item, android.R.id.text1, campusNameList);
        spinnerCampusName.setAdapter(arrayAdapter);
        spinnerCampusName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (campusIdList.get(position) == 0) {
                    Toast.makeText(getActivity(), "Lütfen Kampus Seçiniz", Toast.LENGTH_SHORT).show();
                } else {
                    System.out.println("dddd" + campusNameList.get(position));
                    System.out.println("cccc" + campusIdList.get(position));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void getCampusList() {
        campusDaoInterface.getCampus().enqueue(new Callback<List<CampusCrudModel>>() {
            @Override
            public void onResponse(Call<List<CampusCrudModel>> call, Response<List<CampusCrudModel>> response) {
                System.out.println("basarili");
                List<CampusCrudModel> getCampuses = response.body();
                for (CampusCrudModel getCampus : getCampuses) {
                    campusNameList.add(getCampus.getName());
                    campusIdList.add(getCampus.getId());
                    System.out.println("a" + getCampus.getName());
                    System.out.println("a" + getCampus.getId());
                }
                spinnerCampusName.setAdapter(arrayAdapter);
                siteRv.setAdapter(customFragmentSiteAdapter);


            }


            @Override
            public void onFailure(Call<List<CampusCrudModel>> call, Throwable t) {

                System.out.println("basarisiz" + t.getLocalizedMessage());
            }
        });
    }
}
