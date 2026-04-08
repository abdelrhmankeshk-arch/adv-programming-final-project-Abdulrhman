package processor;
import exception.InvalidGradeException;
import java.util.ArrayList;

public abstract class GradeProcessor {
    public abstract Object processGrades(ArrayList<Double> grades);

    public void validateGrades(ArrayList<Double> grades) throws InvalidGradeException {
        if (grades == null) return;
        for (Double grade : grades) {
            if (grade == null || grade < 0 || grade > 100) {
                throw new InvalidGradeException("Invalid grade: " + grade + ". Must be 0-100.");
            }
        }
    }
}