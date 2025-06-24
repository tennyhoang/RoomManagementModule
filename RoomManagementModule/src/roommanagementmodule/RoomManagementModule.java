/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package roommanagementmodule;

import Business.Guests;
import java.util.Scanner;
import Business.Rooms;
import Model.Guest;
import Model.Room;

/**
 *
 * @author ttuan
 */
public class RoomManagementModule {
  
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); 
        Rooms r = new Rooms();
        Guests g = new Guests(r.roommap);
        g.ReadfromFile();

        int choice;
        do{
        System.out.println("\n========== ROOM MANAGEMENT MODULE ==========");
        System.out.println("1.Import Room Data from Text File");
        System.out.println("2.Display Available Room List.");
        System.out.println("3.Enter Guest Information.");
        System.out.println("4.Update Guest Stay Information.");
        System.out.println("5.Search Guest by National ID.");
        System.out.println("6.Delete Guest Reservation Before Arrival.");
        System.out.println("7.List Vacant Rooms.");
        System.out.println("8.Monthly Revenue Report.");
        System.out.println("9.Revenue Report by Room Type.");
        System.out.println("10.Save Guest Information.");
        System.out.println("Orthers- Quit.");
        System.out.printf("Enter your choice : ");
        while(!sc.hasNextInt()){
            System.out.println("Invalid input. Enter again");
            sc.next();
        }
        choice = sc.nextInt();
        switch (choice) {
            case 1:
                r.readFromFile();
                break;
            case 2:
                r.displayRoomList();
                break;
            case 3:
                g.EnterGuestInformation();
                break;
            case 4:
                g.updateGuest();
                break;
            case 5:
                g.SearchGuestByNaID();
                break;
            case 6:
                g.DeleteGuest();
                break;
            case 7:
                r.ListVacantRooms(g.getGuestMap());
                break;
            case 8:
                r.MonRevenueReport(g.getGuestMap());
                break;
            case 9:
                r.RevenueRoomType(g.getGuestMap());
                break;
            case 10:
                g.SaveGuest();
                break;
            default:
                System.out.println("Exiting program. See ya!");
                break;
            }
        }while(choice >= 1 && choice <=10);
    }
}

