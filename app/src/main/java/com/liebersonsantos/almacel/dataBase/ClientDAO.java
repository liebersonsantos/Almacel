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
        DBCore dbCore = new DBCore(context);
        database = dbCore.getWritableDatabase();
        this.context = context;
    }

    public void insertClient(Client client) {

        try {
            ContentValues values = new ContentValues();
            values.put("nome", client.getName());
            values.put("empresa", client.getCompanyName());

            database.insert("clientes", null, values);

        } catch (Exception e) {
            Log.i(TAG, "insertClient: " + e.getMessage());
        }

    }

    public void updateClient(Client client) {

        try {
            ContentValues values = new ContentValues();
            values.put("nome", client.getName());
            values.put("empresa", client.getCompanyName());

            database.update("clientes", values, "_id = ?", new String[]{"" + client.getId()});

        } catch (Exception e) {
            Log.i(TAG, "updateClient: " + e.getMessage());
        }
    }

    public List<Client> getClients() {

        List<Client> clientList = new ArrayList<Client>();
        String[] columns = {"_id", "nome", "empresa"};
        Cursor cursor = database.query("clientes", columns, null, null, null, null, "_id");

        try {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                do {
                    Client client = new Client();
                    client.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                    client.setName(cursor.getString(cursor.getColumnIndex("nome")));
                    client.setCompanyName(cursor.getString(cursor.getColumnIndex("empresa")));

                    clientList.add(client);

                } while (cursor.moveToNext());
            }
            return (clientList);

        } catch (Exception e) {
            Log.i(TAG, "searchClient: " + e.getMessage());
            return (clientList);
        } finally {
            cursor.close();
        }

    }

    public Client getClientById(long id) {

        Client client = new Client();
        String[] columns = {"_id", "nome", "empresa"};
        String where = "_id = ?";
        Cursor cursor = database.query("clientes", columns, where, new String[]{"" + id}, null, null, null);

        try {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();

                do {

                    client.setId(cursor.getLong(cursor.getColumnIndex("_id")));
                    client.setName(cursor.getString(cursor.getColumnIndex("nome")));
                    client.setCompanyName(cursor.getString(cursor.getColumnIndex("empresa")));

                    return client;

                } while (cursor.moveToNext());
            } else {
                return client;
            }

        } catch (Exception e) {
            Log.i(TAG, "searchClient: " + e.getMessage());
            return (client);
        } finally {
            cursor.close();
        }
    }

    public void deleteClient(Client client) {
        try {
            database.delete("clientes", "_id = ?", new String[]{"" + client.getId()});
        } catch (Exception e) {
            Log.i(TAG, "deleteClient: " + e.getMessage());
        }

    }
}
