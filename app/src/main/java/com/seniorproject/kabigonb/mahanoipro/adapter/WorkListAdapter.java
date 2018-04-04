package com.seniorproject.kabigonb.mahanoipro.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.seniorproject.kabigonb.mahanoipro.dao.WorkListDao;
import com.seniorproject.kabigonb.mahanoipro.view.WorkListItem;

/**
 * Created by LaFezTer on 10-Feb-18.
 */

public class WorkListAdapter extends BaseAdapter {

    private WorkListDao dao;

    public WorkListDao getDao() {
        return dao;
    }

    public void setDao(WorkListDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {

        if(dao == null)
        {
            return 0;
        }
        if(dao.getResult() == null)
        {
            return 0;
        }

        return dao.getResult().size();

    }

    @Override
    public Object getItem(int position) {

        return dao.getResult().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        WorkListItem item;

        if(convertView != null)
        {
            item = (WorkListItem) convertView;
        }
        else
        {
            item = new WorkListItem(parent.getContext());

        }

        return item;

    }
}
