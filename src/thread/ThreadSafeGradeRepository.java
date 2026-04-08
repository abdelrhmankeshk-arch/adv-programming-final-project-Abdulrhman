package thread;
import model.Student;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ThreadSafeGradeRepository {
    private final ConcurrentHashMap<String, Student> students = new ConcurrentHashMap<>();
    private boolean productionDone = false;

    public synchronized void addStudent(Student student) {
        if (student != null) {
            students.put(student.getStudentId(), student);
            notifyAll();
        }
    }

    public synchronized List<Student> getAllStudents() {
        while (students.isEmpty() && !productionDone) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return new ArrayList<>(students.values());
    }

    public synchronized void setProductionDone() {
        productionDone = true;
        notifyAll();
    }
}