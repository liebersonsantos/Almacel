package com.liebersonsantos.almacel.dataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.liebersonsantos.almacel.model.Client;

import java.util.ArrayList;
import java.util.List;

public class ClientDAO {

    private static final String TAG = "ClientDAOLog";
    private SQLiteDatabase database;
    private Context context;

    public ClientDAO(Context context) {
        DBCore dbCore = new  DBCore(context);
        database = dbCore.getWritableDatabase();
        this.context = context;
    }

    public void insertClient(Client client){
        
        try {
            ContentValues values = new ContentValues();
            values.put("nome", client.getName());
            values.put("empresa", client.getCompanyName());

            database.insert("clientes",null, values);
        }catch (Exception e){
            Log.i(TAG, "insertClient: " + e.getMessage());
        }
            
    }

    public void updateClient(Client client){

        try {
            ContentValues values = new ContentValues();
            values.put("nome", client.getName());
            values.put("empresa", client.getCompanyName());

            database.update("clientes", values, "_id = ?", new String[]{"" + client.getId()});
        }catch (Exception e){
            Log.i(TAG, "updateClient: " + e.getMessage());
        }
    }

    public List<Client> searchClient(){

        List<Client> clientList = new ArrayList<Client>();
        String[] columns = {"_id", "nome", "empresa"};
        Cursor cursor = database.query("clientes", columns, null, null, null, null,"_id");

        return ();
    }



}