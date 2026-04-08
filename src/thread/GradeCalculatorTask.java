package thread;
import model.Student;
import processor.GradeAnalyzer;

public class GradeCalculatorTask implements Runnable {
    private final Student student;
    private final ThreadSafeGradeRepository repository;

    public GradeCalculatorTask(Student student, ThreadSafeGradeRepository repository) {
        this.student = student;
        this.repository = repository;
    }

    @Override
    public void run() {
        try {
            GradeAnalyzer analyzer = new GradeAnalyzer();
            analyzer.processGrades(student.getGrades()); // polymorphism
            repository.addStudent(student);
            System.out.println("✓ Processed: " + student.getStudentId());
        } catch (Exception e) {
            System.err.println("Task error: " + e.getMessage());
        }
    }
}