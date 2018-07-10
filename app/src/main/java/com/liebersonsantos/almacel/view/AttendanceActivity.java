package com.liebersonsantos.almacel.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.liebersonsantos.almacel.R;
import com.liebersonsantos.almacel.dataBase.AttendantsDAO;
import com.liebersonsantos.almacel.model.Attendants;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AttendanceActivity extends AppCompatActivity {

    @BindView(R.id.edtName)
    EditText edtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttonSave)
    public void saveClick() {
        AttendantsDAO attendantsDAO = new AttendantsDAO(this);

        if (!edtName.getText().toString().equals("")) {

            Attendants attendants = new Attendants();
            attendants.setAttendantName(edtName.getText().toString());
            attendantsDAO.insertAttedants(attendants);
            Toast.makeText(this, "Atendente salvo com sucesso...", Toast.LENGTH_LONG).show();
            finish();

        } else {
            Toast.makeText(this, "Preencha o campo...", Toast.LENGTH_SHORT).show();
            edtName.requestFocus();
        }
    }
}
