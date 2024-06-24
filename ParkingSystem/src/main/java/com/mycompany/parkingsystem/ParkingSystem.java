/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.parkingsystem;

import javax.swing.JFrame;

/**
 *
 * @author ASUS
 */
public class ParkingSystem extends JFrame {

    public static void main(String[] args) {
        ManageSpace ms = new ManageSpace();
        ms.CreateSpace(20);
        MainFrame mf = new MainFrame();
        mf.setTitle("Parking Management App");
        mf.setVisible(true);
    }
}
