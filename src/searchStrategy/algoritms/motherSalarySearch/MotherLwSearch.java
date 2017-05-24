package searchStrategy.algoritms.motherSalarySearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class MotherLwSearch implements SearchStrategy, Serializable {
    private Double lowerLimit;

    public MotherLwSearch(Double lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    @Override
    public boolean execute(Student student) {
        Double motherSalary = student.getMother().getSalary();

        return lowerLimit <= motherSalary;
    }
}