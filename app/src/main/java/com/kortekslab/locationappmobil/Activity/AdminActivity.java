package com.kortekslab.locationappmobil.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
import com.kortekslab.locationappmobil.Fragment.CampusFragment;
import com.kortekslab.locationappmobil.Fragment.SiteFragment;
import com.kortekslab.locationappmobil.IkinciFragment;
import com.kortekslab.locationappmobil.R;
import com.kortekslab.locationappmobil.UcuncuFragment;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SearchView.OnQueryTextListener {

    private NavigationView navigationView;
    public Toolbar toolbar;
    private Fragment fragment;
    private DrawerLayout drawer;
    private MenuItem menuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        //fragment = new BirinciFragment();
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment_tutucu, fragment).commit();

        navigationView = findViewById(R.id.nav_view);
        drawer = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);


        setToolbar(toolbar, "deneme");
        navigationView.setNavigationItemSelectedListener(this);


    }


    public void setToolbar(Toolbar toolbar, String title) {
        AppCompatActivity actionBar = this;
        actionBar.setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) actionBar.findViewById(R.id.drawer);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.setDrawerIndicatorEnabled(true);
        toogle.syncState();
        if (toolbar != null)
            toolbar.setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem menuItem=menu.findItem(R.id.action_search);
       // SearchView searchView= (SearchView) menuItem.getActionView();
       //searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();
        if (id == R.id.action_bir) {
            fragment = new CampusFragment();
            setToolbar(toolbar, "campus");



        } else if (id == R.id.action_iki) {
            fragment = new SiteFragment();
        } else if (id == R.id.action_uc) {
            fragment = new UcuncuFragment();
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_tutucu, fragment).commit();

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {

            drawer.closeDrawer(GravityCompat.START);

        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
