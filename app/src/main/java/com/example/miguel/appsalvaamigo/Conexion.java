package com.example.miguel.appsalvaamigo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Miguel on 21/12/2017.
 */

public class Conexion
{
    public static SQLiteDatabase db;

    public Conexion(Context context)
    {
        BaseDatos baseDatos = new BaseDatos(context, "SalvaAmigo", null, 1);
        db = baseDatos.getWritableDatabase();
    }

    public static SQLiteDatabase getDb() {
        return db;
    }
}
