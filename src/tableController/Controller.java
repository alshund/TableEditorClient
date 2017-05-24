package tableController;

import dataSource.DataSource;
import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.util.List;

/**
 * Created by shund on 21.05.2017.
 */
public interface Controller {
    boolean isConnected();
    void changePage(int currentPage, int recodesAmount);
    void addStudent(Student student);
    List<Student> findStudent(SearchStrategy searchStrategy);
    void deleteStudent(SearchStrategy searchStrategy);
    int getDatabaseSize();
    DataSource getModel();
}
