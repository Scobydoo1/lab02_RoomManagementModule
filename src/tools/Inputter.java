/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;
import models.Guest;
import models.Room;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author thanh
 */
public class Inputter implements Acceptable {
  private Scanner scanner;

    public Inputter() {
        scanner = new Scanner(System.in);
    }

    /**
     * Generic input method with validation
     */
    public String input(String message, String errorMsg, String regex) {
        String result;
        boolean reInput = false;
        do {
            System.out.println(message);
            result = scanner.nextLine().trim();
            reInput = !Acceptable.isValid(result, regex);
            if (reInput) {
                System.out.println(errorMsg + ". Please re-enter...");
            }
        } while (reInput);
        return result;
    }

    /**
     * Input guest information with validation
     */
    public Guest inputGuest(boolean isUpdated) {
        Guest guest = new Guest();

        // Input National ID
        if (!isUpdated) {
            String message = "Enter National ID (12 digits):";
            String errorMsg = "National ID must be exactly 12 digits";
            guest.setNationalId(input(message, errorMsg, Acceptable.nationalIdRegex));
        }

        // Input Full Name
        String message = "Enter full name (2-25 characters, start with letter):";
        String errorMsg = "Name must be 2-25 characters and start with a letter";
        guest.setFullName(input(message, errorMsg, Acceptable.nameRegex));

        // Input Birth Date
        Date birthDate = inputDate("Enter birth date (dd/MM/yyyy):");
        guest.setBirthDate(birthDate);

        // Input Gender
        message = "Enter gender (Male/Female):";
        errorMsg = "Gender must be Male or Female";
        guest.setGender(input(message, errorMsg, Acceptable.genderRegex));

        // Input Phone Number
        message = "Enter phone number (10 digits, Vietnam format):";
        errorMsg = "Invalid phone format for Vietnam";
        guest.setPhoneNumber(input(message, errorMsg, Acceptable.phoneRegex));

        // Input Desired Room ID
        message = "Enter desired room ID (letter followed by digits, max 5 chars):";
        errorMsg = "Room ID must start with letter followed by digits";
        guest.setDesiredRoomId(input(message, errorMsg, Acceptable.roomIdRegex));

        // Input Rental Days
        int rentalDays = inputInt("Enter number of rental days:", "Must be positive integer");
        guest.setRentalDays(rentalDays);

        // Input Start Date
        Date startDate;
        Date today = new Date();
        do {
            startDate = inputDate("Enter start date (dd/MM/yyyy):");
            if (startDate.before(today) || startDate.equals(today)) {
                System.out.println("Start date must be in the future. Please re-enter...");
            }
        } while (startDate.before(today) || startDate.equals(today));
        guest.setStartDate(startDate);

        // Input Co-tenant Name (optional)
        System.out.println("Enter co-tenant name (optional, press Enter to skip):");
        String coTenant = scanner.nextLine().trim();
        guest.setCoTenantName(coTenant.isEmpty() ? null : coTenant);

        return guest;
    }

    /**
     * Input date with validation
     */
    public Date inputDate(String message) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        Date date = null;
        boolean validDate = false;
        
        do {
            try {
                System.out.println(message);
                String dateStr = scanner.nextLine().trim();
                date = sdf.parse(dateStr);
                validDate = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use dd/MM/yyyy format.");
            }
        } while (!validDate);
        
        return date;
    }

    /**
     * Input integer with validation
     */
    public int inputInt(String message, String errorMsg) {
        int result = 0;
        boolean validInput = false;
        
        do {
            try {
                String input = input(message, errorMsg, Acceptable.positiveIntegerRegex);
                result = Integer.parseInt(input);
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a positive integer.");
            }
        } while (!validInput);
        
        return result;
    }
}