package searchStrategy.algoritms.fatherSalarySearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class FatherLwSearch implements SearchStrategy, Serializable {
    private Double lowerLimit;

    public FatherLwSearch(Double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    @Override
    public boolean execute(Student student) {
        Double fatherSalary = student.getFather().getSalary();

        return lowerLimit <= fatherSalary;
    }
}