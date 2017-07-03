package com.wayfinder.rinno.wayfinderentregafinal;



import com.wayfinder.rinno.wayfinderentregafinal.model.Descuentos;
import com.wayfinder.rinno.wayfinderentregafinal.model.Nodes;
import com.wayfinder.rinno.wayfinderentregafinal.model.Stores;

import java.util.ArrayList;

/**
 * Created by gekou on 6/3/2017.
 */

public class RGlobal

{
    public static FirebaseApplication appF;
    public static ArrayList<String> arrDescuentosPestanasBotones = new ArrayList<>();
    public static ArrayList<Stores> tiendas;
    public static ArrayList<Stores> categorias;

    public static boolean clickeado = false;
    public static boolean finalizado = false;
    public static boolean inMapa = false;

    public static ArrayList<Nodes> listaNodesID;
    public static ArrayList arregloLocationX;
    public static ArrayList arregloLocationY;


    public static ArrayList<Stores> tiendasPrimerPiso;
    public static ArrayList<Stores> tiendasSegundoPiso;
    public static ArrayList<Stores> tiendasTercerPiso;


    public  static ArrayList<Stores> listaRestauran;
    public  static ArrayList<Stores> listaCafeteria;
    public  static ArrayList<Stores> listaChocolateria;
    public  static ArrayList<Stores> listaHeladeria;
    public  static ArrayList<Stores> listaBuscarComida = new ArrayList<>();


    public static ArrayList<Descuentos> listaImagenesDescuento =  new ArrayList<>();
    public static ArrayList<Descuentos> listaDescuentos=  new ArrayList<>();
    public static ArrayList<Descuentos> listaDescuentosCategoria =  new ArrayList<>();



}
