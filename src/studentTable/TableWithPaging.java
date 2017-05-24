package studentTable;

import constants.Table;
import observe.Observer;
import studentDataBase.Student;
import tableController.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by shund on 11.04.2017.
 */
public class TableWithPaging implements Observer {
    public static final int SPINNER_WIDTH = 50;
    public static final int SPINNER_HEIGHT = 32;
    private Controller controller;

    private JPanel tableWithPaging;
    private JTable table;
    private TableModel tableModel;

    private SpinnerNumberModel spmRecodesChanger;
    private SpinnerNumberModel spmPageChanger;
    private JLabel lbPageRecodes;

    private JToolBar toolBar;

    private JButton btChangeRecodesAmount;
    private JButton btFirstPage;
    private JButton btLastPage;
    private JButton btPreviousPage;
    private JButton btNextPage;
    private JButton btChangePagesAmount;

    public TableWithPaging(Controller controller) {
        this.controller = controller;
        controller.getModel().addTable(this);
        createTableWithPagingPanel();
        addListeners();
    }

    @Override
    public void updateTable(List<Student> page) {
        tableModel.setStudents(page);
        tableModel.fireTableDataChanged();
    }

    @Override
    public void updatePaging() {
        if (controller.isConnected()) {
            int databaseSize = controller.getDatabaseSize();
            if (databaseSize != 0 && !isNewPage(databaseSize)) {
                setPagesAmount((int) Math.ceil((double) databaseSize / getRecodesAmount()));
            } else if(!isNewPage(databaseSize)){
                setPagesAmount(Table.FIRST_PAGE);
            }
            if (isNewPage(databaseSize)) {
                setPagesAmount((int) Math.ceil((double) databaseSize / getRecodesAmount()));
                setCurrentPage(getPagesAmount());
            } else if (getCurrentPage() > getPagesAmount()) {
                setCurrentPage(getPagesAmount());
            }
            lbPageRecodes.setText(String.valueOf(getPagesAmount()));
        }
        controller.changePage(getCurrentPage(), getRecodesAmount());
    }

    @Override
    public void createNewTable() {
        if (tableModel == null) {
            tableModel = new TableModel(new ArrayList<Student>());
            setTableModel(tableModel);
            getToolBar().setVisible(true);
        } else {
            tableModel.setStudents(new ArrayList<Student>());
        }
        tableModel.fireTableDataChanged();
    }

