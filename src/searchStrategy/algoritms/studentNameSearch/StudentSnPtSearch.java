package searchStrategy.algoritms.studentNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 01.05.2017.
 */
public class StudentSnPtSearch implements SearchStrategy, Serializable {
    private String surname;
    private String patronymic;

    public StudentSnPtSearch(String surname, String patronymic) {
        this.surname = surname;
        this.patronymic = patronymic;
    }

    @Override
    public boolean execute(Student student) {
        String studentSn = student.getSurname();
        String studentPt = student.getPatronymic();

        return surname.equals(studentSn) && patronymic.equals(studentPt);
    }
}
