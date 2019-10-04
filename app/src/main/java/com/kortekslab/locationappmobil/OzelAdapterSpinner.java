package com.kortekslab.locationappmobil;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;

import java.util.ArrayList;

public class OzelAdapterSpinner implements SpinnerAdapter {

    private ArrayList<String>campusName=new ArrayList<String>();
    private ArrayList<Integer>campusId=new ArrayList<Integer>();
    private Context context;

    public OzelAdapterSpinner(ArrayList<String> campusName, ArrayList<Integer> campusId, Context context) {
        this.campusName = campusName;
        this.campusId = campusId;
        this.context = context;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
