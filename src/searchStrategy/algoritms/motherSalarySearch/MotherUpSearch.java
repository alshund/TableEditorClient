package searchStrategy.algoritms.motherSalarySearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class MotherUpSearch implements SearchStrategy, Serializable {
    private Double upperLimit;

    public MotherUpSearch(Double upperLimit) {
        this.upperLimit = upperLimit;
    }

    @Override
    public boolean execute(Student student) {
        Double motherSalary = student.getMother().getSalary();

        return motherSalary <= upperLimit;
    }
}
