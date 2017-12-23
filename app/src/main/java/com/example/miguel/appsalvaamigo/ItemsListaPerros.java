package com.example.miguel.appsalvaamigo;

/**
 * Created by Miguel on 21/12/2017.
 */

public class ItemsListaPerros
{
    int imagen;
    String nombre;
    String edad;
    String raza;
    String tipo;

    public ItemsListaPerros(int imagen, String nombre, String edad, String raza, String tipo)
    {
        this.imagen = imagen;
        this.nombre = nombre;
        this.edad = edad;
        this.raza = raza;
        this.tipo = tipo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
