package searchStrategy.algoritms.motherSalarySearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class MotherLwUpSearch implements SearchStrategy, Serializable {
    private Double lowerLimit;
    private Double upperLimit;

    public MotherLwUpSearch(Double lowerLimit, Double upperLimit) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    @Override
    public boolean execute(Student student) {
        Double motherSalary = student.getMother().getSalary();

        return lowerLimit <= motherSalary && motherSalary <= upperLimit;
    }
}