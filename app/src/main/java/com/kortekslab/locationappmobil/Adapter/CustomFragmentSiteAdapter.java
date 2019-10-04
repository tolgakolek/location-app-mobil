package com.kortekslab.locationappmobil.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.kortekslab.locationappmobil.R;

import java.util.List;

public class CustomFragmentSiteAdapter extends RecyclerView.Adapter<CustomFragmentAdminAdapter.CustomFragmentAdminNesneTutucu>{

    private Context context;
    private List<String> gelenList;
    private List<Boolean> gelenAktifDurum;
    private List<Integer>gelenIdlistid;

    public CustomFragmentSiteAdapter(Context context, List<String> gelenList, List<Boolean> gelenAktifDurum, List<Integer> gelenIdlistid) {
        this.context = context;
        this.gelenList = gelenList;
        this.gelenAktifDurum = gelenAktifDurum;
        this.gelenIdlistid = gelenIdlistid;
    }



    public class CustomFragmentSiteNesneTutucu extends RecyclerView.ViewHolder {

        private TextView textCampusName;
        private CardView cardCampus;
        private ImageView satirResim;
        private CheckBox checkBoxDurum;

        public CustomFragmentSiteNesneTutucu(@NonNull View itemView) {
            super(itemView);
            cardCampus = itemView.findViewById(R.id.campusCard);
            textCampusName = itemView.findViewById(R.id.textCampus);
            satirResim = itemView.findViewById(R.id.satirImage);
            checkBoxDurum = itemView.findViewById(R.id.checkBox);
        }
    }
    @NonNull
    @Override
    public CustomFragmentAdminAdapter.CustomFragmentAdminNesneTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomFragmentAdminAdapter.CustomFragmentAdminNesneTutucu holder, int position) {

    }

    @Override
    public int getItemCount() {
        return gelenIdlistid.size();
    }
}
