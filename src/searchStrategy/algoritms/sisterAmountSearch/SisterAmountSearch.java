package searchStrategy.algoritms.sisterAmountSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class SisterAmountSearch implements SearchStrategy, Serializable {
    private Integer sisterAmount;

    public SisterAmountSearch(Integer sisterAmount) {
        this.sisterAmount = sisterAmount;
    }

    @Override
    public boolean execute(Student student) {
        Integer studentStAm = student.getSistersAmount();

        return sisterAmount == studentStAm;
    }
}
