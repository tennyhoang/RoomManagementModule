/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author ttuan
 */
public class Guest implements Serializable{
    String NationalID;
    String Fname;
    LocalDate Birthdate;
    String Gender;
    String Phone;
    String DesiredRoomID;
    int NumberRental;
    LocalDate Startdate;
    String Namecotent;

    public Guest() {
    }
    
    public Guest(String NationalID, String Fname, LocalDate Birthdate, String Gender, String Phone, String RoomID, int NumberRental, LocalDate Startdate, String Namecotent) {
        this.NationalID = NationalID;
        this.Fname = Fname;
        this.Birthdate = Birthdate;
        this.Gender = Gender;
        this.Phone = Phone;
        this.DesiredRoomID = RoomID;
        this.NumberRental = NumberRental;
        this.Startdate = Startdate;
        this.Namecotent = Namecotent;
    }

    public String getNationalID() {
        return NationalID;
    }

    public void setNationalID(String NationalID) {
        this.NationalID = NationalID;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname = Fname;
    }

    public LocalDate getBirthdate() {
        return Birthdate;
    }

    public void setBirthdate(LocalDate Birthdate) {
        this.Birthdate = Birthdate;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getRoomID() {
        return DesiredRoomID;
    }

    public void setRoomID(String RoomID) {
        this.DesiredRoomID = RoomID;
    }

public int getNumberRental() {
    return NumberRental;
}

    public void setNumberRental(int NumberRental) {
        this.NumberRental = NumberRental;
    }

    public LocalDate getStartdate() {
        return Startdate;
    }

    public void setStartdate(LocalDate Startdate) {
        this.Startdate = Startdate;
    }

    public String getNamecotent() {
        return Namecotent;
    }

    public void setNamecotent(String Namecotent) {
        this.Namecotent = Namecotent;
    }

    @Override
    public String toString() {
        return String.format(
                "Guest NaID: %s\nFull Name: %s\nBirth: %s\nGender: %s\nPhone: %s\nRoomID: %s\nDays: %d\nStart: %s\nCo-tenant: %s",
                NationalID, Fname, Birthdate, Gender, Phone, DesiredRoomID, NumberRental, Startdate,
                (Namecotent == null || Namecotent.isEmpty()) ? "None" : Namecotent);
    }
}
