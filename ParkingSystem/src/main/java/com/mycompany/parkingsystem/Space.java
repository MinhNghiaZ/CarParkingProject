/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkingsystem;

import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class Space implements Serializable{
    private String id;
    private String Type;
    private boolean isOccupied;
    private Vehicle vehicle;

    public Space(String id, String Type, boolean isOccupied, Vehicle vehicle) {
        this.id = id;
        this.Type = Type;
        this.isOccupied = isOccupied;
        this.vehicle = vehicle;
    }

    public Space() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public boolean isIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
    //chinh lai status cua space
    public void ParkVehicle(Vehicle vehicle){
        this.isOccupied = true;
        this.vehicle = vehicle;
    }
    public void unParkVehicle(){
        this.isOccupied = false;
        this.vehicle = null;
    }
}
