package searchStrategy.algoritms.motherNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class MotherSnSearch implements SearchStrategy, Serializable {
    private String surname;

    public MotherSnSearch(String surname) {
        this.surname = surname;
    }

    @Override
    public boolean execute(Student student) {
        String motherSn = student.getMother().getSurname();

        return surname.equals(motherSn);
    }
}
