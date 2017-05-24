package tableController;

import dataSource.DataSource;
import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.util.List;

/**
 * Created by shund on 21.05.2017.
 */
public class SearchController implements Controller {
    private DataSource dataSource;

    public SearchController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public boolean isConnected() {
        return dataSource.isConnected();
    }

    @Override
    public void changePage(int currentPage, int recodesAmount) {
        dataSource.changeSearchPage(currentPage, recodesAmount);
    }

    @Override
    public void addStudent(Student student) {

    }

    @Override
    public List<Student> findStudent(SearchStrategy searchStrategy) {
        return null;
    }

    @Override
    public void deleteStudent(SearchStrategy searchStrategy) {

    }

    @Override
    public int getDatabaseSize() {
        return dataSource.getSearchListSize();
    }

    @Override
    public DataSource getModel() {
        return dataSource;
    }
}
