package processor;
import exception.InvalidGradeException;
import exception.FileProcessingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GradeAnalyzer extends GradeProcessor {
    @Override
    public Object processGrades(ArrayList<Double> grades) {
        try {
            validateGrades(grades);
            if (grades == null || grades.isEmpty()) {
                return new HashMap<String, Double>();
            }
            double highest = Double.MIN_VALUE;
            double lowest = Double.MAX_VALUE;
            double sum = 0;
            for (Double g : grades) {
                if (g > highest) highest = g;
                if (g < lowest) lowest = g;
                sum += g;
            }
            double avg = sum / grades.size();
            double sumSq = 0;
            for (Double g : grades) {
                sumSq += Math.pow(g - avg, 2);
            }
            double stdDev = Math.sqrt(sumSq / grades.size());

            Map<String, Double> result = new HashMap<>();
            result.put("highest", highest);
            result.put("lowest", lowest);
            result.put("average", avg);
            result.put("stdDev", stdDev);
            return result;
        } catch (InvalidGradeException e) {
            throw new FileProcessingException("Grade validation failed", e);
        }
    }
}