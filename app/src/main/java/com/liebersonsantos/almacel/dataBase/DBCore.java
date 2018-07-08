package com.liebersonsantos.almacel.dataBase;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBCore extends SQLiteOpenHelper {

    private static final String DB_NAME = "almacel_db";
    private static final int DB_VERSION = 1;

    public DBCore(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table if not exists atendentes("
                + "_id integer not null primary key autoincrement,"
                + "nome text(64) not null);");

        db.execSQL("create table if not exists clientes("
                + "_id integer not null primary key autoincrement,"
                + "nome text(64) not null,"
                + "empresa text(64) not null);");

        db.execSQL("create table if not exists incidentes("
                + "_id integer not null primary key autoincrement,"
                + "atendente integer(11) not null,"
                + "cliente integer(11) not null,"
                + "descricao text(512) default null,"
                + "status text(16) default null,"
                + "creation_time datetime default current_timestamp);");

        db.execSQL("insert into atendentes (_id, nome) values("
                +"4, 'Anderson')");
        db.execSQL("insert into atendentes (_id, nome) values("
                +"5, 'Andre')");
        db.execSQL("insert into atendentes (_id, nome) values("
                +"6, 'Otavio')");

        db.execSQL("insert into clientes (_id, nome, empresa) values("
                +"1, 'Jefferson', 'Pixel Inc')");
        db.execSQL("insert into clientes (_id, nome, empresa) values("
                +"2, 'Maximo', 'York Research')");
        db.execSQL("insert into clientes (_id, nome, empresa) values("
                +"3, 'Gabriella', 'Faraday Co')");

        db.execSQL("insert into incidentes (_id, atendente, cliente, descricao, status, creation_time) values("
                +"1, 4, 2, 'Desc do problema', 'aberto', '2018-06-19 01:12:48')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table atendentes");
        db.execSQL("drop table clientes");
        db.execSQL("drop table incidentes");
        onCreate(db);
    }
}
