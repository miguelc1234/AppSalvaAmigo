package com.example.miguel.appsalvaamigo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class DetallePerros extends AppCompatActivity
{

    ImageView imagen;
    TextView txtNombre;
    TextView txtEdad;
    TextView txtTipo;
    TextView txtRaza;
    TextView txtDescripcion;
    long idPerro;

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_perros);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db = Conexion.getDb();

        Bundle extra = getIntent().getExtras();
        idPerro = extra.getLong("idPerro");

        imagen = (ImageView) findViewById(R.id.imgIconoDetalles);
        txtNombre = (TextView) findViewById(R.id.txtNombreDetalles);
        txtEdad = (TextView) findViewById(R.id.txtEdadDetalles);
        txtTipo = (TextView) findViewById(R.id.txtTipoDetalles);
        txtRaza = (TextView) findViewById(R.id.txtRazaDetalles);
        txtDescripcion = (TextView) findViewById(R.id.txtDescripcionDetalles);

        cargarPerro();

    }

    public void cargarPerro()
    {
        Cursor cursor = db.rawQuery("SELECT * FROM Perros WHERE id="+(idPerro+1), null);

        if(cursor.moveToFirst())
        {
            do
            {
                imagen.setImageResource(cursor.getInt(1));
                txtNombre.setText("Nombre: " + cursor.getString(2));
                txtEdad.setText("Edad: " + cursor.getString(3));
                txtTipo.setText("Tipo: " + cursor.getString(4));
                txtRaza.setText("Raza: " + cursor.getString(5));
                txtDescripcion.setText("Descripcion: " + cursor.getString(6));
            }while (cursor.moveToNext());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
