package com.liebersonsantos.almacel.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.liebersonsantos.almacel.R;
import com.liebersonsantos.almacel.dataBase.IncidentDAO;
import com.liebersonsantos.almacel.model.Incident;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewIncidentActivity extends AppCompatActivity {

    @BindView(R.id.new_attendant_name)
    EditText edtNewAttendatName;
    @BindView(R.id.new_client_name)
    EditText edtNewClientName;
    @BindView(R.id.new_description)
    EditText edtNewDescription;
    @BindView(R.id.new_status)
    EditText edtNewStatus;

    private IncidentDAO incidentDAO;
    private Incident incident;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_incident);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.btn_new_incident)
    void newIncident(){

        incidentDAO = new IncidentDAO(this);
        incident = new Incident();

        incident.setAttendantName(edtNewAttendatName.getText().toString());
        incident.setClientName(edtNewClientName.getText().toString());
        incident.setDescription(edtNewDescription.getText().toString());
        incident.setStatus(edtNewStatus.getText().toString());

        incidentDAO.insertIncident(incident);

        Toast.makeText(this, "Incidente inserido com sucesso!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(NewIncidentActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
