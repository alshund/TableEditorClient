package studentTable.dialog;

import selectStrategy.SelectStrategy;
import selectStrategy.algoritms.*;
import studentDataBase.Student;
import studentTable.TableModel;
import studentTable.TableWithPaging;
import tableController.SearchController;
import tableController.TableController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shund on 22.04.2017.
 */
public class SearchStudentDialog {
    private JDialog searchStudentDialog;
    private SearchPanel searchPanel;

    private SearchController searchController;
    private TableWithPaging searchTable;

    private TableController tableController;


    public SearchStudentDialog(JFrame mainFrame, TableController tableController) {
        this.tableController = tableController;

        searchStudentDialog = new JDialog(mainFrame, "Search student", true);
        searchStudentDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        searchStudentDialog.setSize(1100, 650);
        searchStudentDialog.setLayout(new BorderLayout());
        searchStudentDialog.setLocationRelativeTo(mainFrame);

        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new GridBagLayout());

        searchPanel = new SearchPanel();
        addComponent(dialogPanel, searchPanel.getSearchPanel(), 0, 0, 1);


        searchController = new SearchController(tableController.getModel());
        searchTable = new TableWithPaging(searchController);

        searchTable.setTableModel(new TableModel(new ArrayList<Student>()));
        searchTable.getToolBar().setVisible(true);
        addComponent(dialogPanel, searchTable.getTableWithPaging(), 1, 0, 2);

        JButton btSearch = new JButton("Search");
        btSearch.addActionListener(new SearchButtonListener());
        addComponent(dialogPanel, btSearch, 0, 1, 3);

        searchStudentDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                searchController.getModel().removeTable(searchTable);
            }
        });

        searchStudentDialog.add(dialogPanel, BorderLayout.CENTER);
        searchStudentDialog.setVisible(true);
    }

    private void addComponent(JComponent container, JComponent component, int gridX, int gridY, int gridWidth) {
        Insets insets = new Insets(0, 0, 0, 0);
        GridBagConstraints gbc = new GridBagConstraints(gridX, gridY, gridWidth, 1, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                insets, 0, 0);
        container.add(component, gbc);
    }

    class SearchButtonListener implements ActionListener {
        List<Student> studentList;

        public SearchButtonListener() {
            studentList = new ArrayList<Student>();
        }

        @Override
        public void actionPerformed(ActionEvent event) {
            SelectStrategy selectStrategy = null;
            for (JRadioButton radioButton : searchPanel.getRadioButtonList()) {
                if (radioButton.isSelected()) {
                    if (radioButton.getName().equals("Student")) {
                        selectStrategy = new StudentNameSelect(searchStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("Father")) {
                        selectStrategy = new FatherNameSelect(searchStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("Mother")) {
                        selectStrategy = new MotherNameSelect(searchStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("FatherSalary")) {
                        selectStrategy = new FatherSalarySelect(searchStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("MotherSalary")) {
                        selectStrategy = new MotherSalarySelect(searchStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("BrotherAmount")) {
                        selectStrategy = new BrotherAmountSelect(searchStudentDialog, searchPanel);
                        break;
                    } else if (radioButton.getName().equals("SisterAmount")) {
                        selectStrategy = new SisterAmountSelect(searchStudentDialog, searchPanel);
                        break;
                    }
                }
            }
            if (selectStrategy != null && selectStrategy.execute() != null) {
                studentList = tableController.findStudent(selectStrategy.execute());
                searchTable.updatePaging();
//                dataSource.createNewTable(studentList);
                JOptionPane.showMessageDialog(searchStudentDialog, "Found " + studentList.size() + " students", "Search", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}

