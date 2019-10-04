package com.kortekslab.locationappmobil.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.kortekslab.locationappmobil.Activity.AdminActivity;
import com.kortekslab.locationappmobil.Adapter.CustomFragmentAdminAdapter;
import com.kortekslab.locationappmobil.Dao.CampusDaoInterface;
import com.kortekslab.locationappmobil.Model.CampusCrudModel;
import com.kortekslab.locationappmobil.Model.CampusModel;
import com.kortekslab.locationappmobil.R;
import com.kortekslab.locationappmobil.Response.CampusCrudResponse;
import com.kortekslab.locationappmobil.Utils.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CampusFragment extends Fragment {

    private RecyclerView rv;
    private CustomFragmentAdminAdapter customFragmentAdminAdapter;
    ArrayList<String> campusNameList = new ArrayList<>();
    ArrayList<Boolean> campusAktifList = new ArrayList<>();
    ArrayList<Integer>campusIdList=new ArrayList<>();
    private CampusDaoInterface campusDaoInterface;
    private FloatingActionButton fab;
    private TextInputLayout textInputLayout;
    private EditText editTextCampus;
    private CheckBox checkBox;
    private View tasarim;
    AdminActivity adminActivity;
    private Toolbar toolbar;
    CampusCrudModel campusCrudModel;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_campus, container, false);



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv = getView().findViewById(R.id.campusRv);
        fab = getView().findViewById(R.id.fab);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        customFragmentAdminAdapter = new CustomFragmentAdminAdapter(getActivity(), campusNameList, campusAktifList,campusIdList);
        campusDaoInterface = ApiUtils.getCampusDaoInterface();
        final AlertDialog.Builder alertolusturucu = new AlertDialog.Builder(getActivity());

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasarim = getLayoutInflater().inflate(R.layout.alert_tasarim_campus, null);

               // textInputLayout = tasarim.findViewById(R.id.textInputCampusName);
                checkBox = tasarim.findViewById(R.id.checkBoxAktif);
                editTextCampus = tasarim.findViewById(R.id.editTextCampusName);

                alertolusturucu.setIcon(R.drawable.ic_add_alert_black_24dp);
                alertolusturucu.setTitle("Kampus Ekle");
                alertolusturucu.setMessage("Mesaj");
                alertolusturucu.setView(tasarim);
                alertolusturucu.setPositiveButton("Kaydet", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        boolean aktifDurum = true;
                        if (checkBox.isChecked()) aktifDurum = true;
                        else aktifDurum = false;
                        customFragmentAdminAdapter.notifyDataSetChanged();
                        String campusName = editTextCampus.getText().toString();
                        CampusModel campusModel = new CampusModel(campusName, null, aktifDurum);
                        campusDaoInterface.setCampus(campusModel).enqueue(new Callback<CampusCrudResponse>() {
                            @Override
                            public void onResponse(Call<CampusCrudResponse> call, Response<CampusCrudResponse> response) {
                                Toast.makeText(getActivity(), "Kaydedili", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(Call<CampusCrudResponse> call, Throwable t) {

                            }
                        });

                    }
                });
                alertolusturucu.setNegativeButton("iptal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                });
                alertolusturucu.create().show();
            }
        });



        getCampusList();
        //postCampus();
    }


    private void getCampusList() {
        campusDaoInterface.getCampus().enqueue(new Callback<List<CampusCrudModel>>() {
            @Override
            public void onResponse(Call<List<CampusCrudModel>> call, Response<List<CampusCrudModel>> response) {
                System.out.println("basarili");

                System.out.println("basarili");
                List<CampusCrudModel> getCampuses = response.body();
                for (CampusCrudModel getCampus : getCampuses) {
                    campusNameList.add(getCampus.getName());
                    campusAktifList.add(getCampus.getActive());
                    campusIdList.add(getCampus.getId());
                }
                rv.setAdapter(customFragmentAdminAdapter);


            }


            @Override
            public void onFailure(Call<List<CampusCrudModel>> call, Throwable t) {

                System.out.println("basarisiz" + t.getLocalizedMessage());
            }
        });
    }

    private void postCampus() {
        CampusModel campusModel = new CampusModel("Cumhuriyet Universtesi", null, true);
        campusDaoInterface.setCampus(campusModel).enqueue(new Callback<CampusCrudResponse>() {
            @Override
            public void onResponse(Call<CampusCrudResponse> call, Response<CampusCrudResponse> response) {
                System.out.println("basarili");
            }

            @Override
            public void onFailure(Call<CampusCrudResponse> call, Throwable t) {

            }
        });
    }
}
