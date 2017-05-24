package tableListener;

import tableController.TableController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shund on 22.05.2017.
 */
public class SetConnectionListener implements ActionListener {
    private TableController tableController;

    public SetConnectionListener(TableController tableController) {
        this.tableController = tableController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String ipAddress = JOptionPane.showInputDialog("Enter the server address");
        if (ipAddress != null) {
            tableController.setConnection(ipAddress);
        }
    }
}
