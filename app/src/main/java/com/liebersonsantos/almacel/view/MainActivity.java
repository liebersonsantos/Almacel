package com.liebersonsantos.almacel.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.liebersonsantos.almacel.R;
import com.liebersonsantos.almacel.adapter.AdapterIncidents;
import com.liebersonsantos.almacel.dataBase.IncidentDAO;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.content_recyclerView)
    RecyclerView recyclerView;

    private static final String TAG = "MAINTEST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        settingsAdapter();
    }

    private void settingsAdapter() {
        IncidentDAO incidentDAO = new IncidentDAO(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        AdapterIncidents adapterIncidents = new AdapterIncidents(this, incidentDAO.getIncidents());
        recyclerView.setAdapter(adapterIncidents);
    }

    @OnClick(R.id.fab)
    void onFabClicked() {
        startActivity(new Intent(MainActivity.this, NewIncidentActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_attendance) {
            startActivity(new Intent(this, AttendanceActivity.class));
            return true;
        }
        if (id == R.id.action_client) {
            startActivity(new Intent(this, ClientActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}