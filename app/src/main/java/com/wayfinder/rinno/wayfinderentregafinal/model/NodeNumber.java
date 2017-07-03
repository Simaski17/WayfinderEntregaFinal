package com.wayfinder.rinno.wayfinderentregafinal.model;

/**
 * Created by tecnova on 10-04-2017.
 */

public class NodeNumber {
    private int x;
    private int y;
    private int floor;
    private int numeroNodo;
    private int number;


    public NodeNumber(int number){
        this.number = number;
    }

    public NodeNumber(int x, int y, int floor, int numeroNodo) {
        this.x = x;
        this.y = y;
        this.floor = floor;
        this.numeroNodo = numeroNodo;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getNumeroNodo() {
        return numeroNodo;
    }

    public void setNumeroNodo(int numeroNodo) {
        this.numeroNodo = numeroNodo;

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