    @Override
    public void printLog(String message) {
        JOptionPane.showMessageDialog(tableWithPaging, message + "!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void createTableWithPagingPanel() {
        tableWithPaging = new JPanel();
        tableWithPaging.setLayout(new BorderLayout());
        tableWithPaging.add(createTable(), BorderLayout.CENTER);
        tableWithPaging.add(createPaging(), BorderLayout.SOUTH);
    }

    private JScrollPane createTable() {
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        return scrollPane;
    }

    private JToolBar createPaging() {
        toolBar = new JToolBar();
        toolBar.setLayout(new BorderLayout());
        setPaging(toolBar);
        return toolBar;
    }

    private void addListeners() {
        btChangeRecodesAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                updatePaging();
            }
        });

        btFirstPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controller.changePage(Table.FIRST_PAGE, getRecodesAmount());
                if (controller.isConnected()) {
                    setCurrentPage(Table.FIRST_PAGE);
                }
            }
        });

        btPreviousPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (getCurrentPage() != Table.FIRST_PAGE) {
                    int previousPage = getCurrentPage() - 1;
                    controller.changePage(previousPage, getRecodesAmount());
                    if (controller.isConnected()) {
                        setCurrentPage(previousPage);
                    }
                }
            }
        });

        btNextPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if (getCurrentPage() != getPagesAmount()) {
                    int nextPage = getCurrentPage() + 1;
                    controller.changePage(nextPage, getRecodesAmount());
                    if (controller.isConnected()) {
                        setCurrentPage(nextPage);
                    }
                }
            }
        });

        btLastPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controller.changePage(getPagesAmount(), getRecodesAmount());
                if (controller.isConnected()) {
                    setCurrentPage(getPagesAmount());
                }
            }
        });

        btChangePagesAmount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                controller.changePage(getCurrentPage(), getRecodesAmount());
            }
        });
    }


    private boolean isNewPage(int dataBaseSize) {
        int pagesAmount = getPagesAmount();
//        int currentPage = getCurrentPage();
        int recodesAmount = getRecodesAmount();
//        return currentPage * recodesAmount <= dataBaseSize;
        return (pagesAmount - 1) * recodesAmount + recodesAmount <= dataBaseSize;
    }


    private void setPaging(JToolBar toolBar) {
        toolBar.setFloatable(false);
        toolBar.setVisible(false);

        JPanel recodesChangerPanel = new JPanel();
        recodesChangerPanel.setLayout(new FlowLayout());

        spmRecodesChanger = new SpinnerNumberModel(5, 1, null, 1);
        JSpinner spRecodesNumber = new JSpinner(spmRecodesChanger);
        Dimension dimensionRN = spRecodesNumber.getPreferredSize();
        dimensionRN.width = SPINNER_WIDTH;
        dimensionRN.height = SPINNER_HEIGHT;
        spRecodesNumber.setPreferredSize(dimensionRN);

        spmPageChanger = new SpinnerNumberModel(1, 1, 1, 1);
        JSpinner spPageChange = new JSpinner(spmPageChanger);
        Dimension dimensionPC = spRecodesNumber.getPreferredSize();
        dimensionPC.width = SPINNER_WIDTH;
        dimensionPC.height = SPINNER_HEIGHT;
        spPageChange.setPreferredSize(dimensionRN);

        lbPageRecodes = new JLabel("1");
        Dimension dimensionPR = lbPageRecodes.getPreferredSize();
        dimensionPR.width = SPINNER_WIDTH;
        dimensionPR.height = SPINNER_HEIGHT;

        btChangeRecodesAmount = new JButton("Change recodes");
        setIcon(btChangeRecodesAmount, "changeRecodesAmount.png");

        recodesChangerPanel.add(spRecodesNumber);
        recodesChangerPanel.add(btChangeRecodesAmount);

        JPanel pageStatePanel = new JPanel();
        pageStatePanel.setLayout(new GridBagLayout());

        btFirstPage = new JButton();
        setIcon(btFirstPage, "firstPage.png");

        btPreviousPage = new JButton();
        setIcon(btPreviousPage, "previousPage.png");

        btNextPage = new JButton();
        setIcon(btNextPage, "nextPage.png");

        btLastPage = new JButton();
        setIcon(btLastPage, "lastPage.png");

        addComponent(pageStatePanel, btFirstPage, 0, 0, 1, 1);
        addComponent(pageStatePanel, btPreviousPage, GridBagConstraints.RELATIVE, 0, 1, 1);
        addComponent(pageStatePanel, btNextPage, GridBagConstraints.RELATIVE, 0, 1, 1);
        addComponent(pageStatePanel, btLastPage, GridBagConstraints.RELATIVE, 0, 1, 1);

        JPanel pageChangerPanel = new JPanel();
        pageChangerPanel.setLayout(new FlowLayout());

        btChangePagesAmount = new JButton("Change page");
        setIcon(btChangePagesAmount, "changePage.png");

        pageChangerPanel.add(btChangePagesAmount);
        pageChangerPanel.add(spPageChange);
        pageChangerPanel.add(lbPageRecodes);

        toolBar.add(recodesChangerPanel, BorderLayout.WEST);
        toolBar.add(pageStatePanel, BorderLayout.CENTER);
        toolBar.add(pageChangerPanel, BorderLayout.EAST);
    }

    private void setIcon(JButton button, String name) {
        ImageIcon imageIcon = new ImageIcon("res/" + name);
        button.setIcon(imageIcon);
    }

    private void addComponent(JComponent container, JComponent component, int gridX, int gridY, int gridWidth, int gridHeight) {
        Insets insets = new Insets(5, 5, 5, 5);
        GridBagConstraints gbc = new GridBagConstraints(gridX, gridY, gridWidth, gridHeight, 1.0, 1.0,
                GridBagConstraints.CENTER, GridBagConstraints.BOTH,
                insets, 0, 0);
        container.add(component, gbc);
    }

    public JPanel getTableWithPaging() {
        return tableWithPaging;
    }

    public void setTableModel(TableModel tableModel) {
        this.tableModel = tableModel;
        table.setModel(tableModel);
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public int getRecodesAmount() {
        return (int) spmRecodesChanger.getValue();
    }

    public int getCurrentPage() {
        return (int) spmPageChanger.getValue();
    }

    public void setCurrentPage(int currentPage) {
        spmPageChanger.setValue(currentPage);
    }

    public int getPagesAmount() {
        return (int) spmPageChanger.getMaximum();
    }

    public void setPagesAmount(int pagesAmount) {
        spmPageChanger.setMaximum(pagesAmount);
    }

}
