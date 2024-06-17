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

/**
 *
 * @author ASUS
 */
public class ManageSpace implements Serializable {

    static ArrayList<Space> spaceList = new ArrayList<>();
    static ArrayList<Ticket> ticketList = new ArrayList<>();
    //lay list
    public ArrayList<Space> GetSpaceList(){
        return spaceList;
    }
    public ArrayList<Ticket> GetTicketList(){
        return ticketList;
    }

    //dat id cho space
    public void CreateSpace(int n) {
        for (int i = 0; i < n; i++) {
            String id = "s" + i;
            spaceList.add(new Space(id, "", false, null));
        }
    }

    //cho xe vo+lay ve
    public boolean addVehicle(Vehicle vehicle) {
        for (Space space : spaceList) {
            if (!space.isIsOccupied()) {
                space.ParkVehicle(vehicle);
                ticketList.add(new Ticket("T" + System.currentTimeMillis(), vehicle, space.getId(), LocalDate.now()));
                return true;
            }
        }
        return false;
    }

    //xet ve + lay xe ra
    static Comparator<Space> compareID = (Space s1, Space s2) -> s1.getId().compareTo(s2.getId());
    static Comparator<Ticket> compareTicketID = (Ticket t1, Ticket t2) -> t1.getTicketId().compareTo(t2.getTicketId());

    public boolean RemoveVehicle(String ticketID) {
        int indexTicket = Collections.binarySearch(ticketList, new Ticket(ticketID, null, "", null), compareTicketID);
        if (indexTicket >= 0) {
            Ticket t = ticketList.get(indexTicket);
            int index = Collections.binarySearch(spaceList, new Space(t.getSpaceId(), "", true, null), compareID);
            spaceList.get(index).unParkVehicle();
            return true;
        } else {
            return false;
        }

    }

    public double FeeCaculate(Ticket t) {
        double fee = 0;
        String type = t.getVehicle().getClass().getSimpleName();
        Period date = Period.between(t.getDate(), LocalDate.now());
        if (type.equals("Bicycle")) {
            fee = date.getDays()*5000;
        } else if (type.equals("MotorBike")) {
            fee = date.getDays() * 10000;
        } else {
            fee = date.getDays() * 20000;
        }
        return fee;
    }

    //write+read file
    public void WriteFileSpace(String filename) {
        try {
            FileOutputStream f = new FileOutputStream(filename);
            ObjectOutputStream oStream = new ObjectOutputStream(f);
            for (Space s : spaceList) {
                oStream.writeObject(s);
            }
            oStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ReadFileSpace(String filename) {
        try {
            FileInputStream f = new FileInputStream(filename);
            ObjectInputStream inStream = new ObjectInputStream(f);
            Space st = null;
            while ((st = (Space) inStream.readObject()) != null) {
                spaceList.clear();
                spaceList.add(st);
            }
            inStream.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void WriteFileTicket(String filename) {
        try {
            FileOutputStream f = new FileOutputStream(filename);
            ObjectOutputStream oStream = new ObjectOutputStream(f);
            for (Ticket s : ticketList) {
                oStream.writeObject(s);
            }
            oStream.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void ReadFileTicket(String filename) {
        try {
            FileInputStream f = new FileInputStream(filename);
            ObjectInputStream inStream = new ObjectInputStream(f);
            Ticket st = null;
            while ((st = (Ticket) inStream.readObject()) != null) {
                ticketList.clear();
                ticketList.add(st);
            }
            inStream.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
