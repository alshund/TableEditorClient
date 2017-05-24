package studentTable;

import constants.Window;
import tableController.TableController;
import tableListener.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by shund on 09.04.2017.
 */
public class MainWindow {
    private JFrame mainFrame;
    private JMenuBar menuBar;
    private JToolBar toolBar;
    private TableWithPaging tableWithPaging;
    private TableController tableController;

    public MainWindow(TableController tableController) {
        this.tableController = tableController;
        mainFrame = new JFrame("Состав семьи студентов");
        mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        mainFrame.setSize(JFrame.MAXIMIZED_HORIZ, 600);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());

        menuBar = addJMenuBar();
        mainFrame.setJMenuBar(menuBar);

        tableWithPaging = new TableWithPaging(tableController);

        toolBar = addJToolBar();

        mainFrame.add(toolBar, BorderLayout.NORTH);
        mainFrame.add(tableWithPaging.getTableWithPaging(), BorderLayout.CENTER);

        addListeners();

        mainFrame.setVisible(true);
        mainFrame.toFront();
        mainFrame.requestFocus();
    }

    private JMenuBar addJMenuBar() {
        JMenuBar menu = new JMenuBar();
        menu.add(createMenu("File", new String[]{"New", "Open", "Save", "Exit"}));
        menu.add(createMenu("Edit", new String[]{"Add", "Find", "Delete"}));
        menu.add(createMenu("Connection", new String[]{"Set", "Shut"}));
        return menu;
    }

    private JMenu createMenu(String name, String[] itemName) {
        JMenu menu = new JMenu(name);
        for (String string : itemName) {
            menu.add(new JMenuItem(string));
        }
        return menu;
    }

    private JToolBar addJToolBar() {
        JToolBar toolBar = new JToolBar("Work with table", JToolBar.HORIZONTAL);
        setJToolBar(toolBar);
        return toolBar;
    }

    private void setJToolBar(JToolBar toolBar) {
        toolBar.add(createButton("open.png", new OpenListener(tableController)));
        toolBar.add(createButton("save.png", new SaveListener(tableController)));
        toolBar.addSeparator();
        toolBar.add(createButton("addition.png", new AdditionStudentListener(mainFrame, tableController)));
        toolBar.add(createButton("search.png", new SearchStudentListener(mainFrame, tableController)));
        toolBar.add(createButton("delete.png", new DeleteStudentListener(mainFrame, tableController)));
        toolBar.addSeparator();
        toolBar.add(createButton("setConnection.png", new SetConnectionListener(tableController)));
        toolBar.add(createButton("shutConnection.png", new ShutConnectionListener(tableController)));
        toolBar.addSeparator();
//        createLabel("goodConnection.png", "badConnection.png", toolBar);
        toolBar.setFloatable(false);
        toolBar.setVisible(true);
    }

    private JButton createButton(String name, ActionListener actionListener) {
        JButton button = new JButton();
        ImageIcon imageIcon = new ImageIcon("res/" + name);
        button.setIcon(imageIcon);
        button.addActionListener(actionListener);
        return button;
    }

//    private void createLabel(String firstName, String secondName, JToolBar toolBar) {
//        JLabel goodConnection = new JLabel();
//        JLabel badConnection = new JLabel();
//        ImageIcon goodConnectionIcon = new ImageIcon("res/" + firstName);
//        ImageIcon badConnectionIcon = new ImageIcon("res/" + secondName);
//        goodConnection.setIcon(goodConnectionIcon);
//        badConnection.setIcon(badConnectionIcon);
//        toolBar.add(goodConnection);
//        toolBar.add(badConnection);
//        new Thread(new ConnectionState(tableController, goodConnection, badConnection)).start();
//    }

    private void addListeners() {
        addListener(Window.FILE_MENU, Window.NEW_FILE, new NewTableListener(tableWithPaging, tableController));
        addListener(Window.FILE_MENU, Window.OPEN_FIE, new OpenListener(tableController));
        addListener(Window.FILE_MENU, Window.SAVE_FILE, new SaveListener(tableController));
        addListener(Window.FILE_MENU, Window.EXIT_FILE, e -> {System.exit(0);});
        addListener(Window.EDIT_MENU, Window.ADD_EDIT, new AdditionStudentListener(mainFrame, tableController));
        addListener(Window.EDIT_MENU, Window.SEARCH_EDIT, new SearchStudentListener(mainFrame, tableController));
        addListener(Window.EDIT_MENU, Window.DELETE_EDIT, new DeleteStudentListener(mainFrame, tableController));
        addListener(Window.SERVER_MENU, Window.SET_CONNECTION_SERVER, new SetConnectionListener(tableController));
        addListener(Window.SERVER_MENU, Window.SHUT_CONNECTION_SERVER, new ShutConnectionListener(tableController));
    }

    private void addListener(int firstIndex, int secondIndex, ActionListener actionListener) {
        menuBar.getMenu(firstIndex).getItem(secondIndex).addActionListener(actionListener);

    }
}
