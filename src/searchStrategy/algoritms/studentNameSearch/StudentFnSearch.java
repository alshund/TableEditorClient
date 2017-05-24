package searchStrategy.algoritms.studentNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 01.05.2017.
 */
public class StudentFnSearch implements SearchStrategy, Serializable {
    private String firstName;

    public StudentFnSearch(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public boolean execute(Student student) {
        String studentFn = student.getFirstName();

        return firstName.equals(studentFn);
    }
}
