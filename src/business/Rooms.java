/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import models.Guest;
import models.Room;

/**
 *
 * @author thanh
 */
public class Rooms extends HashMap<String, Room> implements Workable<Room> {

    private String pathFile;
    private boolean saved;

    public Rooms(String pathFile) {
        this.pathFile = pathFile;
        this.saved = true;
    }

    public Room dataToObject(String temp) {
        Room room = null;
        try {
            String[] data = temp.split(";");
            if (data.length == 6) {
                room = new Room();
                room.setRoomId(data[0].trim());
                room.setRoomName(data[1].trim());
                room.setRoomType(data[2].trim());
                room.setDailyRate(Double.valueOf(data[3].trim()));
                room.setCapacity(Integer.valueOf(data[4].trim()));
                room.setFurnitureDescription(data[5].trim());
            }
        } catch (Exception e) {
            room = null;
        }
        return room;
    }
//* Function 1: Import Room Data from Text File

    public void readFromFile() {
        int successCount = 0;
        int failCount = 0;
        
        try {
            File f = new File(this.pathFile);
            if (!f.exists()) {
                System.out.println("File not found: \"" + pathFile + "\"");
                return;
            }
            
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String temp = "";
            
            // Read line by line and validate
            while ((temp = br.readLine()) != null) {
                Room room = dataToObject(temp);
                if (room != null && !this.containsKey(room.getRoomId())) {
                    this.put(room.getRoomId(), room);
                    successCount++;
                } else {
                    failCount++;
                }
            }
            br.close();
            
            System.out.println(successCount + " rooms successfully loaded.");
            if (failCount > 0) {
                System.out.println(failCount + " entries failed.");
            }
            
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    public boolean isSaved() {
        return saved;
    }

    public boolean isDuplicated(Room room) {
        return this.containsKey(room.getRoomId());

    }

    @Override
    public void addNew(Room t) {
        if (!isDuplicated(t)) {
            this.put(t.getRoomId(), t);
            this.saved = false;
        } else {
            System.out.println("Error: Room already exists!");
        }
    }

    @Override
    public void update(Room t) {
        this.put(t.getRoomId(), t);
        this.saved = false;
    }

    @Override
    public Room searchById(String id) {
        return this.get(id);
    }
// * Function 2: Display Available Room List

    @Override
    public void showAll() {

        List<Room> list = new ArrayList<>(this.values());

        Collections.sort(list, new Comparator<Room>() {
            @Override
            public int compare(Room o1, Room o2) {
                    return Double.compare(o1.getDailyRate(), o2.getDailyRate());
            }
        });

        if (list.isEmpty()) {
            System.out.println("Room list is currently empty, not loaded yet.");
            return;
        }
        System.out.println("------------------------");
        System.out.println("List of Rooms:");
        System.out.println("------------------------");
        for (Room room : list) {
            System.out.format("%-15s: %s\n", "Room ID", room.getRoomId());
            System.out.format("%-15s: %s\n", "Room Name", room.getRoomName());
            System.out.format("%-15s: %s\n", "Type", room.getRoomType());
            System.out.format("%-15s: %.2f$\n", "Daily Rate", room.getDailyRate());
            System.out.format("%-15s: %d\n", "Capacity", room.getCapacity());
            System.out.format("%-15s: %s\n", "Furniture", room.getFurnitureDescription());
            System.out.println("------------------------");
        }
    }

////     * Function 7: List Vacant Rooms
//    public void showVacant() {
//        Collection<Room> vacantRooms = new java.util.ArrayList<>();
//
//        // Filter vacant rooms
//        for (Room room : this.values()) {
//            if (!room.isOccupied()) {
//                vacantRooms.add(room);
//            }
//        }
//
//        if (vacantRooms.isEmpty()) {
//            System.out.println("All rooms are currently rented out --- no availability at the moment!");
//        } else {
//            System.out.println("Available Room List");
//            show(vacantRooms);
//        }
//    }
////        * Function 8: Monthly Revenue Report
//        public void monthlyRevenue(int month, int year, business.Guests guests) {
//        System.out.format("Monthly Revenue Report - '%02d/%d'\n", month, year);
//        
//        if (guests.isEmpty()) {
//            System.out.println("There is no data on guests who have rented rooms.");
//            return;
//        }
//        
//        System.out.println("RoomID | Room Name | Room type | DailyRate | Amount");
//        double totalRevenue = 0;
//        
//        java.util.Calendar cal = java.util.Calendar.getInstance();
//        for (models.Guest guest : guests) {
//            cal.setTime(guest.getStartDate());
//            if (cal.get(java.util.Calendar.MONTH) + 1 == month && 
//                cal.get(java.util.Calendar.YEAR) == year) {
//                
//                Room room = this.searchById(guest.getDesiredRoomId());
//                if (room != null) {
//                    double amount = room.getDailyRate() * guest.getRentalDays();
//                    totalRevenue += amount;
//                    System.out.format("%s | %s | %s | %.2f | %.2f\n",
//                        room.getRoomId(), room.getRoomName(), room.getRoomType(),
//                        room.getDailyRate(), amount);
//                }
//            }
//        }
//        System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
//    }
////        * Function 9: Revenue Report by Room Type
//        public void revenueByRoomType(String roomType, business.Guests guests) {
//        System.out.println("Revenue Report by Room Type");
//        
//        if (!isValidRoomType(roomType)) {
//            System.out.println("Invalid room type!");
//            return;
//        }
//        
//        System.out.println("Room type | Amount");
//        java.util.Map<String, Double> revenueByType = new java.util.HashMap<>();
//        
//        for (models.Guest guest : guests) {
//            Room room = this.searchById(guest.getDesiredRoomId());
//            if (room != null && (roomType.equalsIgnoreCase("ALL") || 
//                                room.getRoomType().equalsIgnoreCase(roomType))) {
//                double amount = room.getDailyRate() * guest.getRentalDays();
//                revenueByType.put(room.getRoomType(), 
//                    revenueByType.getOrDefault(room.getRoomType(), 0.0) + amount);
//            }
//        }
//        
//        for (java.util.Map.Entry<String, Double> entry : revenueByType.entrySet()) {
//            System.out.format("%s | $%.2f\n", entry.getKey(), entry.getValue());
//        }
//    }

    private boolean isValidRoomType(String roomType) {
        if (roomType.equalsIgnoreCase("ALL")) {
            return true;
        }
        for (Room room : this.values()) {
            if (room.getRoomType().equalsIgnoreCase(roomType)) {
                return true;
            }
        }
        return false;
    }
}
