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
public class Passenger implements Serializable {
    private String firstName;
    private String lastName;
    private int age;
    private String passportSerial;
    private String passportNumber;
}

