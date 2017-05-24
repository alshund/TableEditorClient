package studentTable;

import tableController.Controller;

import javax.swing.*;

/**
 * Created by shund on 24.05.2017.
 */
public class ConnectionState implements Runnable {
    private Controller controller;
    private JLabel goodConnection;
    private JLabel badConnection;

    public ConnectionState(Controller controller, JLabel goodConnection, JLabel badConnection) {
        this.controller = controller;
        this.goodConnection = goodConnection;
        this.badConnection = badConnection;
    }

    @Override
    public void run() {
        while (true) {
            if (controller.isConnected()) {
                badConnection.setVisible(false);
                goodConnection.setVisible(true);
            } else {
                goodConnection.setVisible(false);
                badConnection.setVisible(true);
            }
        }
    }
}
