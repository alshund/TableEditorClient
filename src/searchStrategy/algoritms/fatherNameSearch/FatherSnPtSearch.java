package searchStrategy.algoritms.fatherNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Father;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class FatherSnPtSearch implements SearchStrategy, Serializable {
    private String surname;
    private String patronymic;

    public FatherSnPtSearch(String surname, String patronymic) {
        this.surname = surname;
        this.patronymic = patronymic;
    }

    @Override
    public boolean execute(Student student) {
        Father father = student.getFather();

        String fatherSn = father.getSurname();
        String fatherPt = father.getPatronymic();

        return surname.equals(fatherSn) && patronymic.equals(fatherPt);
    }
}
