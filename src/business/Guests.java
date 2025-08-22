/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import models.Guest;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;

/**
 *
 * @author thanh
 */
public class Guests extends HashSet<Guest> implements Workable<Guest>, Serializable {

    private String pathFile;
    private boolean saved;

    public Guests(String pathFile) {
        this.pathFile = pathFile;
        this.saved = true;
        init();
    }

    private void init() {
        HashSet<Guest> result = readFromFile();
        this.clear();
        this.addAll(result);
    }

    public boolean isDuplicated(Guest guest) {
        return this.contains(guest);
    }
// * Function 3: Enter Guest Information

    @Override
    public void addNew(Guest t) {
         if (!this.isDuplicated(t)) {
            this.add(t);
            this.saved = false;
        } else {
            System.out.println("Error: Customer already exists!");
        }
    }
    
  
    
//* Function 4: Update Guest Stay Information

  @Override
    public void update(Guest guest) {
        Guest existingGuest = searchById(guest.getNationalId());
        if (existingGuest != null) {
            this.remove(existingGuest);
            this.add(guest);
        
            this.saved = false;
            System.out.println("Guest information updated for ID: " + guest.getNationalId());
        } else {
            System.out.println("No guest found with the requested ID!");
        }
    }

    @Override
    public Guest searchById(String id) {
          Guest result = null;
          for (Guest c : this) {
            if (c.getNationalId().equalsIgnoreCase(id)) {
                result = c;
            }
        }
        return result;
    }
 public void show(Set<Guest> list) { 
        System.out.println("-------------------------------------------------------------");
        System.out.format("%-15s | %-20s | %-12s | %-10s | %-8s%n", 
            "National ID", "Full Name", "Phone", "Room ID", "Days");
        System.out.println("-------------------------------------------------------------");
        for (Guest guest : list) {
            System.out.format("%-15s | %-20s | %-12s | %-10s | %-8d%n",
                guest.getNationalId(), guest.getFullName(), guest.getPhoneNumber(),
                guest.getDesiredRoomId(), guest.getRentalDays());
        }
        System.out.println("-------------------------------------------------------------");
    }

    @Override
    public void showAll() {
        show(this);
    }
     public HashSet<Guest> readFromFile() {
        HashSet<Guest> result = new HashSet<>();
        try {
            // B1. Tao file moi
            File f = new File(pathFile);
            if (!f.exists()) {
                return result;
            }

            // B2. Tao FileInputStream
            FileInputStream fis = new FileInputStream(f);

            // B3. Tao ObjectOutputStream
            ObjectInputStream ois = new ObjectInputStream(fis);

            // B4. Ghi file
            result = (Guests) ois.readObject();

            // B5. Dong cac object
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
 public Set<Guest> filterByNationalId(String nationalId) {
        Set<Guest> result = new HashSet<>();
        for (Guest guest : this) {
            if (guest.getNationalId().contains(nationalId)) {
                result.add(guest);
            }
        }
        return result;
    }

    public boolean isSaved() {
        return saved;
    }
    /**
     * Function 10: Save Guest Information - 50 LOC
     * Saves guest data to binary file with serialization
     */
    public void saveToFile() {
        if (this.saved) {
            return;
        }
        try {
            File f = new File(pathFile);
            if (!f.exists()) {
                f.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();

            this.saved = true;
            System.out.println("Guest information has been successfully saved to \"" + pathFile + "\".");
        } catch (Exception e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }
 
}
//Read from file
// int successCount = 0;
//        int failCount = 0;
//        
//        try {
//            File f = new File(this.pathFile);
//            if (!f.exists()) {
//                System.out.println("File not found: \"" + pathFile + "\"");
//                return;
//            }
//            
//            FileReader fr = new FileReader(f);
//            BufferedReader br = new BufferedReader(fr);
//            String temp = "";
//            
//            // Read line by line and validate
//            while ((temp = br.readLine()) != null) {
//                Room room = dataToObject(temp);
//                if (room != null && !this.containsKey(room.getRoomId())) {
//                    this.put(room.getRoomId(), room);
//                    successCount++;
//                } else {
//                    failCount++;
//                }
//            }
//            br.close();
//            
//            System.out.println(successCount + " rooms successfully loaded.");
//            if (failCount > 0) {
//                System.out.println(failCount + " entries failed.");
//            }
//            
//        } catch (Exception e) {
//            System.out.println("Error reading file: " + e.getMessage());
//        }
//    }