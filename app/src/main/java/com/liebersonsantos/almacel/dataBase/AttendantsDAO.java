package com.liebersonsantos.almacel.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.liebersonsantos.almacel.model.Attendants;

import java.util.ArrayList;
import java.util.List;

public class AttendantsDAO {

    private static final String TAG = "AttendantsDAO";
    private SQLiteDatabase database;
    private Context context;

    public AttendantsDAO(Context context){
        DBCore dbCore = new DBCore(context);
        database = dbCore.getWritableDatabase();
        this.context = context;
    }

    public void insertAttedants(Attendants attendants){

        try {
            ContentValues values = new ContentValues();
            values.put("nome", attendants.getAttendantName());

            database.insert("atendentes", null, values);

        }catch (Exception e){
            Log.i(TAG, "insertAttedants: " + e.getMessage());

        }
    }

    public void updateAttendants(Attendants attendants){
        try {
            ContentValues values = new ContentValues();
            values.put("nome", attendants.getAttendantName());

            database.update("atendentes", values, "_id = ?", new String[]{"" + attendants.getId()});

        }catch (Exception e){
            Log.i(TAG, "updateAttendants: " + e.getMessage());
        }

    }

    public List<Attendants> getAttendants(){

        List<Attendants> attendantsList = new ArrayList<>();
        String[] columns = {"_id", "nome"};
        Cursor cursor = database.query("atendentes", columns, null, null, null, null, "_id");

        try {
            if (cursor.getCount() > 0){
                cursor.moveToFirst();

                do {
                    Attendants attendants = new Attendants();
                    attendants.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                    attendants.setAttendantName(cursor.getString(cursor.getColumnIndex("nome")));

                    attendantsList.add(attendants);

                }while (cursor.moveToNext());
            }
            return (attendantsList);

        }catch (Exception e){
            Log.i(TAG, "searchAttendants: " + e.getMessage());
            return (attendantsList);
        }finally {
            cursor.close();
        }

    }

    public void deleteAttendants(Attendants attendants){
        try {
            database.delete("atendentes", "_id = ?", new String[]{"" + attendants.getId()});
        }catch (Exception e){
            Log.i(TAG, "deleteAttendants: " + e.getMessage());
        }

    }

}
