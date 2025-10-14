package dev.said.coursesmanagement;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends User {

    public Student(int id, String name) {
        super(id, name);
    }

    @Override
    public void displayRole() {
        System.out.println("User with id: " + this.getId() + " is student");
    }
}
