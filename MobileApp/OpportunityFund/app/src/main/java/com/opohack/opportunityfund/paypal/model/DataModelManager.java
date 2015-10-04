package com.opohack.opportunityfund.paypal.model;

/**
 * Created by vipulkanade on 5/13/15.
 */
public class DataModelManager {

    private static DataModelManager mInstance;
    private static final Object obj = new Object();



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
