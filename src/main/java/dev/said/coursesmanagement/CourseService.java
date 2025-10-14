package dev.said.coursesmanagement;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CourseService {

    Course currentCourse;
    List<Course> courses = new ArrayList<>();

    public CourseService(Course currentCourse) {
        this.currentCourse = currentCourse;
        this.courses = new ArrayList<>();
    }

    public CourseService(List<Course> courses) {
        this.courses = courses;
    }

    public void createCourse(Course course) {
        courses.add(course);
        System.out.println("Course added: " + course.getTitle());
    }

    public void createCourse(int courseId, String courseName, Tutor tutor) {
        Course course = new Course(courseId, courseName, tutor);
        courses.add(course);
        System.out.println("Course added: " + course.getTitle());
    }

    public void addStudent(Student student, int courseId) {

        Course course = findCourseById(courseId);
        if (course == null) {
            System.out.println("Course with id " + courseId + " does not exist.");
            return;
        }
        course.getStudents().add(student);
        course.getAttendance().put(student.getId(), new ArrayList<>());
        course.getGrades().put(student.getId(), 0);
        System.out.println("Student " + student.getName() + " added to course " + course.getTitle());
    }

    private Course findCourseById(int courseId) {
        for (Course course : courses) {
            if (course.getId() == courseId) {
                return course;
            }
        }
        return null;
    }

    private Student findStudentById(Course course, int studentId) {

        if (course == null) {
            return null;
        }

        for (Student student : course.getStudents()) {
            if (student.getId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public void markAttendance(int courseId, int studentId, boolean present) {
        Course course = findCourseById(courseId);
        if (course == null) {
            System.out.println("Course with id " + courseId + " does not exist.");
            return;
        }

        Student student = findStudentById(course, studentId);
        if (student == null) {
            System.out.println("Student with id " + studentId + " is not enrolled in this course.");
            return;
        }

        List<Boolean> attendanceList = course.getAttendance().get(student.getId());
        attendanceList.add(present);
    }

    public void assignGrade(int courseId, int studentId, int grade) {

        Course course = findCourseById(courseId);
        if (course == null) {
            System.out.println("Course with id " + courseId + " does not exist.");
            return;
        }

        Student student = findStudentById(course, studentId);
        if (student == null) {
            System.out.println("Student with id " + studentId + " is not enrolled in this course.");
            return;
        }

        course.getGrades().put(student.getId(), grade);
    }

    public void showCourseInfo(int courseId) {
        Course course = findCourseById(courseId);
        if (course == null) {
            System.out.println("Course with id " + courseId + " does not exist.");
            return;
        }

        System.out.println("Course: " + course.getTitle());
        System.out.println("Tutor: " + course.getTutor().getName());
        System.out.println("Students enrolled:");
        for (Student s : course.getStudents()) {
            System.out.println("- " + s.getName());
        }
    }

    public void showGrades(int courseId) {
        Course course = findCourseById(courseId);
        if (course == null) {
            System.out.println("Course with id " + courseId + " does not exist.");
            return;
        }

        System.out.println("\nGrades for course " + course.getTitle() + ":");
        for (Student s : course.getStudents()) {
            System.out.println(s.getName() + " => " + course.getGrades().get(s.getId()));
        }
    }

    public void showAttendance(int courseId) {
        Course course = findCourseById(courseId);
        if (course == null) {
            System.out.println("Course with id " + courseId + " does not exist.");
            return;
        }

        System.out.println("\nAttendance for course " + course.getTitle() + ":");
        for (Student s : course.getStudents()) {
            List<Boolean> records = course.getAttendance().get(s.getId());
            long presentCount = records.stream().filter(p -> p).count();
            System.out.println(s.getName() + " => attended " + presentCount + "/" + records.size() + " classes");
        }
    }
}