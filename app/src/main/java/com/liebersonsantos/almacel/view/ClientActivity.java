package com.liebersonsantos.almacel.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.liebersonsantos.almacel.R;
import com.liebersonsantos.almacel.dataBase.AttendantsDAO;
import com.liebersonsantos.almacel.dataBase.ClientDAO;
import com.liebersonsantos.almacel.model.Attendants;
import com.liebersonsantos.almacel.model.Client;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientActivity extends AppCompatActivity {

    @BindView(R.id.edtName)
    EditText edtName;

    @BindView(R.id.edtCompany)
    EditText edtCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonSave)
    public void saveClick() {
        ClientDAO clientDAO = new ClientDAO(this);

        if (edtName.getText().toString().equals("")) {
            Toast.makeText(this, "Preencha o campo...", Toast.LENGTH_SHORT).show();
            edtName.requestFocus();
            return;
        }

        if (edtCompany.getText().toString().equals("")) {
            Toast.makeText(this, "Preencha o campo...", Toast.LENGTH_SHORT).show();
            edtCompany.requestFocus();
            return;
        }

        Client client = new Client();
        client.setName(edtName.getText().toString());
        client.setCompanyName(edtCompany.getText().toString());

        clientDAO.insertClient(client);
        Toast.makeText(this, "Cliente salvo com sucesso...", Toast.LENGTH_LONG).show();
        finish();
    }
}
