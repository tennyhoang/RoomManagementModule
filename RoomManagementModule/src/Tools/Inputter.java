/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;

import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 *
 * @author ttuan
 */
public class Inputter {
    private static final Scanner sc = new Scanner(System.in);
    private static DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;
    
    public static String getStringpattern(String mess,String pattern){
        String input;
        do{
            System.out.printf(mess);
            input = sc.nextLine().trim();
            if(Acceptable.isValid(input, pattern)){
                return input;
            }
            else{
                System.out.println("Invalid input");
            }
        }while(true);
    }
    
    public static LocalDate getFutureDate(String message) {
        LocalDate date;
        while (true) {
            System.out.print(message);
            String input = sc.nextLine().trim();
            try{
            date = LocalDate.parse(input, format);
            if (date.isAfter(LocalDate.now())) return date;
            else System.out.println("Date must be in the future.");
            }catch (DateTimeParseException e){
                System.out.println("Invalid date format. Use yyyy-mm-dd");
            }
        }
    }
    
    public static LocalDate getDate(String mess){
        LocalDate date = null ;
        do{
            System.out.print(mess);
            String input = sc.nextLine().trim();
            try{
                date = LocalDate.parse(input, format);
            }catch(DateTimeParseException e){
                System.out.println("Error format.");
            }
        }while(date == null);
        return date;
    }
    
    
    public static String getString(String mess){
        System.out.printf(mess);
        return sc.nextLine().trim();
    }
    
    public static int getInt(String mess){
        int number;
        while(true){
            try {
                System.out.print(mess);
                number = Integer.parseInt(sc.nextLine().trim());
                return number;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }
    
    public static String getNonEmptyString(String mess){
        String input;
        do{
            System.out.print(mess);
            input =sc.nextLine().trim();
            if(!input.isEmpty()) return input;
            System.out.println("input cannot be empty.");
        }while(true);
    }
}
