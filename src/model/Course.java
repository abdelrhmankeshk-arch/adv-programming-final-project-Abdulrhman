package model;
import exception.InvalidDataException;
import java.util.ArrayList;

/**
 * Course class - Association with Student
 */
public class Course {
    private String courseCode;
    private String courseName;
    private ArrayList<Student> students;

    public Course(String courseCode, String courseName) throws InvalidDataException {
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new InvalidDataException("Course code cannot be null or empty");
        }
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new InvalidDataException("Course name cannot be empty");
        }
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student s) {
        if (s != null) students.add(s);
    }

    public double getClassAverage() {
        if (students.isEmpty()) return 0.0;
        double sum = 0;
        for (Student s : students) sum += s.calculateAverage();
        return sum / students.size();
    }

    public Student getTopStudent() {
        if (students.isEmpty()) return null;
        Student top = students.get(0);
        for (Student s : students) {
            if (s.calculateAverage() > top.calculateAverage()) top = s;
        }
        return top;
    }

    public ArrayList<Student> getStudents() {
        return new ArrayList<>(students); // defensive copy
    }

    public String getCourseCode() { return courseCode; }
    public String getCourseName() { return courseName; }

    public String generateGradeReport() {
        StringBuilder sb = new StringBuilder();
        sb.append("GRADE SUMMARY REPORT\n");
        sb.append("Course: ").append(courseCode).append(" - ").append(courseName).append("\n");
        sb.append("Total Students: ").append(students.size()).append("\n");
        sb.append("Class Average: ").append(String.format("%.1f", getClassAverage())).append("\n");
        Student top = getTopStudent();
        sb.append("Top Student: ").append(top != null ? top.toString() : "None").append("\n");
        return sb.toString();
    }
}