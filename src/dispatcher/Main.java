/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dispatcher;
import business.Guests;
import business.Rooms;
import java.util.Scanner;
import models.Guest;
import tools.Inputter;
/**
 *
 * @author thanh
 */
public class Main {
  private static final int IMPORT_ROOMS = 1;
    private static final int DISPLAY_AVAILABLE = 2;
    private static final int ENTER_GUEST = 3;
    private static final int UPDATE_GUEST = 4;
    private static final int SEARCH_GUEST = 5;
    private static final int DELETE_GUEST = 6;
    private static final int LIST_VACANT = 7;
    private static final int MONTHLY_REVENUE = 8;
    private static final int REVENUE_BY_TYPE = 9;
    private static final int SAVE_GUEST = 10;
    private static final int CONVERT_NAME_TO_VIETNAMESE = 11;
    private static final int RETURN_MENU_FUNCTION = 12;
    private static final int EXIT = 13;
    private static final int RETURN_TO_MAIN = 2;
    
    // File constants
    private static final String ROOM_FILE = "Active_Room_List.txt";
    private static final String GUEST_FILE = "guestInfo.dat";
    
    // System components
    private static Inputter inputter;
    private static Rooms rooms;
    private static Guests guests;
    private static Scanner scanner;

    /**
     * Initialize system components and load data
     */
    private static void initializeSystem() {
        inputter = new Inputter();
        rooms = new Rooms(ROOM_FILE);
        guests = new Guests(GUEST_FILE);
        scanner = new Scanner(System.in);
    }

