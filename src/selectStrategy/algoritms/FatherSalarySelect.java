package selectStrategy.algoritms;

import selectStrategy.DataReader;
import selectStrategy.SelectStrategy;
import searchStrategy.SearchStrategy;
import searchStrategy.algoritms.fatherSalarySearch.FatherLwSearch;
import searchStrategy.algoritms.fatherSalarySearch.FatherLwUpSearch;
import searchStrategy.algoritms.fatherSalarySearch.FatherUpSearch;
import studentTable.dialog.SearchPanel;

import javax.swing.*;

/**
 * Created by shund on 03.05.2017.
 */
public class FatherSalarySelect implements SelectStrategy {
    private DataReader dataReader;

    public FatherSalarySelect(JDialog dialog, SearchPanel searchPanel) {
        dataReader = new DataReader(dialog, searchPanel);
    }

    @Override
    public SearchStrategy execute() {
        if (isLowerUpperLimit()) {
            return new FatherLwUpSearch(getLowerLimit(), getUpperLimit());
        } else if (isLowerLimit()) {
            return new FatherLwSearch(getLowerLimit());
        } else if (isUpperLimit()) {
            return new FatherUpSearch(getUpperLimit());
        }  else {
            return null;
        }
    }

    private Double getLowerLimit() {
        return (Double) dataReader.getNumber(SearchPanel.SALARY_LOWER_LIMIT);
    }

    private Double getUpperLimit() {
        return (Double) dataReader.getNumber(SearchPanel.SALARY_UPPER_LIMIT);
    }

    private boolean isLowerUpperLimit() {
        return dataReader.isTwo(SearchPanel.SALARY_LOWER_LIMIT, SearchPanel.SALARY_UPPER_LIMIT);
    }

    private boolean isLowerLimit() {
        return dataReader.isOne(SearchPanel.SALARY_LOWER_LIMIT);
    }

    private boolean isUpperLimit() {
        return dataReader.isOne(SearchPanel.SALARY_UPPER_LIMIT);
    }
}