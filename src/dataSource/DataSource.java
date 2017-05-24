package dataSource;

import command.Commands;
import observe.Observable;
import observe.Observer;
import org.apache.log4j.Logger;
import searchStrategy.SearchStrategy;
import studentDataBase.Student;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shund on 20.05.2017.
 */
public class DataSource implements Observable {
    public static final String NULL_CONNECTION = "There's no server connection";
    private Socket socket;
    private InetAddress inetAddress;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private List<Observer> observerList;
    private Logger logger = Logger.getLogger(DataSource.class);


    public DataSource() {
        observerList = new LinkedList<Observer>();
    }

    @Override
    public void addTable(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void removeTable(Observer observer) {
        observerList.remove(observer);
    }

    public void setConnection(String ipAddress) {
        if (socket == null || socket.isConnected()) {
            try {
                inetAddress = InetAddress.getByName(ipAddress);
                socket = new Socket(inetAddress, 3220);
                outputStream = new ObjectOutputStream(socket.getOutputStream());
                inputStream = new ObjectInputStream(socket.getInputStream());
            } catch (IOException e) {
                printLog(e.getMessage());
                logger.error(e.getMessage());
            }
        }
    }

    public void shutConnection() {
        try {
            if (!socket.isClosed()) {
                outputStream.writeUTF(Commands.SHUT_CONNECTION);
                outputStream.flush();
                socket.close();
            }
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            printLog(NULL_CONNECTION);
            logger.error(NULL_CONNECTION);
        }
    }

    public void updatePaging() {
        for (Observer observer : observerList) {
            try {
                observer.updatePaging();
            } catch (NullPointerException e) {
                printLog("There's no table");
                logger.error("There's no table");
            }
        }
    }

    public void changePage(int currentPage, int recodesAmount) {
        try {
            outputStream.writeUTF(Commands.GET_PAGE);
            outputStream.writeInt(currentPage);
            outputStream.writeInt(recodesAmount);
            outputStream.flush();
            List<Student> page = (List<Student>) inputStream.readObject();
            for (Observer observer : observerList) {
                observer.updateTable(page);
            }
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        }
    }

    public void changeSearchPage(int currentPage, int recodesAmount) {
        try {
            outputStream.writeUTF(Commands.GET_SEARCH_PAGE);
            outputStream.writeInt(currentPage);
            outputStream.writeInt(recodesAmount);
            outputStream.flush();
            List<Student> page = (List<Student>) inputStream.readObject();
            observerList.get(1).updateTable(page);
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            printLog(NULL_CONNECTION);
            logger.error(NULL_CONNECTION);
        }
    }

    public void addStudent(Student student) {
        try {
            outputStream.writeUTF(Commands.ADD_STUDENT);
            outputStream.writeObject(student);
            outputStream.flush();
            updatePaging();
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            printLog(NULL_CONNECTION);
            logger.error(NULL_CONNECTION);
        }

    }

    public List<Student> findStudent(SearchStrategy searchStrategy) {
        try {
            outputStream.writeUTF(Commands.FIND_STUDENT);
            outputStream.writeObject(searchStrategy);
            outputStream.flush();
            return (List<Student>) inputStream.readObject();
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            printLog(NULL_CONNECTION);
            logger.error(NULL_CONNECTION);
        }
        return null;
    }

    public void deleteStudent(SearchStrategy searchStrategy) {
        try {
            outputStream.writeUTF(Commands.DELETE_STUDENT);
            outputStream.writeObject(searchStrategy);
            outputStream.flush();
            updatePaging();
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            printLog(NULL_CONNECTION);
            logger.error(NULL_CONNECTION);
        }
    }

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        try {
            outputStream.writeUTF(Commands.SET_SEARCH_STRATEGY);
            outputStream.writeObject(searchStrategy);
            outputStream.flush();
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        }
    }

    public SearchStrategy getSearchStrategy() {
        try {
            outputStream.writeUTF(Commands.GET_SEARCH_STRATEGY);
            outputStream.flush();
            return (SearchStrategy) inputStream.readObject();
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (ClassNotFoundException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        }
        return null;
    }

    public void createNewTable() {
        try {
            outputStream.writeUTF(Commands.SET_STUDENTS);
            outputStream.writeObject(new ArrayList<Student>());
            outputStream.flush();
            for (Observer observer : observerList) {
                observer.createNewTable();
                observer.updatePaging();
            }
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            printLog(NULL_CONNECTION);
            logger.error(NULL_CONNECTION);
        }

    }

    public int getDatabaseSize() {
        try {
            outputStream.writeUTF(Commands.GET_DATABASE_SIZE);
            outputStream.flush();
            int size = inputStream.readInt();
            return size;
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            printLog(NULL_CONNECTION);
            logger.error(NULL_CONNECTION);
        }
        return 0;
    }

    public int getSearchListSize() {
        try {
            outputStream.writeUTF(Commands.GET_SEARCH_LIST_SIZE);
            outputStream.flush();
            int size = inputStream.readInt();
            return size;
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        }
        return Integer.parseInt(null);
    }

    public void saveAction(String fileName) {
        try {
            outputStream.writeUTF(Commands.SAVE_TABLE);
            outputStream.writeUTF(fileName);
            outputStream.flush();
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            printLog(NULL_CONNECTION);
            logger.error(NULL_CONNECTION);
        }
    }

    public void openAction(String fileName) {
        try {
            outputStream.writeUTF(Commands.OPEN_TABLE);
            outputStream.writeUTF(fileName);
            outputStream.flush();
            for (Observer observer : observerList) {
                observer.createNewTable();
                observer.updatePaging();
            }
        } catch (IOException e) {
            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
            printLog(NULL_CONNECTION);
            logger.error(NULL_CONNECTION);
        }
    }

    public boolean isConnected() {
        try {
            if (!socket.isClosed() && socket.isConnected()){
                outputStream.writeUTF(Commands.CHECK_CONNECTION);
                outputStream.flush();
                return inputStream.readBoolean();
            }
        } catch (IOException e) {
//            printLog(e.getMessage());
            logger.error(e.getMessage());
        } catch (NullPointerException e) {
//            printLog(NULL_CONNECTION);
            logger.error(NULL_CONNECTION);
        }
        return false;
    }

    private void printLog(String log) {
        for (Observer observer : observerList) {
            observer.printLog(log);
        }
    }
}
