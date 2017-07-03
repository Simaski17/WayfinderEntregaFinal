package com.wayfinder.rinno.wayfinderentregafinal.model;

import java.util.List;

/**
 * Created by tecnova on 08-04-2017.
 */

public class Stores {
    private String category;
    private String description;
    private int floor;
    private String logo;
    private String name;
    private String nodeID;
    private String nodeIDForContextual;
    private String phone;
    private String road;
    private String storeImage;
    private String storeNumber;
    private String email;
    private String web;
    private List<String> tags = null;
    private String estado;

    //CONTEXTUAL
    private int percentaje;
    private String title;
    private float coordX;
    private float coordXContextual;
    private float coordY;
    private float coordYContextual;

    public Stores(){

    }

    public Stores(int floor, String name, String storeNumber, int percentaje, String title, float coordX, float coordY, float coordXContextual, float coordYContextual){
        this.floor = floor;
        this.name = name;
        this.storeNumber = storeNumber;
        this.percentaje = percentaje;
        this.title = title;
        this.coordX = coordX;
        this.coordY = coordY;
        this.coordX = coordXContextual;
        this.coordY = coordYContextual;
    }

    public Stores(String name){
        this.name = name;
    }

    public Stores(String name, String category){
        this.name = name;
        this.category = category;
    }

    public Stores(String name, String category, String storeNumber, String road, String phone, String nodeID, List<String> tags, int floor, String nodeIDForContextual, String email, String web, String storeImage){
        this.name = name;
        this.category = category;
        this.storeNumber = storeNumber;
        this.road = road;
        this.phone = phone;
        this.nodeID = nodeID;
        this.tags = tags;
        this.floor = floor;
        this.nodeIDForContextual = nodeIDForContextual;
        this.email = email;
        this.web = web;
        this.storeImage = storeImage;
    }

    public Stores(String category, String description, int floor, String logo, String name, String nodeID,
                  String nodeIDForContextual, String phone, String road, String storeImage, String storeNumber,
                  List<String> tags, String email, String web){
        this.category = category;
        this.description = description;
        this.floor = floor;
        this.logo = logo;
        this.name = name;
        this.nodeID = nodeID;
        this.nodeIDForContextual = nodeIDForContextual;
        this.phone = phone;
        this.road = road;
        this.storeImage = storeImage;
        this.storeNumber = storeNumber;
        this.tags = tags;
        this.email = email;
        this.web = web;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getNodeIDForContextual() {
        return nodeIDForContextual;
    }

    public void setNodeIDForContextual(String nodeIDForContextual) {
        this.nodeIDForContextual = nodeIDForContextual;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }


    public int getPercentaje() {
        return percentaje;
    }

    public void setPercentaje(int percentaje) {
        this.percentaje = percentaje;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public float getCoordX() {
        return coordX;
    }

    public void setCoordX(float coordX) {
        this.coordX = coordX;
    }

    public float getCoordXContextual() {
        return coordXContextual;
    }

    public void setCoordXContextual(float coordXContextual) {
        this.coordXContextual = coordXContextual;
    }

    public float getCoordY() {
        return coordY;
    }

    public void setCoordY(float coordY) {
        this.coordY = coordY;
    }

    public float getCoordYContextual() {
        return coordYContextual;
    }

    public void setCoordYContextual(float coordYContextual) {
        this.coordYContextual = coordYContextual;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}