    /**
     * Display main menu options
     */
    private static void displayMainMenu() {
       System.out.println("\n----------ROOM MANAGEMENT SYSTEM------------");
        System.out.println("1. Import Room Data from Text File");
        System.out.println("2. Display Available Room List");
        System.out.println("3. Enter Guest Information");
        System.out.println("4. Update Guest Stay Information");
        System.out.println("5. Search Guest by National ID");
        System.out.println("6. Delete Guest Reservation Before Arrival");
        System.out.println("7. List Vacant Rooms");
        System.out.println("8. Monthly Revenue Report");
        System.out.println("9. Revenue Report by Room Type");
        System.out.println("10. Save Guest Information");
        System.out.println("11. Convert name to Vietnamese format");
        System.out.println("12. Return to Menu function");
        System.out.println("13. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Main menu loop
     */
    private static void runMainMenu() {
        int choice;
        do {
            displayMainMenu();
            choice = getMenuChoice();
            processMenuChoice(choice);
        } while (choice != EXIT);
    }

    /**
     * Function 1: Import Room Data from Text File
     * Handles room data import with user feedback
     */
    private static void handleImportRooms() {
        System.out.println("Importing room data from " + ROOM_FILE + "...");
        rooms.readFromFile();
    }

    /**
     * Function 2: Display Available Room List
     * Shows all loaded room data in formatted table
     */
    private static void handleDisplayAvailable() {
        rooms.showAll();
    }

    /**
     * Function 3: Enter Guest Information
     * Handles new guest registration with room validation
     */
    private static void handleEnterGuest() {
        int option = 0;
        do {
            Guest guest = inputter.inputGuest(false);
            
            // Check if room exists
            if (rooms.searchById(guest.getDesiredRoomId()) == null) {
                System.out.println("Room " + guest.getDesiredRoomId() + " does not exist!");
                continue;
            }
            
            guests.addNew(guest);
            
            System.out.println("1. Continue entering new guests");
            System.out.println("2. Return to the main menu");
            System.out.print("Enter your option: ");
            option = getMenuChoice();
        } while (option != RETURN_TO_MAIN);
    }

    /**
     * Function 4: Update Guest Stay Information
     * Handles guest information updates with validation
     */
    private static void handleUpdateGuest() {
        int option;
        do {
            System.out.print("Enter National ID: ");
            String nationalId = scanner.nextLine();
            Guest existingGuest = guests.searchById(nationalId);
            
            if (existingGuest == null) {
                System.out.println("No guest found with the requested ID!");
            } else {
                System.out.println("Current guest information:");
                existingGuest.display(rooms);
                
                Guest updatedGuest = inputter.inputGuest(true);
                updatedGuest.setNationalId(nationalId);
                guests.update(updatedGuest);
            }
            
            System.out.println("1. Continue updating guests");
            System.out.println("2. Return to the main menu");
            System.out.print("Enter your option: ");
            option = getMenuChoice();
        } while (option != RETURN_TO_MAIN);
    }

    /**
     * Function 5: Search Guest by National ID
     * Searches and displays guest information with room details
     */
    private static void handleSearchGuest() {

        System.out.print("Enter National ID: ");
        String nationalId = scanner.nextLine();
        Guest guest = guests.searchById(nationalId);
        
        if (guest == null) {
            System.out.println("No guest found with the requested ID '" + nationalId + "'");
        } else {
            guest.display(rooms);
        }
    }

    /**
     * Function 6: Delete Guest Reservation Before Arrival
     * Handles guest reservation cancellation with date validation
     */
    private static void handleDeleteGuest() {
//        System.out.print("Enter National ID: ");
//        String nationalId = scanner.nextLine();
//        guests.deleteGuestBeforeArrival(nationalId);
    }

    /**
     * Function 7: List Vacant Rooms
     * Shows all available/unoccupied rooms
     */
    private static void handleListVacant() {
//        rooms.showVacant();
    }

    /**
     * Function 8: Monthly Revenue Report
     * Generates revenue report for specific month/year
     */
    private static void handleMonthlyRevenue() {
//        try {
//            System.out.print("Enter month (1-12): ");
//            int month = Integer.parseInt(scanner.nextLine());
//            System.out.print("Enter year (YYYY): ");
//            int year = Integer.parseInt(scanner.nextLine());
//            
//            if (month < 1 || month > 12) {
//                System.out.println("Invalid month!");
//                return;
//            }
//            
//            rooms.monthlyRevenue(month, year, guests);
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid number format!");
//        }
    }

    /**
     * Function 9: Revenue Report by Room Type
     * Generates revenue report grouped by room type
     */
    private static void handleRevenueByType() {
//        System.out.print("Enter room type (or 'ALL' for all types): ");
//        String roomType = scanner.nextLine();
//        rooms.revenueByRoomType(roomType, guests);
    }

    /**
     * Function 10: Save Guest Information
     * Saves guest data to binary file
     */
    private static void handleSaveGuest() {
        guests.saveToFile();
    }
 private static void handleConvertName() {
        System.out.print("Enter National ID: ");
        String nationalId = scanner.nextLine();
        Guest guest = guests.searchById(nationalId);
        
        if (guest == null) {
            System.out.println("No guest found with the requested ID '" + nationalId + "'");
            return;
        }
        
        // Hiển thị thông tin khách hàng
        guest.display(rooms);
        
        // Hiển thị menu lựa chọn
        System.out.println("\nOptions:");
        System.out.println("1. Convert guest name to uppercase");
        System.out.println("2. Return to main menu");
        System.out.print("Enter your choice: ");
        
        int choice = getMenuChoice();
        
        if (choice == 1) {
            // Chuyển tên thành uppercase và hiển thị lại
            String originalName = guest.getFullName();
            String upperCaseName = originalName.toUpperCase();
            guest.setFullName(upperCaseName);
            
            System.out.println("\nGuest information after name conversion:");
            guest.display(rooms);
            
            // Cập nhật thông tin trong hệ thống
            guests.show(guests);
            System.out.println("Guest name has been converted to uppercase and updated in the system.");
        } else {
            System.out.println("Returning to main menu...");
        }
    }
    /**
     * Process user menu selection
     */
    private static void processMenuChoice(int choice) {
        switch (choice) {
            case IMPORT_ROOMS:
                handleImportRooms();
                break;
            case DISPLAY_AVAILABLE:
                handleDisplayAvailable();
                break;
            case ENTER_GUEST:
                handleEnterGuest();
                break;
            case UPDATE_GUEST:
                handleUpdateGuest();
                break;
            case SEARCH_GUEST:
                handleSearchGuest();
                break;
            case DELETE_GUEST:
                handleDeleteGuest();
                break;
            case LIST_VACANT:
                handleListVacant();
                break;
            case MONTHLY_REVENUE:
                handleMonthlyRevenue();
                break;
            case REVENUE_BY_TYPE:
                handleRevenueByType();
                break;
            case SAVE_GUEST:
                handleSaveGuest();
                break;
              case CONVERT_NAME_TO_VIETNAMESE:
                handleConvertName();
                break;
            case EXIT:
                System.out.println("Thank you for using Room Management System!");
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    public static void main(String[] args) {
        initializeSystem();
        runMainMenu();
    }
}
