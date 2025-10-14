package dev.said.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Airplane implements Serializable {
    private String name; // boeing 777; airbus .......
    private final int seatsCount; // 10% of it BUSINESS and 90% is ECONOMY
    List<Seat> seats = new ArrayList<>();


    public Airplane(String name, int seatsCount) {
        this.name = name;
        this.seatsCount = seatsCount;
        initializeSeats();
    }

    public Airplane(int seatsCount) {
        this.name = "Some Default Airplane";
        this.seatsCount = seatsCount;
        initializeSeats();
    }

    public void initializeSeats() {
        if (!seats.isEmpty()) return; // уже инициализировано

        int businessCount = (int) (seatsCount * 0.1);
        int economyCount = seatsCount - businessCount;

        int seatNumber = 1;

        for (int i = 0; i < businessCount; i++) {
            seats.add(new Seat("B" + seatNumber++, SeatClass.BUSINESS, null, true));
        }

        for (int i = 0; i < economyCount; i++) {
            seats.add(new Seat("E" + seatNumber++, SeatClass.ECONOMY, null, true));
        }

        System.out.println("✅ Initialized " + businessCount + " business and " + economyCount + " economy seats.");
    }

    public Seat findSeatByNumber(String seatNumber) {
        for (Seat seat : seats) {
            if (seat.getNumber().equalsIgnoreCase(seatNumber)) {
                return seat;
            }
        }
        return null;
    }


}
