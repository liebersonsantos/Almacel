package com.liebersonsantos.almacel.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.liebersonsantos.almacel.model.Incident;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class IncidentDAO {

    private static final String TAG = "IncidentDAO";
    private SQLiteDatabase database;
    private Context context;

    public IncidentDAO(Context context){
        DBCore dbCore = new DBCore(context);
        database = dbCore.getWritableDatabase();
        this.context = context;
    }

    public void insertIncident(Incident incident){
        try {
            ContentValues values = new ContentValues();
            values.put("atendente", incident.getAttendantName());
            values.put("cliente", incident.getClientName());
            values.put("descricao", incident.getDescription());
            values.put("status", incident.getStatus());
            values.put("creation_time", incident.getCreationTime());

            database.insert("incidentes", null, values);

        }catch (Exception e){
            Log.i(TAG, "insertIncident: " + e.getMessage());
        }
    }

    public void updateIncident(Incident incident){
        try {
            ContentValues values = new ContentValues();
            values.put("atendente", incident.getAttendantName());
            values.put("cliente", incident.getClientName());
            values.put("descricao", incident.getDescription());
            values.put("status", incident.getStatus());
            values.put("creation_time", incident.getCreationTime());

            database.update("incidentes", values, "_id = ?", new String[]{"" + incident.getId()});

        }catch (Exception e){
            Log.i(TAG, "updateIncident: " + e.getMessage());
        }
    }

    public List<Incident> getIncident(){

        List<Incident> incidentList = new ArrayList<>();
        String[] columns = {"_id", "atendente", "cliente", "descricao", "status", "creation_time"};
        Cursor cursor = database.query("incidentes", columns, null, null, null, null, "_id");

        try {
            if (cursor.getCount() > 0){
                cursor.moveToFirst();

                do {
                    Incident incident = new Incident();
                    incident.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                    incident.setAttendantName(cursor.getString(cursor.getColumnIndex("atendente")));
                    incident.setClientName(cursor.getString(cursor.getColumnIndex("cliente")));
                    incident.setDescription(cursor.getString(cursor.getColumnIndex("descricao")));
                    incident.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                    incident.setCreationTime(cursor.getString(cursor.getColumnIndex("creation_time")));

                    incidentList.add(incident);

                }while (cursor.moveToNext());

            }
            return (incidentList);

        }catch (Exception e){
            Log.i(TAG, "searchIncident: " + e.getMessage());
            return (incidentList);
        }finally {
            cursor.close();
        }
    }

    public void deleteIncident(Incident incident){
        try {
            database.delete("incidentes", "_id = ?", new String[]{"" + incident.getId()});

        }catch (Exception e){
            Log.i(TAG, "deleteIncident: " + e.getMessage());
        }
    }

}