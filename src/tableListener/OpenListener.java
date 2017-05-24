package tableListener;

import tableController.TableController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shund on 25.04.2017.
 */
public class OpenListener implements ActionListener {
    private TableController tableController;

    public OpenListener( TableController tableController) {
        this.tableController = tableController;
    }
    @Override
    public void actionPerformed(ActionEvent event) {
        String fileName = JOptionPane.showInputDialog("Enter the name of the file");
        if(fileName != null) {
            tableController.openAction(fileName);
        }
    }
}
