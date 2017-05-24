package tableListener;

import tableController.TableController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shund on 25.04.2017.
 */
public class SaveListener implements ActionListener {
    private TableController tableController;

    public SaveListener(TableController tableController) {
        this.tableController = tableController;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        String fileName = JOptionPane.showInputDialog("Enter the name of the file");
        if (fileName != null) {
            System.out.println(fileName);
            tableController.saveAction(fileName);
        }
//        FileHandler fileHandler = new FileHandler(tableController);
//        try {
//            fileHandler.saveFile();
//        } catch (ParserConfigurationException e) {
//            e.printStackTrace();
//        } catch (TransformerException e) {
//            e.printStackTrace();
//        }
    }
}
