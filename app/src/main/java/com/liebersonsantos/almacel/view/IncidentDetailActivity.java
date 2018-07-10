package com.liebersonsantos.almacel.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.liebersonsantos.almacel.R;
import com.liebersonsantos.almacel.dataBase.AttendantsDAO;
import com.liebersonsantos.almacel.dataBase.ClientDAO;
import com.liebersonsantos.almacel.dataBase.IncidentDAO;
import com.liebersonsantos.almacel.model.Attendants;
import com.liebersonsantos.almacel.model.Client;
import com.liebersonsantos.almacel.model.Incident;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IncidentDetailActivity extends AppCompatActivity {

    @BindView(R.id.item_content_id_incident)
    TextView txtId;

    @BindView(R.id.item_content_attendance_name)
    TextView txtAttendanceName;

    @BindView(R.id.item_content_client_name)
    TextView txtClientName;

    @BindView(R.id.item_content_client_company)
    TextView txtClientNameCompany;

    @BindView(R.id.item_content_description)
    TextView txtDescription;

    @BindView(R.id.item_content_date)
    TextView txtDate;

    @BindView(R.id.item_content_status)
    TextView txtStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_detail);

        ButterKnife.bind(this);


        if (getIntent() != null && getIntent().getExtras() != null) {
            Incident incident = new IncidentDAO(this).getIncidentById(getIntent().getLongExtra("incident_id", 0));
            Client client = new ClientDAO(this).getClientById(incident.getClient());
            Attendants attendant = new AttendantsDAO(this).getAttendantById(incident.getAttendant());

            txtId.setText(getString(R.string.text_fotmat, "Id: ", String.valueOf(incident.getId())));
            txtAttendanceName.setText(getString(R.string.text_fotmat, "Atendente: ", attendant.getAttendantName()));
            txtClientName.setText(getString(R.string.text_fotmat, "Cliente: ", client.getName()));
            txtClientNameCompany.setText(getString(R.string.text_fotmat, "Cliente empresa: ", client.getCompanyName()));
            txtDescription.setText(incident.getDescription());
            txtDate.setText(getString(R.string.text_fotmat, "Data de criação: ", incident.getCreationTime()));
            txtStatus.setText(getString(R.string.text_fotmat, "Status: ", incident.getStatus()));

        } else {
            finish();
        }
    }
}
