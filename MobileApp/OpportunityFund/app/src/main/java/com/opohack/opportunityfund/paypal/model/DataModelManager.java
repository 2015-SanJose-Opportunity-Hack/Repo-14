package com.opohack.opportunityfund.paypal.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vipulkanade on 5/13/15.
 */
public class DataModelManager {

    private static DataModelManager mInstance;
    private static final Object obj = new Object();

    private List<EmployeeInStore> mEmployeeInStoreList = new ArrayList<EmployeeInStore>();

    private List<GetProductHistory> mGetProductHistoryList = new ArrayList<GetProductHistory>();

    private List<GetProduct> mGetProductList = new ArrayList<GetProduct>();

    public DataModelManager() {
    }

    public static DataModelManager getInstance() {
        synchronized (obj) {
            if(mInstance == null)
                mInstance = new DataModelManager();
        }
        return mInstance;
    }

    public List<EmployeeInStore> getEmployeeInStoreList() {
        return mEmployeeInStoreList;
    }

    public void setEmployeeInStoreList(List<EmployeeInStore> mEmployeeInStoreList) {
        this.mEmployeeInStoreList = mEmployeeInStoreList;
    }

    public List<GetProductHistory> getGetProductHistoryList() {
        return mGetProductHistoryList;
    }

    public void setGetProductHistoryList(List<GetProductHistory> mGetProductHistoryList) {
        this.mGetProductHistoryList = mGetProductHistoryList;
    }

    public List<GetProduct> getGetProductList() {
        return mGetProductList;
    }

    public void setGetProductList(List<GetProduct> mGetProductList) {
        this.mGetProductList = mGetProductList;
    }
}
