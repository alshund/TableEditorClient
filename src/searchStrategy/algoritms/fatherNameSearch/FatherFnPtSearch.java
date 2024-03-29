package searchStrategy.algoritms.fatherNameSearch;

import searchStrategy.SearchStrategy;
import studentDataBase.Father;
import studentDataBase.Student;

import java.io.Serializable;

/**
 * Created by shund on 08.05.2017.
 */
public class FatherFnPtSearch implements SearchStrategy, Serializable {
    private String firstName;
    private String patronymic;

    public FatherFnPtSearch(String firstName, String patronymic) {
        this.firstName = firstName;
        this.patronymic = patronymic;
    }

    @Override
    public boolean execute(Student student) {
        Father father = student.getFather();

        String fatherFn = father.getFirstName();
        String fatherPt = father.getPatronymic();

        return firstName.equals(fatherFn) && patronymic.equals(fatherPt);
    }
}
