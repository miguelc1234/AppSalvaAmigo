package com.example.miguel.appsalvaamigo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Miguel on 21/12/2017.
 */

public class BaseDatos extends SQLiteOpenHelper
{
    String perros = "CREATE TABLE Perros (id INTEGER PRIMARY KEY AUTOINCREMENT, imagen INTEGER, nombre TEXT, edad TEXT, raza TEXT, tipo TEXT, descripcion TEXT)";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(perros);
    }

    public BaseDatos(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Perros");
        sqLiteDatabase.execSQL(perros);
    }
}
