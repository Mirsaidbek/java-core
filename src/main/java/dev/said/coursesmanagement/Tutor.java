package dev.said.coursesmanagement;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tutor extends User {
    int courseId;

    public Tutor(int id, String name) {
        super(id, name);
    }

    public Tutor(int id, String name, int courseId) {
        super(id, name);
        this.courseId = courseId;
    }

    @Override
    public void displayRole() {
        System.out.println("User with id: " + this.getId() + " is tutor of the course");
    }

    @Override
    public String toString() {
        return "User: [" +
            " \nid = " + getId() +
            "; \nname = " + getName() +
            "; \nrole = Tutor, \ncourseId = " + getCourseId() +
            "\n]";
    }
}
