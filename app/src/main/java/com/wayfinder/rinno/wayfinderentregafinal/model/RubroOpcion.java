package com.wayfinder.rinno.wayfinderentregafinal.model;

/**
 * Created by tecnova on 23-04-2017.
 */

public class RubroOpcion {

    private int opciones;
    String nombre;



    public RubroOpcion(int opciones, String nombre){
        this.opciones = opciones;
        this.nombre =  nombre;
    }

    public int getOpciones() {
        return opciones;
    }

    public void setOpciones(int opciones) {
        this.opciones = opciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
