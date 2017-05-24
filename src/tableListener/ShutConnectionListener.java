package tableListener;

import tableController.TableController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shund on 22.05.2017.
 */
public class ShutConnectionListener implements ActionListener {
    private TableController tableController;

    public ShutConnectionListener(TableController tableController) {
        this.tableController = tableController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tableController.shutConnection();
    }
}
