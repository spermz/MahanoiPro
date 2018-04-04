package com.seniorproject.kabigonb.mahanoipro.manager;

import android.content.Context;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.seniorproject.kabigonb.mahanoipro.dao.WorkListDao;


public class WorkListManager {

    private static WorkListManager instance;

    public static WorkListManager getInstance() {
        if (instance == null)
            instance = new WorkListManager();
        return instance;
    }

    private Context mContext;
    private WorkListDao dao;

    private WorkListManager() {
        mContext = Contextor.getInstance().getContext();
    }

    public WorkListDao getDao() {
        return dao;
    }

    public void setDao(WorkListDao dao) {
        this.dao = dao;
    }
}
