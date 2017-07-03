package com.wayfinder.rinno.wayfinderentregafinal.model;

/**
 * Created by tecnova on 21-03-2017.
 */

public class MessageNew {

    private String mensaje;
    private int opcion;

    public MessageNew(String mensaje){
        this.mensaje = mensaje;
    }

    public MessageNew(int opcion){
        this.opcion = opcion;
    }

    public MessageNew(String mensaje, int opcion) {
        this.mensaje = mensaje;
        this.opcion = opcion;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }
}