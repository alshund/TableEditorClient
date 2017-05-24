package searchStrategy.algoritms.studentNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 01.05.2017.
 */
public class StudentPtSearch implements SearchStrategy, Serializable {
    private String patronymic;

    public StudentPtSearch(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean execute(Student student) {
        String studentPt = student.getPatronymic();

        return patronymic.equals(studentPt);
    }
}
