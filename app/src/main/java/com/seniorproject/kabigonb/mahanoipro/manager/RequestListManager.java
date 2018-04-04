package com.seniorproject.kabigonb.mahanoipro.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.seniorproject.kabigonb.mahanoipro.dao.ServiceCollectionDao;


public class RequestListManager {

    private static RequestListManager instance;

    public static RequestListManager getInstance() {
        if (instance == null)
            instance = new RequestListManager();
        return instance;
    }

    private Context mContext;
    private ServiceCollectionDao dao;

    private RequestListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public ServiceCollectionDao getDao() {
        return dao;
    }

    public void setDao(ServiceCollectionDao dao) {
        this.dao = dao;
    }
}
