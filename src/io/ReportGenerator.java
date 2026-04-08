package io;
import model.Course;
import model.Student;
import java.io.PrintWriter;

public class ReportGenerator {
    public void generateSummaryReport(Course course, String outputFile) {
        try (PrintWriter writer = new PrintWriter(outputFile)) {
            writer.println("GRADE SUMMARY REPORT");
            writer.println("Course: " + course.getCourseCode() + " - " + course.getCourseName());
            writer.println("Total Students: " + course.getStudents().size());
            writer.println("Class Average: " + String.format("%.1f", course.getClassAverage()));

            int a = 0, b = 0, c = 0, d = 0, f = 0;
            for (Student s : course.getStudents()) {
                String grade = s.getLetterGrade();
                if (grade.equals("A")) a++;
                else if (grade.equals("B")) b++;
                else if (grade.equals("C")) c++;
                else if (grade.equals("D")) d++;
                else if (grade.equals("F")) f++;
            }
            int total = course.getStudents().size();
            writer.println("Grade Distribution:");
            writer.println("A: " + a + " students (" + (total > 0 ? (a * 100 / total) : 0) + "%)");
            writer.println("B: " + b + " students (" + (total > 0 ? (b * 100 / total) : 0) + "%)");
            writer.println("C: " + c + " students (" + (total > 0 ? (c * 100 / total) : 0) + "%)");
            writer.println("D: " + d + " students (" + (total > 0 ? (d * 100 / total) : 0) + "%)");
            writer.println("F: " + f + " students (" + (total > 0 ? (f * 100 / total) : 0) + "%)");

            Student top = course.getTopStudent();
            writer.println("Top Student: " + (top != null ? top.getStudentId() + " - " + top.getName() + " (" + String.format("%.1f", top.calculateAverage()) + ")" : "None"));
        } catch (Exception e) {
            System.err.println("Report error: " + e.getMessage());
        }
    }

    public void generateErrorLog(String errorMessage, String logFile) {
        try (PrintWriter writer = new PrintWriter(logFile)) {
            writer.println("ERROR LOG - " + java.time.LocalDateTime.now());
            writer.println(errorMessage);
        } catch (Exception e) {
            System.err.println("Log error: " + e.getMessage());
        }
    }
}