package dev.said.service;

import dev.said.entity.Airplane;
import dev.said.entity.Passenger;
import dev.said.entity.Seat;
import dev.said.util.FileUtils;

import java.util.List;
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
            System.out.println("Seat not found!");
            return;
        }
        if (!seat.isAvailable()) {
            System.out.println(" Seat already booked!");
            return;
        }

        seat.setPassenger(passenger);
        seat.setAvailable(false);
        FileUtils.saveAirplane(airplane);
        System.out.println(" Seat " + seatNumber + " booked successfully for " + passenger.getFirstName());
    }

    public void cancelBooking(String seatNumber) {
        Seat seat = airplane.findSeatByNumber(seatNumber);
        if (seat == null) {
            System.out.println("Seat not found!");
            return;
        }
        if (seat.isAvailable()) {
            System.out.println("ERROR! Seat " + seatNumber + " is already available.");
            return;
        }

        seat.setPassenger(null);
        seat.setAvailable(true);
        FileUtils.saveAirplane(airplane);
        System.out.println("^_^ Booking for seat " + seatNumber + " has been cancelled.");
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
        System.out.println("X No passenger found with passport " + passportSerial + " " + passportNumber);
    }

    public void showSeatMap() {
        System.out.println("\n================ SEATS ================");

        int seatsPerRow = 5;
        int count = 0;

        for (Seat seat : airplane.getSeats()) {
            String status = seat.isAvailable() ? "(O)" : "(X)";
            System.out.print(seat.getNumber() + " : " + status + " | ");
            count++;

            if (count % seatsPerRow == 0) {
                System.out.println();
            }
        }

        System.out.println("\n(O) - available, (X) - booked");
        System.out.println("==========================================");
    }

    public void getMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("""
                    
                    ===== AIRPLANE BOOKING =====
                    1. Show seats
                    2. Book a seat
                    3. Cancel booking
                    4. Search passenger by passport
                    5. Search seat by number
                    6. Save and exit
                    """);
            System.out.print("> ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> showSeatMap();
                case 2 -> bookSeatMenu(scanner);
                case 3 -> {
                    System.out.print("Enter seat number to cancel: ");
                    String seatNum = scanner.nextLine();
                    cancelBooking(seatNum);
                }
                case 4 -> {
                    System.out.print("Passport serial: ");
                    String ps = scanner.nextLine();
                    System.out.print("Passport number: ");
                    String pn = scanner.nextLine();
                    searchPassengerByPassport(ps, pn);
                }
                case 5 -> {
                    System.out.print("Enter seat number: ");
                    String seatNum = scanner.nextLine();
                    getSeatInfo(seatNum, scanner);
                }
                case 6 -> {
                    FileUtils.saveAirplane(airplane);
                    System.out.println("Saved successfully");
                    return;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }

    private void getSeatInfo(String seatNum, Scanner scanner) {
        List<Seat> seats = airplane.getSeats();
        for (Seat seat : seats) {
            if (seat.getNumber().equalsIgnoreCase(seatNum)) {
                System.out.println("==== Seat Information ==== ");
                System.out.println("number: " + seat.getNumber());
                System.out.println("class: " + seat.getSeatClass());
                System.out.println("available: " + (seat.isAvailable() ? "Yes" : "No"));
                return;
            }
        }
        System.out.println("Something went wrong!/nSeat with given number [" + seatNum + "] was not found");
    }

    private void bookSeatMenu(Scanner scanner) {
        System.out.print("Enter seat number: ");
        String seatNumber = scanner.nextLine();

        System.out.print("First name: ");
        String fName = scanner.nextLine();
        System.out.print("Last name: ");
        String lName = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Passport serial: ");
        String passportSerial = scanner.nextLine();
        System.out.print("Passport number: ");
        String passportNumber = scanner.nextLine();

        Passenger passenger = new Passenger(fName, lName, age, passportSerial, passportNumber);
        bookSeat(seatNumber, passenger);
    }

}
