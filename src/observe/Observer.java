package observe;

import studentDataBase.Student;

import java.util.List;

/**
 * Created by shund on 07.05.2017.
 */
public interface Observer {
    void updateTable(List<Student> page);
    void updatePaging();
    void createNewTable();
    void printLog(String message);
}
