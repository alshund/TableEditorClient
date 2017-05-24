package searchStrategy.algoritms.motherNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class MotherPtSearch implements SearchStrategy, Serializable {
    private String patronymic;

    public MotherPtSearch(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public boolean execute(Student student) {
        String motherPt = student.getMother().getSurname();

        return patronymic.equals(motherPt);
    }
}
