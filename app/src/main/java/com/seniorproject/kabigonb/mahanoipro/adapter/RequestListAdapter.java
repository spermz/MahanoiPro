package com.seniorproject.kabigonb.mahanoipro.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.seniorproject.kabigonb.mahanoipro.dao.RequestDataDao;
import com.seniorproject.kabigonb.mahanoipro.dao.ServiceCollectionDao;
import com.seniorproject.kabigonb.mahanoipro.view.RequestListItem;

/**
 * Created by LaFezTer on 10-Feb-18.
 */

public class RequestListAdapter extends BaseAdapter {

    ServiceCollectionDao dao;

    public void setDao(ServiceCollectionDao dao) {
        this.dao = dao;
    }

    @Override
    public int getCount() {

        if(dao == null)
        {
            Log.d("getCount" , "dao == null");
            return 0;
        }
        if(dao.getRequestDataDao() == null)
        {
            Log.d("getCount" , "dao.getRequestDataDao() == null");
            return 0;
        }
             Log.d("getCount" , "return dao.getRequestDataDao().size()");
            return dao.getRequestDataDao().size();
    }

    @Override
    public Object getItem(int position) {
        return dao.getRequestDataDao().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        RequestListItem item;

        if (convertView != null) {
            item = (RequestListItem) convertView;
        }
        else
        {
            item = new RequestListItem(parent.getContext());
        }

        RequestDataDao dao = (RequestDataDao) getItem(position);

        item.setList_request_name(dao.getUserName());
        item.setList_request_serviceName(dao.getTypeInfo());
        //item.setList_request_location();
        return item;

    }


}
