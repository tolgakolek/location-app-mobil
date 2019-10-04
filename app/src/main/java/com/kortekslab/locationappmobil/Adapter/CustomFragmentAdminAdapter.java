package com.kortekslab.locationappmobil.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kortekslab.locationappmobil.Dao.CampusDaoInterface;
import com.kortekslab.locationappmobil.Model.CampusUpdateModel;
import com.kortekslab.locationappmobil.R;
import com.kortekslab.locationappmobil.Response.CampusCrudResponse;
import com.kortekslab.locationappmobil.Utils.ApiUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomFragmentAdminAdapter extends RecyclerView.Adapter<CustomFragmentAdminAdapter.CustomFragmentAdminNesneTutucu> {

    private Context context;
    private List<String> gelenList;
    private List<Boolean> gelenAktifDurum;
    private List<Integer>gelenIdlistid;
    private PopupMenu popupMenu;
    private View tasarim;
    private CheckBox checkBox;
    private EditText editTextCampusName;
    private CampusDaoInterface campusDaoInterface;
    LinearLayout linearLayout;


    public CustomFragmentAdminAdapter(Context context, List<String> gelenList, List<Boolean> gelenAktifDurum,List<Integer>gelenIdlistid) {
        this.context = context;
        this.gelenList = gelenList;
        this.gelenAktifDurum = gelenAktifDurum;
        this.gelenIdlistid=gelenIdlistid;

    }


    public class CustomFragmentAdminNesneTutucu extends RecyclerView.ViewHolder {

        private TextView textCampusName;
        private CardView cardCampus;
        private ImageView satirResim;
        private CheckBox checkBoxDurum;

        public CustomFragmentAdminNesneTutucu(@NonNull View itemView) {
            super(itemView);
            cardCampus = itemView.findViewById(R.id.campusCard);
            textCampusName = itemView.findViewById(R.id.textCampus);
            satirResim = itemView.findViewById(R.id.satirImage);
            checkBoxDurum = itemView.findViewById(R.id.checkBox);
        }
    }

    @NonNull
    @Override
    public CustomFragmentAdminNesneTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_fragment_admin, parent, false);
        tasarim = LayoutInflater.from(parent.getContext()).inflate(R.layout.alert_tasarim_campus, parent,false);
        return new CustomFragmentAdminNesneTutucu(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CustomFragmentAdminNesneTutucu holder, int position) {
        final String name = gelenList.get(position);
        final Boolean aktifDurum = gelenAktifDurum.get(position);
        final Integer listId=gelenIdlistid.get(position);

        editTextCampusName = tasarim.findViewById(R.id.editTextCampusName);
        checkBox = tasarim.findViewById(R.id.checkBoxAktif);
        linearLayout = tasarim.findViewById(R.id.layout);
        holder.textCampusName.setText(name);
        holder.checkBoxDurum.setChecked(aktifDurum);


        holder.satirResim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                popupMenu = new PopupMenu(context, holder.satirResim);
                popupMenu.getMenuInflater().inflate(R.menu.campus_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {


                        ViewParent t = editTextCampusName.getParent();
                        ViewParent s = checkBox.getParent();
                        if (t.getParent() != null) {
                            ((ViewGroup) t.getParent()).removeView((View) t); // <- fix
                        }
                        if (s.getParent() != null) {
                            ((ViewGroup) s.getParent()).removeView((View) s); // <- fix
                        }
                        editTextCampusName.setText(name);

                        final int id = item.getItemId();
                        if (id == R.id.action_duzenle) {

                            Toast.makeText(context, "Duzenle" + name, Toast.LENGTH_SHORT).show();
                            final AlertDialog.Builder alertbuild = new AlertDialog.Builder(context);
                            editTextCampusName.setText(name);
                            checkBox.setChecked(aktifDurum);
                            alertbuild.setView(tasarim);
                            alertbuild.setIcon(R.drawable.ic_add_alert_black_24dp);
                            alertbuild.setTitle("Kampus Duzenle");
                            alertbuild.setMessage("Mesaj");


                            String name=editTextCampusName.getText().toString();
                            campusDaoInterface=ApiUtils.getCampusDaoInterface();
                            alertbuild.setPositiveButton("Duzenle", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    boolean durum = true;
                                    if (checkBox.isChecked()) durum = true;
                                    else durum = false;
                                    CampusUpdateModel campusUpdateModel=new CampusUpdateModel(editTextCampusName.getText().toString(),null,durum,listId);
                                    campusDaoInterface.updateCampus(campusUpdateModel).enqueue(new Callback<CampusCrudResponse>() {
                                        @Override
                                        public void onResponse(Call<CampusCrudResponse> call, Response<CampusCrudResponse> response) {
                                            System.out.println("updata");
                                         System.out.println("aaa"+response.body().getCampus().getName());
                                            System.out.println("bbb"+response.body().getCampus().getActive());
                                        }

                                        @Override
                                        public void onFailure(Call<CampusCrudResponse> call, Throwable t) {

                                            System.out.println("update"+t.getLocalizedMessage());
                                        }
                                    });

                                }
                            });
                            alertbuild.setNegativeButton("iptal", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            alertbuild.create();
                            alertbuild.show();
                            return true;
                        } else if (id == R.id.action_sil) {
                            Toast.makeText(context, "Sil" + name, Toast.LENGTH_SHORT).show();
                            return true;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return gelenList.size();
    }


}
