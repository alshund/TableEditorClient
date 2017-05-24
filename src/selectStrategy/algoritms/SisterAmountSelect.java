package selectStrategy.algoritms;

import selectStrategy.DataReader;
import selectStrategy.SelectStrategy;
import searchStrategy.SearchStrategy;
import searchStrategy.algoritms.sisterAmountSearch.SisterAmountSearch;
import studentTable.dialog.SearchPanel;

import javax.swing.*;

/**
 * Created by shund on 03.05.2017.
 */
public class SisterAmountSelect implements SelectStrategy {
    private DataReader dataReader;

    public SisterAmountSelect(JDialog dialog, SearchPanel searchPanel) {
        dataReader = new DataReader(dialog, searchPanel);
    }

    @Override
    public SearchStrategy execute( ) {
        return new SisterAmountSearch(getBrotherAmount());
    }

    private Integer getBrotherAmount() {
        return (Integer) dataReader.getNumber(SearchPanel.SIBLING_AMOUNT);
    }
}
