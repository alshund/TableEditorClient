package searchStrategy.algoritms.studentNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 01.05.2017.
 */
public class StudentSnFnSearch implements SearchStrategy, Serializable {
    private String surname;
    private String firstName;

    public StudentSnFnSearch(String surname, String firstName) {
        this.surname = surname;
        this.firstName = firstName;
    }

    @Override
    public boolean execute(Student student) {
        String studentSn = student.getSurname();
        String studentFn = student.getFirstName();

        return surname.equals(studentSn) && firstName.equals(studentFn);
    }
}
