package model;
import exception.InvalidDataException;
import java.util.ArrayList;

/**
 * Student class - Encapsulation + constructor validation
 */
public class Student {
    private String studentId;
    private String name;
    private ArrayList<Double> grades;

    public Student(String studentId, String name, ArrayList<Double> grades) throws InvalidDataException {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new InvalidDataException("Student ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Student name cannot be empty");
        }
        this.studentId = studentId;
        this.name = name;
        this.grades = grades != null ? grades : new ArrayList<>();
    }

    public String getStudentId() { return studentId; }
    public String getName() { return name; }
    public ArrayList<Double> getGrades() { return grades; }

    public void setStudentId(String studentId) throws InvalidDataException {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new InvalidDataException("Student ID cannot be null or empty");
        }
        this.studentId = studentId;
    }

    public void setName(String name) throws InvalidDataException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidDataException("Student name cannot be empty");
        }
        this.name = name;
    }

    public void setGrades(ArrayList<Double> grades) {
        this.grades = grades != null ? grades : new ArrayList<>();
    }

    public double calculateAverage() {
        if (grades.isEmpty()) return 0.0;
        double sum = 0;
        for (Double g : grades) sum += g;
        return sum / grades.size();
    }

    public String getLetterGrade() {
        double avg = calculateAverage();
        if (avg >= 90) return "A";
        else if (avg >= 80) return "B";
        else if (avg >= 70) return "C";
        else if (avg >= 60) return "D";
        else return "F";
    }

    @Override
    public String toString() {
        return "Student [ID=" + studentId + ", Name=" + name + ", Avg=" + String.format("%.2f", calculateAverage()) + ", Grade=" + getLetterGrade() + "]";
    }
}