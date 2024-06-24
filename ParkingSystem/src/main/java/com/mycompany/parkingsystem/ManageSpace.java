/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parkingsystem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

/**
 *
 * @author ASUS
 */
public class ManageSpace implements Serializable {

    private static ArrayList<Space> spaceList = new ArrayList<>();
    private static ArrayList<Ticket> ticketList = new ArrayList<>();


    public ManageSpace() {
    }
    static Fee f = new Fee();

    public void ChangeFee(double car,double motor,double bicycle){
       f.setBicycleFee(bicycle);
       f.setCarFee(car);
       f.setMotorFee(motor);
    }
    public Fee returnFee(){
        return f;
    }
    //lay list
    public ArrayList<Space> GetSpaceList() {
        return spaceList;
    }

    public ArrayList<Ticket> GetTicketList() {
        return ticketList;
    }

    //dat id cho space
    public void CreateSpace(int n) {
        for (int i = 1; i <= n; i++) {
            String id = "s" + i;
            spaceList.add(new Space(id, "", false, null));
        }
    }

    //them space
    public void AddSpace() {
        String id = "s" + spaceList.size();
        spaceList.add(new Space(id, "", false, null));
    }

    //cho xe vo+lay ve
    public boolean addVehicle(Vehicle vehicle) {
        int count = 0;
        for (Space space : spaceList) {
            count++;
            if (!space.isIsOccupied()) {
                space.ParkVehicle(vehicle);
                ticketList.add(new Ticket("T" + System.currentTimeMillis(), vehicle, space.getId(), LocalDate.now()));
                return true;
            } else if (space.getVehicle().getLicensePlate().equals(vehicle.getLicensePlate())) {
                JOptionPane.showMessageDialog(null, "Vehicle already exist", "Failed", JOptionPane.ERROR_MESSAGE);
                return false;

            } else if (count == spaceList.size()) {
                JOptionPane.showMessageDialog(null, "All spaces are full", "Failed", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return false;
    }

    //xet ve + lay xe ra
    static Comparator<Space> compareID = (Space s1, Space s2) -> s1.getId().compareTo(s2.getId());
    static Comparator<Ticket> compareTicketID = (Ticket t1, Ticket t2) -> t1.getTicketId().compareTo(t2.getTicketId());

    public void RemoveVehicle(String ticketID) {
         int indexTicket = Collections.binarySearch(ticketList, new Ticket(ticketID, null, "", null), compareTicketID);
        if (indexTicket >= 0) {
            Ticket t = ticketList.get(indexTicket);
            int index = Collections.binarySearch(spaceList, new Space(t.getSpaceId(), "", true, null), compareID);
            double fee = FeeCaculate(t);
            JOptionPane.showMessageDialog(null, fee + " VND", "Fee", JOptionPane.INFORMATION_MESSAGE);
            if (index >= 0 && index < spaceList.size()) {
                spaceList.get(index).unParkVehicle();
            }
            ticketList.remove(indexTicket);
        } else {
            JOptionPane.showMessageDialog(null, "Not Found", "Failed", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void Delete(String ticketID) {
        int indexTicket = Collections.binarySearch(ticketList, new Ticket(ticketID, null, "", null), compareTicketID);
        Ticket t = ticketList.get(indexTicket);
        int index = Collections.binarySearch(spaceList, new Space(t.getSpaceId(), "", true, null), compareID);
        if (index >= 0 && index < spaceList.size()) {
                spaceList.get(index).unParkVehicle();
            }
        ticketList.remove(indexTicket);
    }

    public double FeeCaculate(Ticket t) {
        double fee = 0;
        String type = t.getVehicle().getClass().getSimpleName();
        Period date = Period.between(t.getDate(), LocalDate.now());
        double day = date.getDays();
        if (day == 0) {
            day++;
        }
        if (type.equals("Bicycle")) {
            fee = day * f.getBicycleFee();
        } else if (type.equals("MotorBike")) {
            fee = day * f.getMotorFee();
        } else {
            fee = day * f.getCarFee();
        }
        return fee;
    }

    //write+read file
    public void WriteFile(String filename) {
        try {
            FileOutputStream f = new FileOutputStream(filename);
            ObjectOutputStream oStream = new ObjectOutputStream(f);
            oStream.writeObject(spaceList);
            oStream.writeObject(ticketList);
            oStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ReadFile(String filename) {
        try {
            FileInputStream f = new FileInputStream(filename);
            ObjectInputStream inStream = new ObjectInputStream(f);
            spaceList = (ArrayList<Space>) inStream.readObject();
            ticketList = (ArrayList<Ticket>) inStream.readObject();
            inStream.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void searchBy(String type, String para) {
        boolean flag = false;
        switch (type) {
            case "Licence" -> {
                for (Ticket cd : ticketList) {
                    if (cd.getVehicle().getLicensePlate().equals(para)) {
                        JOptionPane.showMessageDialog(null, "Found ticket: " + cd.getTicketId() + "\nSpace ID: " + cd.getSpaceId() + "\nDate: " 
                                + cd.getDate() + "\nType Vehicle: " + cd.getVehicle().getClass().getSimpleName(), "Search Successfully", JOptionPane.INFORMATION_MESSAGE);
                        flag = true;
                        break;
                    }
                }
                if (!flag) {
                    JOptionPane.showMessageDialog(null, "Not found ticket: ", "Search Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
            case "Space ID" -> {
                for (Ticket cd : ticketList) {
                    if (cd.getSpaceId().equals(para)) {
                        flag = true;
                        JOptionPane.showMessageDialog(null, "Found ticket: " + cd.getTicketId() + "\nSpace ID: " + cd.getSpaceId() + "\nDate: " 
                                + cd.getDate() + "\nType Vehicle: " + cd.getVehicle().getClass().getSimpleName(), "Search Successfully", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
                if (!flag) {
                    JOptionPane.showMessageDialog(null, "Not found ticket: ", "Search Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}
