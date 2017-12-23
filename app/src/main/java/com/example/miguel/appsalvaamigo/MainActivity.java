package com.example.miguel.appsalvaamigo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;
import java.io.File;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{
    private final int requestCode = 20;
    private final String ruta_fotos = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/misfotos/";
    private File file = new File(ruta_fotos);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Conexion conexionDB = new Conexion(this);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        file.mkdirs();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_inicio)
        {

        }
        else if (id == R.id.nav_perros)
        {
            Intent intent = new Intent(MainActivity.this, Perros.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_comparte)
        {
            Intent intent = new Intent(MainActivity.this, Comparte.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_siguenos)
        {
            Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
            startActivityForResult(gallery, requestCode);
        }
        else if (id == R.id.nav_mapa)
        {
            Intent intent = new Intent(MainActivity.this, MapaComparte.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_contactame)
        {
            Intent intent = new Intent(MainActivity.this, Contactame.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(this.requestCode == requestCode && resultCode == RESULT_OK){
            //Bundle extras = data.getExtras();
            //Bitmap imageBitmap = (Bitmap) extras.get("data");

            Uri imageUri = data.getData();

            String strAsunto = "Fotografia";
            String strMensaje = "Foto de la adopcion.";

            String[] TO = {"hcharkaoui@americanassist.com"}; //aquí pon tu correo
            Intent intentEnviar = new Intent(Intent.ACTION_SEND);
            intentEnviar.setData(Uri.parse("mailto:"));
            intentEnviar.setType("text/plain");
            intentEnviar.putExtra(Intent.EXTRA_EMAIL, TO);
            intentEnviar.putExtra(Intent.EXTRA_SUBJECT, strAsunto);
            intentEnviar.putExtra(Intent.EXTRA_TEXT, strMensaje);
            intentEnviar.putExtra(Intent.EXTRA_STREAM, imageUri);
            intentEnviar.setType("image/jpg");

            try {
                startActivity(Intent.createChooser(intentEnviar, "Enviando Mensaje"));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(MainActivity.this,"No tienes clientes de email instalados.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
