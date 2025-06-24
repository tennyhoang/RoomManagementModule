/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ttuan
 */
public class Room {
    private String RoomID;
    private String RoomName;
    private String RoomType;
    private double DailyRate;
    private int Capacity;
    private String FurDescription;

    public Room(String RoomID, String RoomName, String RoomType, double DailyRate, int Capacity, String FurDescription) {
        this.RoomID = RoomID;
        this.RoomName = RoomName;
        this.RoomType = RoomType;
        this.DailyRate = DailyRate;
        this.Capacity = Capacity;
        this.FurDescription = FurDescription;
    }
    
    public Room(String[] parts) throws NumberFormatException {
        this.RoomID = parts[0].trim();
        this.RoomName = parts[1].trim();
        this.RoomType = parts[2].trim();
        this.DailyRate = Double.parseDouble(parts[3].trim());
        this.Capacity = Integer.parseInt(parts[4].trim());
        this.FurDescription = parts[5].trim();
    }
    
    public Room() {
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String RoomID) {
        this.RoomID = RoomID;
    }

    public String getRoomName() {
        return RoomName;
    }

    public void setRoomName(String RoomName) {
        this.RoomName = RoomName;
    }

    public String getRoomType() {
        return RoomType;
    }

    public void setRoomType(String RoomType) {
        this.RoomType = RoomType;
    }

    public double getDailyRate() {
        return DailyRate;
    }

    public void setDailyRate(double DailyRate) {
        this.DailyRate = DailyRate;
    }

    public int getCapacity() {
        return Capacity;
    }

    public void setCapacity( int Capacity) {
        this.Capacity = Capacity;
    }

    public String getFurDescription() {
        return FurDescription;
    }

    public void setFurDescription(String FurDescription) {
        this.FurDescription = FurDescription;
    }
     @Override
    public String toString() {
        return String.format("%-6s | %-15s | %-8s | %-6.2f | %-8d | %s",
                RoomID, RoomName, RoomType, DailyRate, Capacity, FurDescription);
    }

}
    
