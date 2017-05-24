package selectStrategy.algoritms;

import selectStrategy.DataReader;
import selectStrategy.SelectStrategy;
import searchStrategy.SearchStrategy;
import searchStrategy.algoritms.brotherAmountSearch.BrotherAmountSearch;
import studentTable.dialog.SearchPanel;

import javax.swing.*;

/**
 * Created by shund on 03.05.2017.
 */
public class BrotherAmountSelect implements SelectStrategy {
    private DataReader dataReader;

    public BrotherAmountSelect(JDialog dialog, SearchPanel searchPanel) {
        dataReader = new DataReader(dialog, searchPanel);
    }

    @Override
    public SearchStrategy execute( ) {
        return new BrotherAmountSearch(getBrotherAmount());
    }

    private Integer getBrotherAmount() {
        return (Integer) dataReader.getNumber(SearchPanel.SIBLING_AMOUNT);
    }
}
