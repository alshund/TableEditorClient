package tableListener;

import studentTable.TableWithPaging;
import tableController.TableController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shund on 10.04.2017.
 */
public class NewTableListener implements ActionListener {
    TableWithPaging tableWithPaging;
    TableController tableController;

    public NewTableListener(TableWithPaging tableWithPaging, TableController tableController) {
        this.tableWithPaging = tableWithPaging;
        this.tableController = tableController;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        tableController.getModel().createNewTable();
        if (tableController.isConnected()) {
            tableWithPaging.getToolBar().setVisible(true);
        }
    }
}
