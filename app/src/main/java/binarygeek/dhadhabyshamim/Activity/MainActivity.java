package binarygeek.dhadhabyshamim.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import binarygeek.dhadhabyshamim.Adapter.EpisodeAdapter;
import binarygeek.dhadhabyshamim.Model.Episode;
import binarygeek.dhadhabyshamim.NetworkCaller.FirebaseCaller;
import binarygeek.dhadhabyshamim.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public LinearLayoutManager linearLayoutManager;
    public static ArrayList<Episode> GlodbalDataStorageForEpisode=new ArrayList<>();
    public FirebaseCaller firebaseCaller;
    public RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateUI();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void workOfDrawer(Toolbar toolbar){

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();


        final  EpisodeAdapter episodeAdapter =new EpisodeAdapter(this);

        firebaseCaller=new FirebaseCaller();
        firebaseCaller.getTotalEpisodes();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("GK","GlodbalDataStorageForEpisode size "+GlodbalDataStorageForEpisode.size());
                episodeAdapter.addAll(GlodbalDataStorageForEpisode);
                linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setAdapter(episodeAdapter);
                recyclerView.setLayoutManager(linearLayoutManager);
            }
        }, 1000);


    }
    public void CreateUI(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        workOfDrawer( toolbar);
    }
}
