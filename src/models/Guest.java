/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import business.Guests;
import business.Rooms;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author thanh
 */
public class Guest implements Serializable {
    private String nationalId;
    private String fullName;
    private Date birthDate;
    private String gender;
    private String phoneNumber;
    private String desiredRoomId;
    private int rentalDays;
    private Date startDate;
    private String coTenantName;

    public Guest() {
    }

    public Guest(String nationalId, String fullName, Date birthDate, String gender,
                 String phoneNumber, String desiredRoomId, int rentalDays, 
                 Date startDate, String coTenantName) {
        this.nationalId = nationalId;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.desiredRoomId = desiredRoomId;
        this.rentalDays = rentalDays;
        this.startDate = startDate;
        this.coTenantName = coTenantName;
    }

    // Getters and Setters
    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDesiredRoomId() {
        return desiredRoomId;
    }

    public void setDesiredRoomId(String desiredRoomId) {
        this.desiredRoomId = desiredRoomId;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getCoTenantName() {
        return coTenantName;
    }

    public void setCoTenantName(String coTenantName) {
        this.coTenantName = coTenantName;
    }

    public Date getEndDate() {
        if (startDate == null) return null;
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDate);
        cal.add(Calendar.DATE, rentalDays);
        return cal.getTime();
    }

    @Override
    public String toString() {
        return "Guest{" + "nationalId=" + nationalId + ", fullName=" + fullName + 
               ", birthDate=" + birthDate + ", gender=" + gender + 
               ", phoneNumber=" + phoneNumber + ", desiredRoomId=" + desiredRoomId + 
               ", rentalDays=" + rentalDays + ", startDate=" + startDate + 
               ", coTenantName=" + coTenantName + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Guest other = (Guest) obj;
        return Objects.equals(this.nationalId, other.nationalId);
    }

    public void display(Rooms rooms) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Guest information [National ID: " + this.getNationalId() + "]");
        System.out.format("Full name: %s Phone number: %s\n", this.getFullName(), this.getPhoneNumber());
        System.out.format("Birth day: %s Gender: %s\n", 
                         sdf.format(this.getBirthDate()), this.getGender());
        System.out.format("Rental room: %s\n", this.getDesiredRoomId());
        System.out.format("Check in: %s Rental days: %d\n", 
                         sdf.format(this.getStartDate()), this.getRentalDays());
        System.out.format("Check out: %s\n", sdf.format(this.getEndDate()));
        
        Room room = rooms.searchById(this.getDesiredRoomId());
        if (room != null) {
            System.out.println("Room information:");
            room.display();
        }
    }
}