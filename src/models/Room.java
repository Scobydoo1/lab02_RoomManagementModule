/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author thanh
 */
public class Room implements Serializable {
    private String roomId;
    private String roomName;
    private String roomType;
    private double dailyRate;
    private int capacity;
    private String furnitureDescription;
    private boolean isOccupied;

    public Room() {
        this.isOccupied = false;
    }

    public Room(String roomId, String roomName, String roomType, double dailyRate, 
                int capacity, String furnitureDescription) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomType = roomType;
        this.dailyRate = dailyRate;
        this.capacity = capacity;
        this.furnitureDescription = furnitureDescription;
        this.isOccupied = false;
    }

    // Getters and Setters
    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getFurnitureDescription() {
        return furnitureDescription;
    }

    public void setFurnitureDescription(String furnitureDescription) {
        this.furnitureDescription = furnitureDescription;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    @Override
    public String toString() {
        return "Room{" + "roomId=" + roomId + ", roomName=" + roomName + 
               ", roomType=" + roomType + ", dailyRate=" + dailyRate + 
               ", capacity=" + capacity + ", furnitureDescription=" + furnitureDescription + 
               ", isOccupied=" + isOccupied + '}';
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
        final Room other = (Room) obj;
        return Objects.equals(this.roomId, other.roomId);
    }

    public void display() {
        System.out.format("+ ID: %s\n", this.getRoomId());
        System.out.format("+ Room: %s\n", this.getRoomName());
        System.out.format("+ Type: %s\n", this.getRoomType());
        System.out.format("+ Daily rate: %.1f$\n", this.getDailyRate());
        System.out.format("+ Capacity: %d\n", this.getCapacity());
        System.out.format("+ Furniture: %s\n", this.getFurnitureDescription());
    }
}
