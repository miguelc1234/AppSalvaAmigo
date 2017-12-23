package com.example.miguel.appsalvaamigo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaComparte extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int posActual = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_comparte);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.addMarker(new MarkerOptions().position(new LatLng(4.676778, -74.048290)).title("Carrera 13 entre 93A y 93B"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(4.648943, -74.06165099999990)).title("Avenida Calle 63, Chapinero Central"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(4.657815, -74.093407)).title("Parque Simon Bolivar Bogotá"));

        LatLng centro = new LatLng(4.630271, -74.090962);
        mMap.addMarker(new MarkerOptions().position(centro).title("Carrera. 37 #24-67-Corferias"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(centro));

        CameraUpdate camUpd2 = CameraUpdateFactory.newLatLngZoom(new LatLng(4.630271, -74.090962), 12F);
        mMap.animateCamera(camUpd2);

        mMap.addMarker(new MarkerOptions().position(new LatLng(4.601992, -74.072113)).title("Carrera. 6 #15-88- Museo del Oro"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(4.595544, -74.077561)).title("Casa de Nariño-Bogotá"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(4.652067, -74.110232)).title("Centro Comercial Salitre Plaza"));



        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            //Toast.makeText(this, "" + Build.VERSION.SDK_INT, Toast.LENGTH_LONG).show();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
                return;
            }
        } else {
            int hasWriteContactsPermission = checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);

            if (hasWriteContactsPermission != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        1);
            } else if (hasWriteContactsPermission == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (1 == requestCode) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                    return;
                }
            }
        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
}
