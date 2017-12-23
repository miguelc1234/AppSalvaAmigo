package com.example.miguel.appsalvaamigo;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Perros extends AppCompatActivity
{
    SQLiteDatabase db;
    ContentValues values = new ContentValues();
    ListView listaPerros;
    ArrayList<ItemsListaPerros> arrayListaPerros = new ArrayList<ItemsListaPerros>();
    String strCargarDatos = "";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perros);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        db = Conexion.getDb();
        listaPerros = (ListView) findViewById(R.id.listPerros);

        SharedPreferences prefe = getSharedPreferences("datos",Context.MODE_PRIVATE);
        strCargarDatos = prefe.getString("cargarDatos","");
        if(strCargarDatos == "")
        {
            insertarDatos();
            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor=preferencias.edit();
            editor.putString("cargarDatos", "datosCargados");
            editor.commit();
        }

        carrgaListaPerros();

        listaPerros.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long id)
            {
                long idPerro = listaPerros.getItemIdAtPosition(posicion);

                Intent intent = new Intent(Perros.this, DetallePerros.class);
                intent.putExtra("idPerro", idPerro);
                startActivity(intent);
            }
        });
    }

    public void insertarDatos()
    {
        values.clear();

        values.put("imagen", R.drawable.sasha);
        values.put("nombre", "Sacha");
        values.put("edad", "2 años");
        values.put("raza", "Labrador");
        values.put("tipo", "Hembra");
        values.put("descripcion", "Perra muy linda y obediente");
        db.insert("Perros", null, values);

        values.clear();

        values.put("imagen", R.drawable.negra);
        values.put("nombre", "Negra");
        values.put("edad", "10 Meses");
        values.put("raza", "No definido");
        values.put("tipo", "Hembra");
        values.put("descripcion", "Esterilizada y vacunada, tamaño mediano pequeño");
        db.insert("Perros", null, values);

        values.clear();

        values.put("imagen", R.drawable.linda);
        values.put("nombre", "Linda");
        values.put("edad", "6 Meses");
        values.put("raza", "No definido");
        values.put("tipo", "Hembra");
        values.put("descripcion", "Esterilizada y vacunada, para adopcion");
        db.insert("Perros", null, values);

        values.clear();

        values.put("imagen", R.drawable.canu);
        values.put("nombre", "Canu");
        values.put("edad", "14 años");
        values.put("raza", "Cruce yorkshire");
        values.put("tipo", "Macho");
        values.put("descripcion", "Canu sólo ha conocido a una dueña en 14 años, pero al final, ha perdido a su compañera humana y el pobre tiene que buscar otro hogar.\n" +
                "Se lleva bien con niños, con perros, hace sus necesidades en la calle y a pesar de su edad tiene Vuena\n" +
                "salud. Es muy cariñoso y tranquilo. ADÓPTALO!!!");
        db.insert("Perros", null, values);

        values.clear();

        values.put("imagen", R.drawable.bizcocho);
        values.put("nombre", "Bizcocho");
        values.put("edad", "2 Meses");
        values.put("raza", "Cruce de Grifon");
        values.put("tipo", "Macho");
        values.put("descripcion", "Mojicon, Bizcocho, Bica, Magdalena y Roscón estaban en un mercadillo, no paseando, si no que estaban siendo vendidos por unos indigentes, los cuales habían tirado a su mama a la basura. Por suerte una familia los recogió y nos ha pedido ayuda.");
        db.insert("Perros", null, values);

        values.clear();

        values.put("imagen", R.drawable.cati);
        values.put("nombre", "Cati");
        values.put("edad", "4 meses y 3 días");
        values.put("raza", "Cruce de aguas");
        values.put("tipo", "Hembra");
        values.put("descripcion", "Cati y Encarni son dos preciosas perritas, cruce de perro de aguas.\n" +
                "Estaban en la perrera y por suerte, an sido sacadas de allí. Unos cachorritos, no tendrían que pasar tan mal trago.\n" +
                "Necesitan un hogar donde las quieran y las mimen.");
        db.insert("Perros", null, values);

        values.clear();

        values.put("imagen", R.drawable.encarni);
        values.put("nombre", "Encarni");
        values.put("edad", "4 meses y 3 días");
        values.put("raza", "Cruce de aguas");
        values.put("tipo", "Hembra");
        values.put("descripcion", "Cati y Encarni son dos preciosas perritas, cruce de perro de aguas.\n" +
                "Estaban en la perrera y por suerte, an sido sacadas de allí. Unos cachorritos, no tendrían que pasar tan mal trago.\n" +
                "Necesitan un hogar donde las quieran y las mimen.");
        db.insert("Perros", null, values);

        values.clear();

        values.put("imagen", R.drawable.curro);
        values.put("nombre", "Curro");
        values.put("edad", "2 años y 3 meses");
        values.put("raza", "Cruce mediano");
        values.put("tipo", "Macho");
        values.put("descripcion", "Curro casi le mata su dueño de una paliza a palos y luego fue llevado a la perra, es solo un perro de dos años que desde que ha empezado su vida, ha sido maltratado, humillado, necesita acogida urgente o adopción, donde se le enseñe lo que es el cariño, una caricia, salir de paseo empezar a vivir la vida .¿Quieres ser tú?");
        db.insert("Perros", null, values);

        values.clear();

        values.put("imagen", R.drawable.magdalena);
        values.put("nombre", "Magdalena");
        values.put("edad", "1 mes y 21 días");
        values.put("raza", "Cruce de Grifon");
        values.put("tipo", "Hembra");
        values.put("descripcion", "Mojicon, Bizcocho, Bica, Magdalena y Roscón estaban en un mercadillo, no paseando, si no que estaban siendo vendidos por unos indigentes, los cuales habían tirado a su mama a la basura. Por suerte una familia los recogió y nos ha pedido ayuda");
        db.insert("Perros", null, values);

        values.clear();

        values.put("imagen", R.drawable.roscon);
        values.put("nombre", "Roscon");
        values.put("edad", "1 mes y 21 días");
        values.put("raza", "Cruce de Grifon");
        values.put("tipo", "Macho");
        values.put("descripcion", "Mojicon, Bizcocho, Bica, Magdalena y Roscón estaban en un mercadillo, no paseando, si no que estaban siendo vendidos por unos indigentes, los cuales habían tirado a su mama a la basura. Por suerte una familia los recogió y nos ha pedido ayuda");
        db.insert("Perros", null, values);
    }

    public void carrgaListaPerros()
    {
        arrayListaPerros.clear();

        Cursor cursor = db.rawQuery("SELECT * FROM Perros", null);

        if(cursor.moveToFirst())
        {
            do
            {
                arrayListaPerros.add(new ItemsListaPerros(cursor.getInt(1),"Nombre: " + cursor.getString(2), "Edad: " + cursor.getString(3),"Raza: " + cursor.getString(4),"Tipo: " + cursor.getString(5)));
            }while (cursor.moveToNext());
        }

        listaPerros.setAdapter(new ListaPerrosAdapter(Perros.this, arrayListaPerros));
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
