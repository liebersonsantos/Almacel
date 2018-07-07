package com.liebersonsantos.almacel.dataBase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBCore extends SQLiteOpenHelper {

    private static final String DB_NAME = "almacel_db";
    private static final int DB_VERSION = 1;

    public DBCore(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists atendentes("
                + "_id integer(11) not null primary key autoincrement,"
                + "nome text(64) not null);");

        db.execSQL("create table if not exists clientes("
                + "_id integer(11) not null primary key autoincrement,"
                + "nome text(64) not null,"
                + "empresa text(64) not null);");

        db.execSQL("create table if not exists incidentes("
                + "_id integer(11) not null primary key autoincrement,"
                + "atendente integer(11) not null,"
                + "cliente integer(11) not null,"
                + "descricao text(512) default null,"
                + "status text(16) default null,"
                + "creation_time datetime default current_timestamp);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table atendentes");
        db.execSQL("drop table clientes");
        db.execSQL("drop table incidentes");
        onCreate(db);
    }
}
