/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;
import Tools.Inputter;
import Tools.Acceptable;
import java.util.HashMap;
import Model.Guest;
import Model.Room;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ttuan
 */

public class Guests implements Serializable{
    private  final String FILE_NAME = "data/guestInfo.dat";
    private final HashMap<String,Guest> guestmap = new HashMap<>();
    private final HashMap<String,Room> roommap ;
    
    
    
    public Guests(HashMap<String,Room> roommap) {
    this.roommap = roommap;
    
}

    
    
    public HashMap<String, Guest> getGuestMap() {
        return guestmap;
        
    }
    
    public void EnterGuestInformation(){
        System.out.println("\n===== ENTER GUEST INFORMATION =====");
        String NaID = Inputter.getStringpattern("Enter your national ID: ", Acceptable.NATIONAL_ID);
        if(guestmap.containsKey(NaID)){
            System.out.println("Your national is exits.");
            return;
        }   
        String Fname = Inputter.getStringpattern("Enter your full name: ", Acceptable.FULL_NAME);
        LocalDate birthdate;
        do{
            birthdate = Inputter.getDate("Enter your birthdate(YYYY-MM-DD): ");
            if(birthdate.isAfter(LocalDate.now())){
                System.out.println("your birthdate can not be in the future. Please try again");
            }
            else if (birthdate.isBefore(LocalDate.now().minusYears(120))){
                System.out.println("Are u kiding me? Please try again");
            }
            else break;
        }while(true);
        String gender = Inputter.getStringpattern("Enter your gender(male/female): ", Acceptable.GENDER);
        String phone = Inputter.getStringpattern("Enter your phone: ", Acceptable.PHONE_NUMBER);
        String DeRoomID ;
                        do{
                            DeRoomID = Inputter.getStringpattern("Enter your desiredroomID: ", Acceptable.ROOM_ID).toUpperCase();
                            if (!roommap.containsKey(DeRoomID)){
                                System.out.println("your ID room "+ DeRoomID + "not exits.");
                            }
                            else break;
                        }while(true);        
        String renday = Inputter.getStringpattern("Enter number of rental days: ", Acceptable.RENTAL_DAYS);
        LocalDate startdate;
        do{
            startdate = Inputter.getDate("Enter date you want to stay: ");
            if(startdate.isBefore(LocalDate.now())){
                System.out.println("Start date must be a future date.");
            }
            else if(startdate.isAfter(LocalDate.now().plusYears(2))){
                System.out.println("Start date no more than 2 years old");
            }
            else break;
            
        }while(true);
        String coname = Inputter.getStringpattern("Enter name of co-tenant: " , Acceptable.CO_TENANT);
        
        boolean hople = true;
        for(Guest g : guestmap.values()){
            if(g.getRoomID().equals(DeRoomID)){
                LocalDate Astart = g.getStartdate();
                LocalDate Aend = g.getStartdate().plusDays(g.getNumberRental());

                LocalDate Bstart = startdate;
                LocalDate Bend = startdate.plusDays(Integer.parseInt(renday));
                if(!(Bend.isBefore(Astart) || (Bstart.isAfter(Aend)))){
                    hople = false;
                    break;
                }
            }
        }
        
        if (!hople){
            System.out.println("your room has been order before by another guest.");
            return;
        }
        
        Guest g = new Guest(NaID, Fname, birthdate, gender, phone, DeRoomID, 0, startdate, Fname);
        guestmap.put(g.getNationalID(), g);
        System.out.println("Guest registered successfully for room " + g.getRoomID());
        System.out.println("Rental from "+ g.getStartdate() + " for "+ g.getNumberRental()+ " days");
    }
    
    public void updateGuest(){
        System.out.println("\n======Update Guest ======");
        System.out.println("Current ID:");
        int i = 1;
        if(guestmap.size()<=5){
            for(Guest g : guestmap.values()){
                System.out.println( i + "." + g.getNationalID());
                i++;
            }
        }
        String NaID = Inputter.getStringpattern("Enter your national ID you want to update: ", Acceptable.NATIONAL_ID);
        Guest g = guestmap.get(NaID);
        if (g == null ){
            System.out.println("No guest found with that ID");
            return;
        }
        
        System.out.printf(g.toString());
        
        String fullname = Inputter.getStringpattern("Enter new full name: ", Acceptable.FULL_NAME) ;
        if(!fullname.isEmpty() && Acceptable.isValid(fullname, Acceptable.FULL_NAME)) g.setFname(fullname);
        
        LocalDate birthdatestr ;
                do{
                    birthdatestr = Inputter.getDate("Enter new your birthdate(YYYY/MM/DD): ");
                    if (birthdatestr.isAfter(LocalDate.now())){
                        System.out.println("Your birthdate must before now.");
                    }
                    else if(birthdatestr.isBefore(LocalDate.now().minusYears(120))){
                        System.out.println("your birthdate no logic.");
                    }
                    else{
                        g.setBirthdate(birthdatestr);
                        break;
                    }
                }while(true);
        String gender = Inputter.getString("Enter new gender (Enter to skip): ");
        if (!gender.isEmpty() && Acceptable.isValid(gender, Acceptable.GENDER)) g.setGender(gender);

        String phone = Inputter.getString("Enter new phone (Enter to skip): ");
        if (!phone.isEmpty() && Acceptable.isValid(gender, Acceptable.PHONE_NUMBER)) g.setPhone(phone);

        String DeRoomID ;
                do{
                    DeRoomID = Inputter.getStringpattern("Enter your desiredroomID: ", Acceptable.ROOM_ID).toUpperCase();
                    if (!roommap.containsKey(DeRoomID)){
                        System.out.println("your ID room "+ DeRoomID + "not exits.");
                    }
                    else{
                        g.setRoomID(DeRoomID);
                        break;
                    }
                }while(true);

        int days = Inputter.getInt("Enter new rental days (Enter <=0 to skip): ");
        if (days > 0) g.setNumberRental(days);

        LocalDate startdate;
                do{
                    startdate = Inputter.getDate("Enter your startdate(YYYY/MM/DD): ");
                    if (startdate.isBefore(LocalDate.now())){
                        System.out.println("Your birthdate must before now.");
                    }
                    else if(startdate.isAfter(LocalDate.now().plusYears(1))){
                        System.out.println("maximum dat phong truoc 1 nam thui");
                    }
                    
                    else{
                        g.setStartdate(startdate);
                        break;
                    }
                    
                }while(true);

        String coTenant = Inputter.getString("Enter new co-tenant name (Enter to skip): ");
        if (!coTenant.isEmpty() && Acceptable.isValid(coTenant,Acceptable.CO_TENANT)) g.setNamecotent(coTenant);
    
        System.out.println("Guest registered successfully for room " + g.getRoomID());
        System.out.println("Rental from " + g.getStartdate()+ " for " + g.getNumberRental());
        SaveGuest();
    }   
    
