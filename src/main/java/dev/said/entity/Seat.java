package dev.said.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Seat implements Serializable {
    private String number;
    private SeatClass seatClass;
    private Passenger passenger;
    private boolean available;
}
