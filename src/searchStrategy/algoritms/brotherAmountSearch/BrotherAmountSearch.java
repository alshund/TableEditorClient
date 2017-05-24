package searchStrategy.algoritms.brotherAmountSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class BrotherAmountSearch implements SearchStrategy, Serializable {
    private Integer brotherAmount;

    public BrotherAmountSearch(Integer brotherAmount) {
        this.brotherAmount = brotherAmount;
    }

    @Override
    public boolean execute(Student student) {
        Integer studentBrAm = student.getBrothersAmount();

        return brotherAmount == studentBrAm;
    }
}
