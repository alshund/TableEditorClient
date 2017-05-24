package observe;

/**
 * Created by shund on 07.05.2017.
 */
public interface Observable {
    void addTable(Observer observer);
    void removeTable(Observer observer);
}
