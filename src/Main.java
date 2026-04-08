import io.FileInputService;
import io.ReportGenerator;
import model.Course;
import model.Student;
import exception.InvalidDataException;
import thread.ResourceManager;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            Course course = new Course("CS244", "Advanced Programming");

            String[] files = {"data/students_1.txt", "data/students_2.txt", "data/students_3.txt"};

            FileInputService input = new FileInputService();
            ArrayList<Student> allStudents = input.readMultipleFiles(files);   

            for (Student s : allStudents) {
                course.addStudent(s);
            }

            ReportGenerator rg = new ReportGenerator();
            rg.generateSummaryReport(course, "reports/summary_report.txt");
            rg.generateErrorLog("Test error log", "reports/error.log");

            System.out.println("=== PROJECT COMPLETED SUCCESSFULLY ===");
            System.out.println(course.generateGradeReport());

            ResourceManager rm = new ResourceManager();
            rm.safeMethod();
            System.out.println("Deadlock prevented using ordered lock acquisition.");

        } catch (InvalidDataException e) {
            System.err.println("Data Error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("General Error: " + e.getMessage());
        }
    }
}