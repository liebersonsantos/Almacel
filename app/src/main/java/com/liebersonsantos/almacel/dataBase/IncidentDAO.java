package com.liebersonsantos.almacel.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.liebersonsantos.almacel.model.Incident;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class IncidentDAO {

    private static final String TAG = "IncidentDAO";
    private SQLiteDatabase database;
    private Context context;

    public IncidentDAO(Context context) {
        DBCore dbCore = new DBCore(context);
        database = dbCore.getWritableDatabase();
        this.context = context;
    }

    public void insertIncident(Incident incident) {
        try {
            ContentValues values = new ContentValues();
            values.put("atendente", incident.getAttendant());
            values.put("cliente", incident.getClient());
            values.put("descricao", incident.getDescription());
            values.put("status", incident.getStatus());
            values.put("creation_time", incident.getCreationTime());

            database.insert("incidentes", null, values);

        } catch (Exception e) {
            Log.i(TAG, "insertIncident: " + e.getMessage());
        }
    }

    public void updateIncident(Incident incident) {
        try {
            ContentValues values = new ContentValues();
            values.put("atendente", incident.getAttendant());
            values.put("cliente", incident.getClient());
            values.put("descricao", incident.getDescription());
            values.put("status", incident.getStatus());
            values.put("creation_time", incident.getCreationTime());

            database.update("incidentes", values, "_id = ?", new String[]{"" + incident.getId()});

        } catch (Exception e) {
            Log.i(TAG, "updateIncident: " + e.getMessage());
        }
    }

    public List<Incident> getIncidents() {

        List<Incident> incidentList = new ArrayList<>();
        String[] columns = {"_id", "atendente", "cliente", "descricao", "status", "creation_time"};
        Cursor cursor = database.query("incidentes", columns, null, null, null, null, "_id");

        try {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                do {
                    Incident incident = new Incident();
                    incident.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                    incident.setAttendant(cursor.getInt(cursor.getColumnIndex("atendente")));
                    incident.setClient(cursor.getInt(cursor.getColumnIndex("cliente")));
                    incident.setDescription(cursor.getString(cursor.getColumnIndex("descricao")));
                    incident.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                    incident.setCreationTime(cursor.getString(cursor.getColumnIndex("creation_time")));

                    incidentList.add(incident);

                } while (cursor.moveToNext());

            }
            return (incidentList);

        } catch (Exception e) {
            Log.i(TAG, "searchIncident: " + e.getMessage());
            return (incidentList);
        } finally {
            cursor.close();
        }
    }

    public Incident getIncidentById(long id) {

        Incident incident = new Incident();
        String[] columns = {"_id", "atendente", "cliente", "descricao", "status", "creation_time"};
        String where = "_id = ?";
        Cursor cursor = database.query("incidentes", columns, where, new String[]{"" + id}, null, null, null);

        try {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                do {

                    incident.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                    incident.setAttendant(cursor.getInt(cursor.getColumnIndex("atendente")));
                    incident.setClient(cursor.getInt(cursor.getColumnIndex("cliente")));
                    incident.setDescription(cursor.getString(cursor.getColumnIndex("descricao")));
                    incident.setStatus(cursor.getString(cursor.getColumnIndex("status")));
                    incident.setCreationTime(cursor.getString(cursor.getColumnIndex("creation_time")));

                    return incident;

                } while (cursor.moveToNext());
            } else {
                return incident;
            }

        } catch (Exception e) {
            Log.i(TAG, "searchClient: " + e.getMessage());
            return (incident);
        } finally {
            cursor.close();
        }
    }

    public void deleteIncident(Incident incident) {
        try {
            database.delete("incidentes", "_id = ?", new String[]{"" + incident.getId()});

        } catch (Exception e) {
            Log.i(TAG, "deleteIncident: " + e.getMessage());
        }
    }

    public static String getDateToString(Date date) {
        if (date == null || date.equals(""))
            return null;

        DateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("pt", "BR"));
        return formatador.format(date);
    }

    public static Date getStringToDate(String date) {
        Date data = null;

        DateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("pt", "BR"));
        try {
            data = (Date) formatador.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return data;
    }
}