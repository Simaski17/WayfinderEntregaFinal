package com.wayfinder.rinno.wayfinderentregafinal.model;

import java.util.ArrayList;

/**
 * Created by tecnova on 24-04-2017.
 */

public class Busqueda {
    public int opcion;
    public ArrayList<Stores> listTienda = new ArrayList<Stores>();

    public Busqueda(int opcion, ArrayList<Stores> listTienda){
        this.opcion = opcion;
        this.listTienda = listTienda;
    }

    public int getOpcion() {
        return opcion;
    }

    public void setOpcion(int opcion) {
        this.opcion = opcion;
    }

    public ArrayList<Stores> getListTienda() {
        return listTienda;
    }

    public void setListTienda(ArrayList<Stores> listTienda) {
        this.listTienda = listTienda;
    }
}
