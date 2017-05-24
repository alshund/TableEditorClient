package searchStrategy.algoritms.fatherSalarySearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class FatherUpSearch implements SearchStrategy, Serializable {
    private Double upperLimit;

    public FatherUpSearch(Double upperLimit) {
        this.upperLimit = upperLimit;
    }

    @Override
    public boolean execute(Student student) {
        Double fatherSalary = student.getFather().getSalary();

        return fatherSalary <= upperLimit;
    }
}