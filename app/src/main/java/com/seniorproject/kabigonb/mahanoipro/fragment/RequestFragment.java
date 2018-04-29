package com.seniorproject.kabigonb.mahanoipro.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.adapter.RequestListAdapter;
import com.seniorproject.kabigonb.mahanoipro.dao.RequestDataDao;
import com.seniorproject.kabigonb.mahanoipro.dao.ServiceCollectionDao;
import com.seniorproject.kabigonb.mahanoipro.manager.HttpManager;
import com.seniorproject.kabigonb.mahanoipro.manager.RequestListManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class RequestFragment extends Fragment
        implements Callback<ServiceCollectionDao>
        , SwipeRefreshLayout.OnRefreshListener
        , AbsListView.OnScrollListener
        , AdapterView.OnItemClickListener {


    public interface FragmentListener{
        void onRequestItemClicked(RequestDataDao dao);


    }


    ListView listViewRequest;
    RequestListAdapter requestListAdapter;
    SwipeRefreshLayout srlRequest;

    public RequestFragment() {
        super();
    }

    public static RequestFragment newInstance() {
        RequestFragment fragment = new RequestFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_request, container, false);

        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        Log.d("RequestFragment", "onCreateView");
        listViewRequest = rootView.findViewById(R.id.listViewRequest);
        requestListAdapter = new RequestListAdapter();
        listViewRequest.setAdapter(requestListAdapter);

        srlRequest = rootView.findViewById(R.id.srlRequest);
        srlRequest.setOnRefreshListener(this);

        listViewRequest.setOnScrollListener(this);
        listViewRequest.setOnItemClickListener(this);
        reloadData();

    }

    private void reloadData() {
        Call<ServiceCollectionDao> call = HttpManager.getInstance().getService().loadRequestList(serviceCollectionForm());
        call.enqueue(this);
    }


    private ServiceCollectionDao serviceCollectionForm() {

        ServiceCollectionDao dao = new ServiceCollectionDao();

        SharedPreferences prefs = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);

        String value = prefs.getString("token",null);
        String value2 = prefs.getString("userName",null);
        int value3 = prefs.getInt("typeService",0);
        Log.d("token",value);
        Log.d("userName",value2);

        dao.setToken(value);
        dao.setProviderName(value2);
        dao.setTypeService(value3);

        return dao;
    }

    @Override
    public void onStart() {
        super.onStart();
        reloadData();
    }


    @Override
    public void onStop() {
        super.onStop();

    }

    /*
     * Save Instance State Here
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save Instance State here
    }


    /*
     * Restore Instance State Here
     */

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    @Override
    public void onResponse(Call<ServiceCollectionDao> call, Response<ServiceCollectionDao> response) {

        srlRequest.setRefreshing(false);

        if(response.isSuccessful())
        {
            ServiceCollectionDao dao = response.body();
            RequestListManager.getInstance().setDao(dao);

            requestListAdapter.setDao(dao);
            requestListAdapter.notifyDataSetChanged();

        }
        else
        {
            try {
                Toast.makeText(Contextor.getInstance().getContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onFailure(Call<ServiceCollectionDao> call, Throwable t) {
        srlRequest.setRefreshing(false);
        Toast.makeText(Contextor.getInstance().getContext()
                ,t.toString()
                ,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onRefresh() {
        reloadData();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        srlRequest.setEnabled(firstVisibleItem == 0);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            RequestDataDao dao = RequestListManager.getInstance().getDao().getRequestDataDao().get(position);
            FragmentListener listener = (FragmentListener) getActivity();
            listener.onRequestItemClicked(dao);

    }
}
