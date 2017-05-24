package searchStrategy.algoritms.studentNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 01.05.2017.
 */
public class StudentFnPtSearch implements SearchStrategy, Serializable {
    private String firstName;
    private String patronymic;

    public StudentFnPtSearch(String firstName, String patronymic) {
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    @Override
    public boolean execute(Student student) {
        String studentFn = student.getFirstName();
        String studentPt = student.getPatronymic();

        return firstName.equals(studentFn) && patronymic.equals(studentPt);
    }
}
