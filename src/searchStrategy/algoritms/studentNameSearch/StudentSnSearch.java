package searchStrategy.algoritms.studentNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 01.05.2017.
 */
public class StudentSnSearch implements SearchStrategy, Serializable {
    private String surname;

    public StudentSnSearch(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean execute(Student student) {
        String studentSn = student.getSurname();

        return surname.equals(studentSn);
    }
}
