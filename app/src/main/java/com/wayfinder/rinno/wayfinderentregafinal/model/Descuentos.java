package com.wayfinder.rinno.wayfinderentregafinal.model;

/**
 * Created by tecnova on 02-04-2017.
 */

public class Descuentos {


    private String id;
    private String after;
    private String before;
    private String conditions;
    private String description;
    private String discountType;
    private String expirationDate;
    private String imgURL;
    private String imgURLWayfinder;
    private int percentaje;
    private int photo;
    private String storeNumber;
    private String title;
    private String estado;
    private int id_numerico;
    private String resumen;
    private String cupon;

    public Descuentos() {

    }

    public Descuentos(String description, String storeNumber, String title) {
        this.description = description;
        this.storeNumber = storeNumber;
        this.title = title;
    }

    public Descuentos(String description, String storeNumber, String title, String imgURLWayfinder, int percentaje) {
        this.description = description;
        this.storeNumber = storeNumber;
        this.title = title;
        this.imgURLWayfinder = imgURLWayfinder;
        this.percentaje = percentaje;
    }

    public Descuentos(int percentaje, String storeNumber) {
        this.percentaje = percentaje;
        this.storeNumber = storeNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Descuentos(String imgURLWayfinder) {
        this.imgURLWayfinder = imgURLWayfinder;
    }

    public Descuentos(int photo) {
        this.photo = photo;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public String getImgURLWayfinder() {
        return imgURLWayfinder;
    }

    public void setImgURLWayfinder(String imgURLWayfinder) {
        this.imgURLWayfinder = imgURLWayfinder;
    }

    public int getPercentaje() {
        return percentaje;
    }

    public void setPercentaje(int percentaje) {
        this.percentaje = percentaje;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getId_numerico() {
        return id_numerico;
    }

    public void setId_numerico(int id_numerico) {
        this.id_numerico = id_numerico;
    }

    public String getResumen() {
        return resumen;
    }

    public void setResumen(String resumen) {
        this.resumen = resumen;
    }

    public String getCupon() {
        return cupon;
    }

    public void setCupon(String cupon) {
        this.cupon = cupon;
    }
}
