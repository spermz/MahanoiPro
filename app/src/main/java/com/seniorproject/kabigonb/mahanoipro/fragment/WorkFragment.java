package com.seniorproject.kabigonb.mahanoipro.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.adapter.WorkListAdapter;
import com.seniorproject.kabigonb.mahanoipro.dao.WorkListDao;
import com.seniorproject.kabigonb.mahanoipro.dao.WorkListDataDao;
import com.seniorproject.kabigonb.mahanoipro.manager.HttpManager;
import com.seniorproject.kabigonb.mahanoipro.manager.WorkListManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class WorkFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, AbsListView.OnScrollListener, AdapterView.OnItemClickListener {

    public interface WorkFragmentListener {
         void onWorkListClicked(WorkListDataDao dao);
    }

    ListView workListItem;
    WorkListAdapter workListAdapter;

    SwipeRefreshLayout srlWork;

    public WorkFragment() {
        super();
    }

    public static WorkFragment newInstance() {
        WorkFragment fragment = new WorkFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_work, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {

        // Init 'View' instance(s) with rootView.findViewById here
        workListItem =  rootView.findViewById(R.id.listViewWork);
        srlWork = rootView.findViewById(R.id.srlWork);

        workListAdapter = new WorkListAdapter();
        workListItem.setAdapter(workListAdapter);

        workListItem.setOnScrollListener(this);
        workListItem.setOnItemClickListener(this);
        srlWork.setOnRefreshListener(this);

        loadData();

    }

    private void loadData() {

        Call<WorkListDao> call = HttpManager.getInstance().getService().loadWorkList(workListForm());
        call.enqueue(responseCallBack);

    }

    private WorkListDao workListForm() {

        WorkListDao dao = new WorkListDao();
        SharedPreferences prefs = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);

        dao.setToken(prefs.getString("token",null));
        dao.setProviderName(prefs.getString("userName",null));

        return dao;


    }


    @Override
    public void onStart() {
        super.onStart();
        loadData();
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
    public void onRefresh() {

        loadData();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        srlWork.setEnabled(firstVisibleItem == 0);
    }

    Callback<WorkListDao> responseCallBack = new Callback<WorkListDao>() {
        @Override
        public void onResponse(Call<WorkListDao> call, Response<WorkListDao> response) {

            srlWork.setRefreshing(false);

            if(response.isSuccessful())
            {
                WorkListDao dao = response.body();
                workListAdapter.setDao(dao);

                WorkListManager.getInstance().setDao(dao);
                workListAdapter.notifyDataSetChanged();
            }
            else
            {
                try {
                    Toast.makeText(Contextor.getInstance().getContext()
                            ,response.errorBody().string()
                            ,Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        }

        @Override
        public void onFailure(Call<WorkListDao> call, Throwable t) {

            srlWork.setRefreshing(false);

            Toast.makeText(Contextor.getInstance().getContext()
                    ,t.getMessage()
                    ,Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

       WorkListDataDao dao = WorkListManager.getInstance().getDao().getResult().get(position);
       WorkFragmentListener listener = (WorkFragmentListener) getActivity();
       listener.onWorkListClicked(dao);

    }
}
