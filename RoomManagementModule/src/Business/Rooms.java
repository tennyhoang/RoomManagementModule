/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Business;

import Model.Guest;
import Model.Room;
import Tools.Inputter;
import Tools.Acceptable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author ttuan
 */
public class Rooms extends HashMap<String, Room>{
    public static HashMap<String,Room> roommap = new HashMap<>() ;
    public static final String File_name = "data/Active_Room_List.txt";
    Scanner sc = new Scanner(System.in); 
    
    public HashMap<String,Room> readFromFile(){
        roommap.clear();
        int lineError = 0;
        int lineSuccess = 0;
        File f = new File(File_name);
        if (f.exists()){
            System.out.println("Cant found file");
        }
        
        try(BufferedReader br = new BufferedReader(new FileReader(f))){
            String line;
            while((line = br.readLine()) != null){
                if(line.trim().isEmpty()){
                    lineError++;
                    continue;
                }
                String parts[] = line.split(";",6);
                String RoomID = parts[0].trim();
                if(parts.length<6){
                    lineError++;
                    continue;
                }   
                Room r = new Room(parts);
                if(roommap.containsKey(RoomID)){
                    lineError++;
                    continue;
                }
                roommap.put(RoomID, r);
                lineSuccess++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Rooms.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Rooms.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(lineSuccess + " rooms successfully loaded.");
        System.out.println(lineError+ " entries failed");
        
        return null;

    }
    
    
    public void displayRoomList(){
        if(roommap.isEmpty()){
            System.out.println("Room list is currently empty, not loaded yet.");
            return;
        }
        
        System.out.println("Active Room List");
        System.out.printf("%-8s | %-16s| %-10s | %-6s | %10s | %-22s\n",
                "RoomID" ,"RoomName" , "Type" , "Rate" , "Capacity" , "Furniture" );
        System.out.println("---------+-----------------+------------+--------+------------+------------------------------------");
        for(Room r : roommap.values()){
            System.out.printf("%-8s | %-16s| %-10s | %-6s | %8s | %-20s\n" ,
                    r.getRoomID(),r.getRoomName(),r.getRoomType(),r.getDailyRate(),r.getCapacity(),r.getFurDescription());
        }
    }
   
    
    public void ListVacantRooms(HashMap<String, Guest> guestmap){
        if (roommap.isEmpty()){
            System.out.println("data not exits");
            return;
        }
        
        boolean found = false;
        
        System.out.println("\n============================== ACTIVE ROOM LIST ==============================");
        System.out.printf("%-8s | %-16s | %-10s | %-6s | %-8s | %-20s\n",
                          "RoomID", "RoomName", "Room Type", "Rate", "Capacity", "Furniture");
        System.out.println("---------+------------------+------------+--------+----------+---------------------------------");

        
            for (Room r : roommap.values()) {
                boolean trung = true;
                for (Guest g : guestmap.values()) {
                    if (g.getRoomID().equalsIgnoreCase(r.getRoomID())) {
                        trung = false;
                        break;
                    }   
                }

                if (trung) {
                    found = true;
                    System.out.printf("%-8s | %-16s | %-10s | %-6.2f | %8d | %-30s\n",
                            r.getRoomID(), r.getRoomName(), r.getRoomType(),
                            r.getDailyRate(), r.getCapacity(), r.getFurDescription());
                }
            }

        if(!found){
         System.out.println("All rooms have currently been rented out ------ no available  at the moment!.");
        }
        
    }
    public void MonRevenueReport(HashMap<String,Guest> guestmap){
        System.out.println("\n===== MONTHLY REVENUE REPORT =====");
        
        if (guestmap.isEmpty() && roommap.isEmpty()){
            System.out.println("There id no data on guests who have rented rooms.");
            return;
        }
        
        System.out.println("Enter month to report(MM/YYYY): ");
        String input = sc.nextLine().trim();
        YearMonth targetmonth = null;
        while(targetmonth != null){
            try{
                DateTimeFormatter fomatter = DateTimeFormatter.ofPattern("MM/yyyy");
                targetmonth = YearMonth.parse(input, fomatter);

            }catch(Exception e){
                System.out.printf("Invalid date format. Please use MM/YYYY:");
                break;
            }
        }
        
        Map<String,Double > tienthue = new TreeMap<>();
         
        for (Guest g : guestmap.values()){
            YearMonth guestmonth = YearMonth.from(g.getStartdate());
            
            if(guestmonth.equals(targetmonth)){
                Room r = roommap.get(g.getRoomID());
                if(r!= null){
                    double revenue = g.getNumberRental() * r.getDailyRate();
                    tienthue.put(r.getRoomID(), tienthue.getOrDefault(r.getRoomID(), 0.0) + revenue);
                }
            }
        }
        if (tienthue.isEmpty()){
            System.out.println("No revenue data available.");
            return ;
        }
        
        System.out.println("\nMonthly Revenue Report - '" + targetmonth.getMonthValue() + "/" + targetmonth.getYear() + "'");
        System.out.println("==========================================================================");
    
    
        System.out.printf("%-8s | %-16s | %-10s | %-6s | %-15s\n","RoomID", "RoomName","Room Type","DailyRate", "Amount");
        System.out.println("------------+-------------------");
        for (Map.Entry<String, Double> entry : tienthue.entrySet()) {
            double amount = entry.getValue();
            Room r = roommap.get(entry.getKey());
            System.out.printf("%-8s | %-16s | %-10s | %-9.2f | $%-14.2f\n",r.getRoomID(), r.getRoomName(), r.getRoomType(),r.getDailyRate(), amount);
        }
    }
    
        public void RevenueRoomType(HashMap<String,Guest> guestmap){
        if(roommap.isEmpty() || guestmap.isEmpty()){
            System.out.println("no data");
            return;
        }
        Set<String> loaiphong = new TreeSet<>();
        for(Room r : roommap.values()){
            loaiphong.add(r.getRoomType());
        }
        int i =1;
        for(String type : loaiphong){
            System.out.println(i+"."+type);
            i++;
        }
        boolean valid;
        do{
        String input = Inputter.getString("Enter your room type(press Enter to show all): ");
        
        Map<String , Double> giatien = new TreeMap<>();
        if (input.isEmpty()){
            for(Guest g : guestmap.values()){
                Room r  = roommap.get(g.getRoomID());
                if(r == null) continue;
                else{
                String type = r.getRoomType();
                double revenue = r.getDailyRate() * g.getNumberRental();
                giatien.merge(type, revenue, Double::sum);
                }
            }
            valid = true;
        }
        else { 
            boolean roomtype = false;
            for(Room r: roommap.values()){
                if(r.getRoomType().equalsIgnoreCase(input)){
                    roomtype = true;
                    break;
                }
            }
            valid = true;
            if(!roomtype){
                System.out.println("Room type not found. pleas try again");
                continue;
            }
            for(Guest g : guestmap.values()){
                Room r  = roommap.get(g.getRoomID());
                if(r == null) continue;
                else{
                String type = r.getRoomType();
                double revenue = r.getDailyRate() * g.getNumberRental();
                giatien.merge(type, revenue, Double::sum);
                }
            }
            valid = true;
        }
        
        
        if(giatien.isEmpty()){
            System.out.println("This month has't earn monny");
        }
        System.out.println("\nRevenue Report by Room Type");
        System.out.println("-------------------------------------------------");
        System.out.printf("%-15s | %-15s\n", "Room Type", "Amount");
        System.out.println("---------------+-----------------");
        for(Map.Entry<String, Double > entry : giatien.entrySet()){
            double amount = entry.getValue();
            String r = entry.getKey();
            System.out.printf("%-15s | $%-15s\n", r , amount);

        }
    }while(!valid);
}
}
