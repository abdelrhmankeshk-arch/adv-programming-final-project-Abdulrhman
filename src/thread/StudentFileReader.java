package thread;
import model.Student;
import exception.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentFileReader extends Thread {
    private final String filename;
    private final ThreadSafeGradeRepository repository;

    public StudentFileReader(String filename, ThreadSafeGradeRepository repository) {
        this.filename = filename;
        this.repository = repository;
    }

    @Override
    public void run() {
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length < 3) continue;

                String id = parts[0].trim();
                String name = parts[1].trim();
                ArrayList<Double> grades = new ArrayList<>();

                for (int i = 2; i < parts.length; i++) {
                    try {
                        double g = Double.parseDouble(parts[i].trim());
                        grades.add(g);
                    } catch (NumberFormatException e) {
                        throw new FileProcessingException("Invalid number format in " + filename, e);
                    }
                }

                Student s = new Student(id, name, grades);
                new Thread(new GradeCalculatorTask(s, repository)).start();
            }
        } catch (Exception e) {
            System.err.println("Error in file reader: " + e.getMessage());
        }
    }
}