    public void SearchGuestByNaID(){
        System.out.println("\n===== SEARCH GUEST BY NATIONAL ID =====");
        String NaID = Inputter.getStringpattern("Enter your national ID you want to search: ", Acceptable.NATIONAL_ID);
        Guest g = guestmap.get(NaID);
        if(g == null){
            System.out.println("Your national can't found");
            return;
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Guest information [National ID: " + g.getNationalID() + "]");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println(" Full name      : " + g.getFname());
        System.out.println(" Phone number   : " + g.getPhone());
        System.out.println(" Birth Day      : " + g.getBirthdate());
        System.out.println(" Gender         : " + g.getGender());
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println(" Rental room    : " + g.getRoomID());
        System.out.println(" Check in       : " + g.getStartdate());
        System.out.println(" Rentals days   : " + g.getNumberRental());
        System.out.println(" Check out      : " + g.getStartdate().plusDays(g.getNumberRental()));
        System.out.println("-----------------------------------------------------------------------------------------------");
        
    }
    
    public void DeleteGuest(){
        System.out.println("\n===== DELETE GUEST RESERVATION BEFORE ARRIVAL =====");
        String NaID = Inputter.getStringpattern("Enter your national you want to delete: ", Acceptable.NATIONAL_ID);
        Guest g = guestmap.get(NaID);
        if (g == null && g.getStartdate().isAfter(LocalDate.now())){
            System.out.println("Booking details for ID" + g.getNationalID() + "could not be found");
        }
        if (g != null && g.getStartdate().isAfter(LocalDate.now())){
            System.out.println("The room booking for this guest cannot be cancelled");
        }
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println("Guest information [National ID: " + g.getNationalID() + "]");
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println(" Full name      : " + g.getFname());
        System.out.println(" Phone number   : " + g.getPhone());
        System.out.println(" Birth Day      : " + g.getBirthdate());
        System.out.println(" Gender         : " + g.getGender());
        System.out.println("-----------------------------------------------------------------------------------------------");
        System.out.println(" Rental room    : " + g.getRoomID());
        System.out.println(" Check in       : " + g.getStartdate());
        System.out.println(" Rentals days   : " + g.getNumberRental());
        System.out.println(" Check out      : " + g.getStartdate().plusDays(g.getNumberRental()));
        System.out.println("-----------------------------------------------------------------------------------------------");
        
        Room r = roommap.get(g.getRoomID());
        
        if(r == null){
            System.out.println("Error room null");
        }
        
        System.out.printf(" + ID       : %s\n", r.getRoomID());
        System.out.printf(" + Room     : %s\n", r.getRoomName());
        System.out.printf(" + Type     : %s\n", r.getRoomType());
        System.out.printf(" + Daly rate: %.0f$\n", r.getDailyRate());
        System.out.printf(" + Capacity : %d\n", r.getCapacity());
        System.out.printf(" + Furniture: %s\n", r.getFurDescription());
        String confirm = Inputter.getStringpattern("Are you sure about delete this guest: ", Acceptable.VALID_YN);
        if(confirm.equalsIgnoreCase("Y")){
            guestmap.remove(NaID);
            SaveGuest();
            System.out.println("The bookign associated with ID "+ g.getRoomID() + " has been successfully canceled.");
        }else{
            System.out.println("The room booking for this guest cannot be cancelled !");
        }
    }
    
    
    
    
    public void SaveGuest(){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))){
            oos.writeObject(guestmap);
            System.out.println("Save guest successfully");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Guests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Guests.class.getName()).log(Level.SEVERE, null, ex);
        }   
    }
    
    public void ReadfromFile(){
        File f = new File(FILE_NAME);
        if (!f.exists()) {
        System.out.println("Chưa có dữ liệu khách, sẽ tạo file mới khi SaveGuest() lần đầu.");
        return;
    }
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
            HashMap<String, Guest> loadmap = (HashMap<String, Guest>) ois.readObject();
            guestmap.clear();
            guestmap.putAll(loadmap);
        }catch (IOException ex) {
            Logger.getLogger(Guests.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Guests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    