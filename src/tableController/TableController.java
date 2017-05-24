package tableController;

import dataSource.DataSource;
import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.util.List;

/**
 * Created by shund on 30.04.2017.
 */
public class TableController implements Controller {
    private DataSource dataSource;

    public TableController(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void changePage(int currentPage, int recodesAmount) {
        dataSource.changePage(currentPage, recodesAmount);
    }

    @Override
    public void addStudent(Student student) {
        dataSource.addStudent(student);
    }

    @Override
    public List<Student> findStudent(SearchStrategy searchStrategy) {
        return dataSource.findStudent(searchStrategy);
    }

    @Override
    public void deleteStudent(SearchStrategy searchStrategy) {
        dataSource.deleteStudent(searchStrategy);
    }

    @Override
    public int getDatabaseSize() {
        return dataSource.getDatabaseSize();
    }

    @Override
    public DataSource getModel() {
        return dataSource;
    }

    public void saveAction(String fileName) {
        dataSource.saveAction(fileName);
    }

    public void openAction(String fileName) {
        dataSource.openAction(fileName);
    }

    public void setConnection(String ipAddress) {
        dataSource.setConnection(ipAddress);
    }

    public void shutConnection() {
        dataSource.shutConnection();
    }

    public boolean isConnected() {
        return dataSource.isConnected();
    }
}
