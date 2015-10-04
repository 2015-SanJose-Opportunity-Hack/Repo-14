package com.opohack.opportunityfund.paypal.view;


import android.app.Application;


public class OpoFundApplication extends Application {

    private static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public synchronized static Application getInstance() {
        return mInstance;
    }
}
