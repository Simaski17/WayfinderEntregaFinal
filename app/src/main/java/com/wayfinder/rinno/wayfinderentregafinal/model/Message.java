package com.wayfinder.rinno.wayfinderentregafinal.model;

/**
 * Created by simaski on 03-02-17.
 */

public class Message {
    private int cont;
    private int rect;
    private int contNode;
    private String mensaje;
    private String stair;


    public Message(int rect){
        this.rect = rect;
    }

    public Message(String mensaje){
        this.mensaje = mensaje;
    }

    public Message(int cont, String stair, int contNode) {
        this.cont = cont;
        this.stair = stair;
        this.contNode = contNode;
    }



    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public String getStair() {
        return stair;
    }

    public void setStair(String stair) {
        this.stair = stair;
    }

    public int getRect() {
        return rect;
    }

    public void setRect(int rect) {
        this.rect = rect;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getContNode() {
        return contNode;
    }

    public void setContNode(int contNode) {
        this.contNode = contNode;
    }
}