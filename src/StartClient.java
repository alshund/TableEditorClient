import dataSource.DataSource;
import studentTable.MainWindow;
import tableController.TableController;

/**
 * Created by shund on 18.05.2017.
 */
public class StartClient {
    public static void main(String[] arg) {
        DataSource dataSource = new DataSource();
        TableController tableController = new TableController(dataSource);
        new MainWindow(tableController);
    }
}
