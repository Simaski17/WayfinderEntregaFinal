package com.wayfinder.rinno.wayfinderentregafinal.model;

import java.util.List;

public class Alimentacion {


    private List<String> cafeterias;
    private List<String> chocolateriaYConfiteria;
    private List<String> heladerias;
    private List<String> names;
    private List<String> restoran;

    public Alimentacion(){

    }

    public Alimentacion(List<String> cafeterias, List<String> chocolateriaYConfiteria, List<String> heladerias, List<String> names, List<String> restoran){
        this.cafeterias = cafeterias;
        this.chocolateriaYConfiteria = chocolateriaYConfiteria;
        this.heladerias = heladerias;
        this.names = names;
        this.restoran = restoran;
    }

    public List<String> getCafeterias() {
        return cafeterias;
    }

    public void setCafeterias(List<String> cafeterias) {
        this.cafeterias = cafeterias;
    }

    public List<String> getChocolateriaYConfiteria() {
        return chocolateriaYConfiteria;
    }

    public void setChocolateriaYConfiteria(List<String> chocolateriaYConfiteria) {
        this.chocolateriaYConfiteria = chocolateriaYConfiteria;
    }

    public List<String> getHeladerias() {
        return heladerias;
    }

    public void setHeladerias(List<String> heladerias) {
        this.heladerias = heladerias;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public List<String> getRestoran() {
        return restoran;
    }

    public void setRestoran(List<String> restoran) {
        this.restoran = restoran;
    }
}