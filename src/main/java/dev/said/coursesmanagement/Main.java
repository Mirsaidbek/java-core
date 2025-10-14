package dev.said.coursesmanagement;

public class Main {
    public static void main(String[] args) {
        CourseService courseService = getCourseService();

        courseService.markAttendance(101, 1, true);
        courseService.markAttendance(101, 1, false);
        courseService.markAttendance(101, 2, true);

        courseService.assignGrade(101, 1, 95);
        courseService.assignGrade(101, 2, 87);
        courseService.assignGrade(102, 3, 90);

        courseService.showCourseInfo(101);
        courseService.showCourseInfo(102);

        courseService.showGrades(101);
        courseService.showGrades(102);

        courseService.showAttendance(101);
        courseService.showAttendance(102);
    }

    private static CourseService getCourseService() {
        CourseService courseService = new CourseService();

        Tutor tutor1 = new Tutor(1, "Mr Smith");
        Tutor tutor2 = new Tutor(2, "Prof. Jack");

        courseService.createCourse(101, "IELTS", tutor1);
        courseService.createCourse(102, "Maths", tutor2);

        Student s1 = new Student(1, "Alice");
        Student s2 = new Student(2, "Bob");
        Student s3 = new Student(3, "Charlie");

        courseService.addStudent(s1, 101);
        courseService.addStudent(s2, 101);
        courseService.addStudent(s3, 102);
        return courseService;
    }
}