package com.seniorproject.kabigonb.mahanoipro;

import android.app.Application;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by LaFezTer on 24-Oct-17.
 */

public class MahanoiProApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Contextor.getInstance().init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

}
