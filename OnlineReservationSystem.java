package oasisinfobyte;
import java.util.HashMap;
import java.util.Scanner;
public class OnlineReservationSystem {
	    private static final HashMap<String, String> users = new HashMap<>(); // username, password
	    private static final HashMap<String, Reservation> reservations = new HashMap<>(); // PNR, Reservation

	    static {
	        // Adding some default users for login
	        users.put("user1", "pass1");
	        users.put("user2", "pass2");
	    }

	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        System.out.print("Enter username: ");
	        String username = scanner.nextLine();
	        System.out.print("Enter password: ");
	        String password = scanner.nextLine();

	        if (login(username, password)) {
	            System.out.println("Login successful!");
	            boolean running = true;
	            while (running) {
	                System.out.println("\n1. Make a Reservation");
	                System.out.println("2. Cancel Reservation");
	                System.out.println("3. Exit");
	                System.out.print("Select an option: ");
	                int choice = scanner.nextInt();
	                scanner.nextLine(); // consume newline

	                switch (choice) {
	                    case 1:
	                        makeReservation(scanner);
	                        break;
	                    case 2:
	                        cancelReservation(scanner);
	                        break;
	                    case 3:
	                        running = false;
	                        System.out.println("Exiting...");
	                        break;
	                    default:
	                        System.out.println("Invalid option. Try again.");
	                }
	            }
	        } else {
	            System.out.println("Login failed. Invalid username or password.");
	        }
	        scanner.close();
	    }

	    private static boolean login(String username, String password) {
	        return users.containsKey(username) && users.get(username).equals(password);
	    }

	    private static void makeReservation(Scanner scanner) {
	        System.out.print("Enter train number: ");
	        String trainNumber = scanner.nextLine();
	        System.out.print("Enter train name: ");
	        String trainName = scanner.nextLine();
	        System.out.print("Enter class type: ");
	        String classType = scanner.nextLine();
	        System.out.print("Enter journey date (YYYY-MM-DD): ");
	        String journeyDate = scanner.nextLine();
	        System.out.print("Enter departure place: ");
	        String departure = scanner.nextLine();
	        System.out.print("Enter destination place: ");
	        String destination = scanner.nextLine();

	        // Generate a random PNR
	        String pnr = "PNR" + (int) (Math.random() * 100000);
	        Reservation reservation = new Reservation(trainNumber, trainName, classType, journeyDate, departure, destination);
	        reservations.put(pnr, reservation);

	        System.out.println("Reservation successful! Your PNR is " + pnr);
	    }

	    private static void cancelReservation(Scanner scanner) {
	        System.out.print("Enter PNR to cancel: ");
	        String pnr = scanner.nextLine();

	        if (reservations.containsKey(pnr)) {
	            reservations.remove(pnr);
	            System.out.println("Reservation with PNR " + pnr + " has been cancelled.");
	        } else {
	            System.out.println("No reservation found with PNR " + pnr);
	        }
	    }
	}

	class Reservation {
	    private String trainNumber;
	    private String trainName;
	    private String classType;
	    private String journeyDate;
	    private String departure;
	    private String destination;

	    public Reservation(String trainNumber, String trainName, String classType, String journeyDate, String departure, String destination) {
	        this.trainNumber = trainNumber;
	        this.trainName = trainName;
	        this.classType = classType;
	        this.journeyDate = journeyDate;
	        this.departure = departure;
	        this.destination = destination;
	    }

	    @Override
	    public String toString() {
	        return "Train Number: " + trainNumber + "\nTrain Name: " + trainName + "\nClass: " + classType +
	                "\nJourney Date: " + journeyDate + "\nFrom: " + departure + "\nTo: " + destination;
	    }
	}


