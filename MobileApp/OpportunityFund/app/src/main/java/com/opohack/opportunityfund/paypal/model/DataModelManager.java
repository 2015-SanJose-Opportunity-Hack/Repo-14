package com.opohack.opportunityfund.paypal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vipulkanade on 5/13/15.
 */
public class DataModelManager {

    private static DataModelManager mInstance;
    private static final Object obj = new Object();

    public CalculatedLoan getCalculatedLoan() {
        return calculatedLoan;
    }

    public void setCalculatedLoan(CalculatedLoan calculatedLoan) {
        this.calculatedLoan = calculatedLoan;
    }

    private CalculatedLoan calculatedLoan;

    public List<CalculatedLoan> getCalculatedLoanList() {
        return mCalculatedLoanList;
    }

    public void setCalculatedLoanList(List<CalculatedLoan> mCalculatedLoanList) {
        this.mCalculatedLoanList = mCalculatedLoanList;
    }

    private List<CalculatedLoan> mCalculatedLoanList = new ArrayList<CalculatedLoan>();

    public DataModelManager() {
    }

    public static DataModelManager getInstance() {
        synchronized (obj) {
            if(mInstance == null)
                mInstance = new DataModelManager();
        }
        return mInstance;
    }


}
