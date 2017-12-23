package com.example.miguel.appsalvaamigo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Contactame extends AppCompatActivity
{
    private boolean correoEnviado = false;

    EditText editNombre;
    EditText editApellido;
    EditText editCorreo;
    EditText editMensaje;

    RadioButton rdbApadrinar;
    RadioButton rdbAdoptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactame);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        editNombre = (EditText) findViewById(R.id.editNombre);
        editApellido = (EditText) findViewById(R.id.editApellido);
        editCorreo = (EditText) findViewById(R.id.editCorreo);
        editMensaje = (EditText) findViewById(R.id.editMensaje);

        rdbApadrinar = (RadioButton) findViewById(R.id.rdbApadrinar);
        rdbAdoptar = (RadioButton) findViewById(R.id.rdbAdoptar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_enviar);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String strNombre = editNombre.getText().toString();
                String strApellido = editApellido.getText().toString();
                String strCorreo = editCorreo.getText().toString();
                String strMensaje = editMensaje.getText().toString();


                if(strNombre.equalsIgnoreCase("")  ||
                        strApellido.equalsIgnoreCase("") ||
                        strCorreo.equalsIgnoreCase("") ||
                        strMensaje.equalsIgnoreCase(""))
                {
                    Toast.makeText(Contactame.this, "Debe diligenciar todos los campos", Toast.LENGTH_LONG).show();
                }
                else
                {
                    enviarCorreo();
                }
            }
        });


    }

    protected void enviarCorreo()
    {
        String strAsunto = "";
        String strMensaje = "";

        String[] TO = {"hcharkaoui@americanassist.com"}; //aquí pon tu correo
        Intent intentEnviar = new Intent(Intent.ACTION_SEND);
        intentEnviar.setData(Uri.parse("mailto:"));
        intentEnviar.setType("text/plain");
        intentEnviar.putExtra(Intent.EXTRA_EMAIL, TO);

        if (rdbApadrinar.isChecked())
        {
            strAsunto = rdbApadrinar.getText().toString();
        }
        else
        {
            strAsunto = rdbAdoptar.getText().toString();
        }

        intentEnviar.putExtra(Intent.EXTRA_SUBJECT, strAsunto);

        strMensaje = "Nombre: " + editNombre.getText() + "\n\n"
                    + "Apellido: " + editApellido.getText() + "\n\n"
                    + "Correo Electronico: " + editCorreo.getText() + "\n\n"
                    + "Tipo: " + strAsunto + "\n\n\n\n"
                    + editMensaje.getText() + "\n\n"
        ;

        intentEnviar.putExtra(Intent.EXTRA_TEXT, strMensaje);

        try {
            //startActivity(Intent.createChooser(intentEnviar, "Enviando Mensaje"));
            startActivityForResult(intentEnviar, 1);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Contactame.this,"No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && correoEnviado)
        {
            Toast.makeText(Contactame.this, "Gracias por pertenecer y aportar un granito de arenaGracias por pertenecer y aportar un granito de arena", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        correoEnviado = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
        correoEnviado = true;
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
