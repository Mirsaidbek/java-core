package dev.said.service;

import dev.said.entity.Airplane;
import dev.said.entity.Passenger;
import dev.said.entity.Seat;
import dev.said.util.FileUtils;

import java.util.Scanner;

public class BookingService {

    private Airplane airplane;

    public BookingService(Airplane airplane) {
        this.airplane = airplane;
    }

    public void showAvailableSeats() {
        System.out.println("\n=== AVAILABLE SEATS ===");
        for (Seat seat : airplane.getSeats()) {
            if (seat.isAvailable()) {
                System.out.println(seat.getNumber() + " (" + seat.getSeatClass() + ")");
            }
        }
    }

    public void showBookedSeats() {
        System.out.println("\n=== BOOKED SEATS ===");
        for (Seat seat : airplane.getSeats()) {
            if (!seat.isAvailable()) {
                System.out.println(seat.getNumber() + " (" + seat.getSeatClass() + ") → " +
                    seat.getPassenger().getFirstName() + " " + seat.getPassenger().getLastName());
            }
        }
    }

    public void bookSeat(String seatNumber, Passenger passenger) {
        Seat seat = airplane.findSeatByNumber(seatNumber);
        if (seat == null) {
            System.out.println("❌ Seat not found!");
            return;
        }
        if (!seat.isAvailable()) {
            System.out.println("❌ Seat already booked!");
            return;
        }

        seat.setPassenger(passenger);
        seat.setAvailable(false);
        FileUtils.saveAirplane(airplane);
        System.out.println("✅ Seat " + seatNumber + " booked successfully for " + passenger.getFirstName());
    }

    public void cancelBooking(String seatNumber) {
        Seat seat = airplane.findSeatByNumber(seatNumber);
        if (seat == null) {
            System.out.println("❌ Seat not found!");
            return;
        }
        if (seat.isAvailable()) {
            System.out.println("⚠️ Seat " + seatNumber + " is already available.");
            return;
        }

        seat.setPassenger(null);
        seat.setAvailable(true);
        FileUtils.saveAirplane(airplane);
        System.out.println("✅ Booking for seat " + seatNumber + " has been cancelled.");
    }

    public void searchPassengerByPassport(String passportSerial, String passportNumber) {
        System.out.println("\n=== SEARCH RESULTS ===");
        for (Seat seat : airplane.getSeats()) {
            Passenger p = seat.getPassenger();
            if (p != null && p.getPassportSerial().equalsIgnoreCase(passportSerial)
                && p.getPassportNumber().equalsIgnoreCase(passportNumber)) {
                System.out.println("Passenger found: " + p.getFirstName() + " " + p.getLastName() +
                    " — Seat: " + seat.getNumber() + " (" + seat.getSeatClass() + ")");
                return;
            }
        }
        System.out.println("❌ No passenger found with passport " + passportSerial + " " + passportNumber);
    }

    public void interactiveMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    
                    ===== AIRPLANE BOOKING MENU =====
                    1. Show available seats
                    2. Show booked seats
                    3. Book a seat
                    4. Cancel booking
                    5. Search passenger by passport
                    6. Save and exit
                    """);
            System.out.print("Choose option > ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> showAvailableSeats();
                case 2 -> showBookedSeats();
                case 3 -> {
                    System.out.print("Enter seat number: ");
                    String seatNum = scanner.nextLine();

                    System.out.print("First name: ");
                    String fn = scanner.nextLine();
                    System.out.print("Last name: ");
                    String ln = scanner.nextLine();
                    System.out.print("Age: ");
                    int age = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Passport serial: ");
                    String ps = scanner.nextLine();
                    System.out.print("Passport number: ");
                    String pn = scanner.nextLine();

                    Passenger p = new Passenger(fn, ln, age, ps, pn);
                    bookSeat(seatNum, p);
                }
                case 4 -> {
                    System.out.print("Enter seat number to cancel: ");
                    String seatNum = scanner.nextLine();
                    cancelBooking(seatNum);
                }
                case 5 -> {
                    System.out.print("Passport serial: ");
                    String ps = scanner.nextLine();
                    System.out.print("Passport number: ");
                    String pn = scanner.nextLine();
                    searchPassengerByPassport(ps, pn);
                }
                case 6 -> {
                    FileUtils.saveAirplane(airplane);
                    System.out.println("💾 Data saved. Exiting...");
                    return;
                }
                default -> System.out.println("❌ Invalid option!");
            }
        }
    }
}