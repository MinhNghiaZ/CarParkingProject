/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkingsystem;

/**
 *
 * @author ASUS
 */
public class Fee {
    private double carFee=20000;
    private double motorFee=10000;
    private double bicycleFee=5000;

    public Fee(double carFee, double motorFee, double bicycleFee) {
        this.carFee = carFee;
        this.motorFee = motorFee;
        this.bicycleFee = bicycleFee;
    }

    public Fee() {
    }

    public double getCarFee() {
        return carFee;
    }

    public void setCarFee(double carFee) {
        this.carFee = carFee;
    }

    public double getMotorFee() {
        return motorFee;
    }

    public void setMotorFee(double motorFee) {
        this.motorFee = motorFee;
    }

    public double getBicycleFee() {
        return bicycleFee;
    }

    public void setBicycleFee(double bicycleFee) {
        this.bicycleFee = bicycleFee;
    }
    
}
