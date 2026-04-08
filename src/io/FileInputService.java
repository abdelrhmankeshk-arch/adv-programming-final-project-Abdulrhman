package io;
import model.Student;
import thread.StudentFileReader;
import thread.ThreadSafeGradeRepository;
import java.util.ArrayList;
import java.util.List;

public class FileInputService {
    public ArrayList<Student> readMultipleFiles(String[] filenames) throws Exception {
    ThreadSafeGradeRepository repo = new ThreadSafeGradeRepository();
    List<Thread> readers = new ArrayList<>();

    for (String file : filenames) {
        StudentFileReader reader = new StudentFileReader(file, repo);
        readers.add(reader);
        reader.start();
    }

    for (Thread r : readers) {
        r.join();
    }

    // حل بسيط عشان الـ GradeCalculatorTask تخلص شغلها
    Thread.sleep(1200);

    repo.setProductionDone();
    return new ArrayList<>(repo.getAllStudents());
    }
}