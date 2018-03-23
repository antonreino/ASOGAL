package com.example.tone.asogal;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.tone.asogal.Adapters.MyAdapter;
import com.example.tone.asogal.Firebase.FirebaseReferences;
import com.example.tone.asogal.Model.Partido;
import com.example.tone.asogal.Model.Persona;
import com.example.tone.asogal.ViewModel.PartidoViewModel;
import com.example.tone.asogal.ViewModel.PersonaViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FirebaseAuth auth;
    FirebaseDatabase DB;
    DatabaseReference partidos;
    TextView tv1,tvArbitro;
    RecyclerView rv;
    List<Partido> listaPartidos=new ArrayList<>();
    PartidoViewModel partidoViewModel;
    PersonaViewModel personaViewModel;
    ImageView icono;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        auth=FirebaseAuth.getInstance();
        DB=FirebaseDatabase.getInstance();
        partidos=DB.getReference(FirebaseReferences.Partido);
        tv1=(TextView)findViewById(R.id.tvHome);
        rv=(RecyclerView) findViewById(R.id.rv);
         tvArbitro = findViewById(R.id.tvArbitro);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        icono = (ImageView) findViewById(R.id.ivicon);





        mRecyclerView =  findViewById(R.id.rv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        partidoViewModel = ViewModelProviders.of(this).get(PartidoViewModel.class);
        partidoViewModel.getPartidos().observe(this, new Observer<List<Partido>>() {
            @Override
            public void onChanged(@Nullable List<Partido> partidos) {





                mAdapter = new MyAdapter(partidos);
                mRecyclerView.setAdapter(mAdapter);

            }
        });



        setSupportActionBar(toolbar);




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //URL url = null;
        View headerView = navigationView.getHeaderView(0);
        tvArbitro =headerView.findViewById(R.id.tvArbitro);
        icono = headerView.findViewById(R.id.ivicon);
        partidoViewModel.getUsrRegistrado().observe(this, new Observer<Persona>() {
            @Override
            public void onChanged(@Nullable Persona persona) {
                tvArbitro.setText(persona.getNombre());
            }
        });
        partidoViewModel.getFotoUsr().observe(this, new Observer<Bitmap>() {
            @Override
            public void onChanged(@Nullable Bitmap bitmap) {
                icono.setImageBitmap(bitmap);
            }
        });







    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        tvArbitro = drawer.findViewById(R.id.tvArbitro);
        tvArbitro.setText("Pedro");
        if (drawer.isDrawerOpen(GravityCompat.START)) {


            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
        Log.i("Datos", "onBackPRessed");
        tvArbitro=findViewById(R.id.tvArbitro);
        Log.i("Datos", tvArbitro.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home2, menu);
        Log.i("Datos", "onCreateOptionMenu");
        tvArbitro=findViewById(R.id.tvArbitro);
        Log.i("Datos", tvArbitro.toString());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        Log.i("Datos", "onItemSelected");
        tvArbitro=findViewById(R.id.tvArbitro);
        Log.i("Datos", tvArbitro.toString());
        return super.onOptionsItemSelected(item);
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
        Log.i("Datos", "onNavigationItem");
        tvArbitro=findViewById(R.id.tvArbitro);
        Log.i("Datos", tvArbitro.toString());
        return true;
    }




}
