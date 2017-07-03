package com.wayfinder.rinno.wayfinderentregafinal.model;

import java.util.List;

/**
 * Created by tecnova on 25-04-2017.
 */

public class PopupMapa {

    public String nombreTienda;
    public String numeroTienda;
    public String numeroTelefono;
    public String numeroId;
    public String email;
    public String web;
    public String storeImage;
    private List<String> tags = null;


    public PopupMapa(String nombreTienda, String numeroTienda, String numeroTelefono, String numeroId, String email, String web, String storeImage, List<String> tags){
        this.nombreTienda = nombreTienda;
        this.numeroTienda = numeroTienda;
        this.numeroTelefono = numeroTelefono;
        this.numeroId = numeroId;
        this.email = email;
        this.web = web;
        this.storeImage = storeImage;
        this.tags = tags;

    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getNumeroTienda() {
        return numeroTienda;
    }

    public void setNumeroTienda(String numeroTienda) {
        this.numeroTienda = numeroTienda;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
    }

    public String getNumeroId() {
        return numeroId;
    }

    public void setNumeroId(String numeroId) {
        this.numeroId = numeroId;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }
}
