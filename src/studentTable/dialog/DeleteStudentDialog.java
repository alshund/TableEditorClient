package studentTable.dialog;

import selectStrategy.SelectStrategy;
import selectStrategy.algoritms.*;
import tableController.TableController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by shund on 25.04.2017.
 */
public class DeleteStudentDialog {
    JDialog deleteStudentDialog;
    SearchPanel searchPanel;
    TableController tableController;

    public DeleteStudentDialog(JFrame mainFrame, TableController tableController) {
        this.tableController = tableController;

        deleteStudentDialog = new JDialog(mainFrame, "Delete student", true);
        deleteStudentDialog.setSize(350, 650);
        deleteStudentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        deleteStudentDialog.setLayout(new BorderLayout());
        deleteStudentDialog.setLocationRelativeTo(mainFrame);

        searchPanel = new SearchPanel();

        deleteStudentDialog.add(searchPanel.getSearchPanel(), BorderLayout.CENTER);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeleteButtonListener());
        deleteStudentDialog.add(deleteButton, BorderLayout.SOUTH);

        deleteStudentDialog.setVisible(true);
    }

    class DeleteButtonListener implements ActionListener {

        public DeleteButtonListener() {

        }

        @Override
        public void actionPerformed(ActionEvent event) {
            int recodesAmount = tableController.getDatabaseSize();
            SelectStrategy selectStrategy = chooseSelectStrategy();
            if (selectStrategy != null && selectStrategy.execute() != null) {
                if (tableController.isConnected()) {
                    tableController.deleteStudent(selectStrategy.execute());
                    recodesAmount = recodesAmount - tableController.getDatabaseSize();
                    String message = "Number of deleted records: " + recodesAmount + "!";
                    String tittle = "Deletion complete";
                    JOptionPane.showMessageDialog(deleteStudentDialog, message, tittle, JOptionPane.INFORMATION_MESSAGE);
                    deleteStudentDialog.dispose();
                } else {
                    String message = "There's no server connection!";
                    String tittle = "Error connection";
                    JOptionPane.showMessageDialog(deleteStudentDialog, message, tittle, JOptionPane.ERROR_MESSAGE);
                    deleteStudentDialog.dispose();
                }
            }
        }

        private SelectStrategy chooseSelectStrategy() {
            SelectStrategy selectStrategy = null;
            for (JRadioButton radioButton : searchPanel.getRadioButtonList()) {
                if (radioButton.isSelected()) {
                    if (radioButton.getName().equals("Student")) {
                        selectStrategy = new StudentNameSelect(deleteStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("Father")) {
                        selectStrategy = new FatherNameSelect(deleteStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("Mother")) {
                        selectStrategy = new MotherNameSelect(deleteStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("FatherSalary")) {
                        selectStrategy = new FatherSalarySelect(deleteStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("MotherSalary")) {
                        selectStrategy = new MotherSalarySelect(deleteStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("BrotherAmount")) {
                        selectStrategy = new BrotherAmountSelect(deleteStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("SisterAmount")) {
                        selectStrategy = new SisterAmountSelect(deleteStudentDialog, searchPanel);
                        break;
                    }
                }
            }
            return selectStrategy;
        }

    }
}
