package com.liebersonsantos.almacel.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.liebersonsantos.almacel.R;
import com.liebersonsantos.almacel.adapter.AdapterIncidents;
import com.liebersonsantos.almacel.dataBase.IncidentDAO;
import com.liebersonsantos.almacel.helper.Constants;
import com.liebersonsantos.almacel.model.Incident;
import com.liebersonsantos.almacel.model.IpResponse;
import com.liebersonsantos.almacel.netWork.RestClient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.content_recyclerView)
    RecyclerView recyclerView;

    private AdapterIncidents adapterIncidents;
    private List<Incident> incidentList;
    private IncidentDAO incidentDAO;
    private IpResponse ipResponse;
    private static final String TAG = "MAINTEST";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
        ButterKnife.bind(this);



        callingAPIconnection();

    }

    private void callingAPIconnection() {
        RestClient.getInstance().getiP(Constants.URL_FORMAT).enqueue(new Callback<IpResponse>() {
            @Override
            public void onResponse(Call<IpResponse> call, Response<IpResponse> response) {

                if (response.isSuccessful() && response.body() != null){

                    Log.i(TAG, "onResponse: " + response.body().getIp());

                    adapterIncidents.setIpResponseL(response.body().getIp());

                    settingsAdapter();
                }

            }

            @Override
            public void onFailure(Call<IpResponse> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Verificar conexão com o retrofit", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void settingsAdapter() {
        incidentDAO = new IncidentDAO(this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapterIncidents = new AdapterIncidents(incidentDAO.getIncident());
        recyclerView.setAdapter(adapterIncidents);
    }

    @OnClick(R.id.fab)
    void onFabClicked(View view){
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();

        Intent intent = new Intent(MainActivity.this, NewIncidentActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

}