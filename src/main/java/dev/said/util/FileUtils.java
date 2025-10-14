package dev.said.util;

import dev.said.entity.Airplane;
import dev.said.entity.Passenger;
import dev.said.entity.Seat;
import dev.said.entity.SeatClass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String AIRPLANE_FILE_NAME = "airplane_state.txt";

    public static void saveAirplane(Airplane airplane) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(AIRPLANE_FILE_NAME))) {
            writer.write("AIRPLANE;" + airplane.getName() + ";" + airplane.getSeatsCount());
            writer.newLine();

            for (Seat seat : airplane.getSeats()) {
                writer.write("SEAT;" +
                    seat.getNumber() + ";" +
                    seat.getSeatClass() + ";" +
                    seat.isAvailable() + ";");

                Passenger p = seat.getPassenger();
                if (p != null) {
                    writer.write(p.getFirstName() + "," +
                        p.getLastName() + "," +
                        p.getAge() + "," +
                        p.getPassportSerial() + "," +
                        p.getPassportNumber());
                } else {
                    writer.write("null");
                }
                writer.newLine();
            }

            System.out.println("✅ Airplane saved successfully to " + AIRPLANE_FILE_NAME);
        } catch (IOException e) {
            System.out.println("❌ Error saving airplane: " + e.getMessage());
        }
    }

    public static Airplane loadAirplane() {
        File file = new File(AIRPLANE_FILE_NAME);
        if (!file.exists()) {
            System.out.println("⚠️ No saved data found, creating a new airplane...");
            return new Airplane("Default Plane", 0);
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            Airplane airplane = null;
            List<Seat> seats = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts[0].equals("AIRPLANE")) {
                    String name = parts[1];
                    int seatsCount = Integer.parseInt(parts[2]);
                    airplane = new Airplane(name, seatsCount);
                } else if (parts[0].equals("SEAT")) {
                    String number = parts[1];
                    SeatClass seatClass = SeatClass.valueOf(parts[2]);
                    Seat seat = getSeat(parts, number, seatClass);
                    seats.add(seat);
                }
            }

            if (airplane != null) {
                airplane.setSeats(seats);
                System.out.println("✅ Airplane loaded successfully from file");
                return airplane;
            } else {
                System.out.println("⚠️ File found but invalid format, creating a new airplane...");
                return new Airplane("Default Plane", 0);
            }

        } catch (Exception e) {
            System.out.println("❌ Error loading airplane: " + e.getMessage());
            return new Airplane("Default Plane", 0);
        }
    }

    private static Seat getSeat(String[] parts, String number, SeatClass seatClass) {
        boolean available = Boolean.parseBoolean(parts[3]);
        Passenger passenger = null;

        if (parts.length > 4 && !"null".equals(parts[4])) {
            String[] pData = parts[4].split(",");
            if (pData.length == 5) {
                passenger = new Passenger(
                    pData[0],
                    pData[1],
                    Integer.parseInt(pData[2]),
                    pData[3],
                    pData[4]
                );
            }
        }

        Seat seat = new Seat(number, seatClass, passenger, available);
        return seat;
    }
}
