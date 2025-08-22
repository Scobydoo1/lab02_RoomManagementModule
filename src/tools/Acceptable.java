/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author thanh
 */
public interface Acceptable {
     public static String nationalIdRegex = "^\\d{12}$"; // 12 digits
    public static String nameRegex = "^[a-zA-Z][a-zA-Z\\s]{1,24}$"; // 2-25 chars, start with letter
    public static String phoneRegex = "^(03[2-9]|086|09[6-8]|090|093|089|070|079|077|076|078|081|082|083|084|085|088|091|094|052|056|058|092)\\d{7}$";
    public static String roomIdRegex = "^[a-zA-Z]\\d{1,4}$"; // Letter followed by 1-4 digits
    public static String positiveIntegerRegex = "^[1-9]\\d*$";
    public static String genderRegex = "^(Male|Female|male|female)$";
    public static String anything = ".*";
    
    public static boolean isValid(String data, String regex) {
        return data.matches(regex);
    }
}
