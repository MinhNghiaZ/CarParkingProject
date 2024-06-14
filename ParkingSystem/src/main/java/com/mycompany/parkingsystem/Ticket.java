/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkingsystem;

/**
 *
 * @author ASUS
 */
public class Ticket {
    private String ticketId;
    private Vehicle vehicle;
    private String spaceId;
    private int numDay;

    public Ticket() {
    }

    public Ticket(String ticketId, Vehicle vehicle, String spaceId, int numDay) {
        this.ticketId = ticketId;
        this.vehicle = vehicle;
        this.spaceId = spaceId;
        this.numDay = numDay;
    }

    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getSpaceId() {
        return spaceId;
    }

    public void setSpaceId(String spaceId) {
        this.spaceId = spaceId;
    }

    public int getNumDay() {
        return numDay;
    }

    public void setNumDay(int numDay) {
        this.numDay = numDay;
    }
    
    
}
