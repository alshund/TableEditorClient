package searchStrategy.algoritms.fatherNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class FatherPtSearch implements SearchStrategy, Serializable {
    private String patronymic;

    public FatherPtSearch(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean execute(Student student) {
        String fatherPt = student.getFather().getPatronymic();

        return patronymic.equals(fatherPt);
    }
}
