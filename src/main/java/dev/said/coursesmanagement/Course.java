package dev.said.coursesmanagement;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Course {
    int id;
    String title;
    Tutor tutor;
    List<Student> students = new ArrayList<>();
    Map<Integer, List<Boolean>> attendance = new HashMap<>(); // key - ид студента; value - присутствие
    Map<Integer, Integer> grades = new HashMap<>();           // key - ид студента; value - оценка

    public Course(int id, String title, Tutor tutor) {
        this.id = id;
        this.title = title;
        this.tutor = tutor;
        this.students = new ArrayList<>();
        this.grades = new HashMap<>();
    }


}
