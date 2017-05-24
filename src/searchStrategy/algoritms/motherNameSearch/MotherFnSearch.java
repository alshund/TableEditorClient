package searchStrategy.algoritms.motherNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class MotherFnSearch implements SearchStrategy, Serializable {
    private String firstName;

    public MotherFnSearch(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean execute(Student student) {
        String motherFn = student.getMother().getFirstName();

        return firstName.equals(motherFn);
    }
}
