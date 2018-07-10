package com.liebersonsantos.almacel.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.liebersonsantos.almacel.R;
import com.liebersonsantos.almacel.dataBase.AttendantsDAO;
import com.liebersonsantos.almacel.dataBase.ClientDAO;
import com.liebersonsantos.almacel.dataBase.IncidentDAO;
import com.liebersonsantos.almacel.helper.Constants;
import com.liebersonsantos.almacel.model.Attendants;
import com.liebersonsantos.almacel.model.Client;
import com.liebersonsantos.almacel.model.Incident;
import com.liebersonsantos.almacel.model.IpResponse;
import com.liebersonsantos.almacel.netWork.RestClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.liebersonsantos.almacel.dataBase.IncidentDAO.getDateToString;

public class NewIncidentActivity extends AppCompatActivity {

    @BindView(R.id.new_description)
    EditText edtNewDescription;

    @BindView(R.id.new_status)
    Spinner spinnerNewStatus;

    @BindView(R.id.new_attendant_name)
    Spinner spinnerAttendant;

    @BindView(R.id.new_client_name)
    Spinner spinnerClient;

    private String ip = "0.0.0.0";
    private List<Client> clients;
    private List<Attendants> attendants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_incident);

        ButterKnife.bind(this);

        initSpinners();
        callingAPIconnection();

    }

    private void initSpinners() {
        clients = new ClientDAO(this).getClients();
        attendants = new AttendantsDAO(this).getAttendants();

        List<String> listAttendants = new ArrayList<>();
        listAttendants.add("Atendente");
        for (Attendants attendant : attendants) {
            listAttendants.add(attendant.getAttendantName());
        }

        List<String> listClients = new ArrayList<>();
        listClients.add("Cliente");
        for (Client client : clients) {
            listClients.add(client.getName());
        }

        spinnerAttendant.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listAttendants));
        spinnerClient.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listClients));
        spinnerNewStatus.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, new String[]{"STATUS", "aberto", "fechado"}));
    }

    public long getClientId(String name) {
        for (Client client : clients) {
            if (client.getName().equalsIgnoreCase(name)) {
                return client.getId();
            }
        }
        return 0;
    }

    public long getAttendanceId(String name) {
        for (Attendants attendant : attendants) {
            if (attendant.getAttendantName().equalsIgnoreCase(name)) {
                return attendant.getId();
            }
        }
        return 0;
    }


    @OnClick(R.id.btn_new_incident)
    void newIncident() {

        if (spinnerAttendant.getSelectedItem().toString().equalsIgnoreCase("Atendente")) {
            Toast.makeText(this, "Selecione o atendente", Toast.LENGTH_SHORT).show();
            return;
        }
        if (spinnerClient.getSelectedItem().toString().equalsIgnoreCase("Cliente")) {
            Toast.makeText(this, "Selecione o cliente", Toast.LENGTH_SHORT).show();
            return;
        }
        if (edtNewDescription.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(this, "Adicione uma descrição", Toast.LENGTH_SHORT).show();
            return;
        }
        if (spinnerNewStatus.getSelectedItem().toString().equalsIgnoreCase("STATUS")) {
            Toast.makeText(this, "Selecione o status", Toast.LENGTH_SHORT).show();
            return;
        }

        IncidentDAO incidentDAO = new IncidentDAO(this);
        Incident incident = new Incident();

        incident.setAttendant(getAttendanceId(spinnerAttendant.getSelectedItem().toString()));
        incident.setClient(getClientId(spinnerClient.getSelectedItem().toString()));
        incident.setDescription(edtNewDescription.getText().toString() + "\n\nIP: " + ip);
        incident.setStatus(spinnerNewStatus.getSelectedItem().toString());
        incident.setCreationTime(getDateToString(new Date()));

        incidentDAO.insertIncident(incident);

        Toast.makeText(this, "Incidente inserido com sucesso!", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(NewIncidentActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void callingAPIconnection() {
        RestClient.getInstance().getiP(Constants.URL_FORMAT).enqueue(new Callback<IpResponse>() {
            @Override
            public void onResponse(@NonNull Call<IpResponse> call, @NonNull Response<IpResponse> response) {

                if (response.isSuccessful() && response.body() != null) {
                    ip = response.body().getIp();
                }
            }

            @Override
            public void onFailure(@NonNull Call<IpResponse> call, @NonNull Throwable t) {
                Toast.makeText(NewIncidentActivity.this, "Verificar conexão com o retrofit", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
