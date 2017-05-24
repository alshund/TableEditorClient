package searchStrategy.algoritms.fatherNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class FatherFnSearch implements SearchStrategy, Serializable {
    private String firstName;

    public FatherFnSearch(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean execute(Student student) {
        String fatherFn = student.getFather().getFirstName();

        return firstName.equals(fatherFn);
    }
}
