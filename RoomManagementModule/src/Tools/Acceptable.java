/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Tools;
import java.time.LocalDate;
import java.util.Date;
/**
 *
 * @author ttuan
 */
public interface Acceptable {

    
    String NATIONAL_ID = "^\\d{12}$";
    String FULL_NAME ="^(?=.{2,50}$)\\p{L}+(?: \\p{L}+)+$";
    String PHONE_NUMBER = "^(03[2-9]|05[2-9]|07\\d|08[1-9]|09\\d)\\d{7}$";
    String GENDER = "^(?i)(Male|Female)$";
    String ROOM_ID = "^[A-Za-z][A-Za-z0-9]{0,4}$";
    String RENTAL_DAYS = "^[1-9]\\d*$";
    String CO_TENANT = "^$|^[A-Za-z][A-Za-z\\s]{1,24}$";
    String DATE = "^\\d{4}/(0[1-9]|1[0-2])/(0[1-9]|[12]\\d|3[01])$";
    String VALID_YN = "^(?i)(Y|N)$";
    
    static boolean isValid(String data, String pattern){
        return data != null && data.matches(pattern);
    }
    
}

