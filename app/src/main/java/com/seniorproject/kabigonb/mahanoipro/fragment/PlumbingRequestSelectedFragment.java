package com.seniorproject.kabigonb.mahanoipro.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.dao.WorkListDataDao;


@SuppressWarnings("unused")
public class PlumbingRequestSelectedFragment extends Fragment implements View.OnClickListener {

    TextView tvPlumbing_customerName,tvPlumbing_customerEmail,tvPlumbing_phoneNumber
            ,tvPlumbing_workKind,tvPlumbing_workRequired,tvPlumbing_placeType
            ,tvPlumbing_problem,tvPlumbing_moreDetail;

    WorkListDataDao dao;

    Button btnPlumbingFinished,btnMapView_plumbing;

    public PlumbingRequestSelectedFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static PlumbingRequestSelectedFragment newInstance(WorkListDataDao dao) {
        PlumbingRequestSelectedFragment fragment = new PlumbingRequestSelectedFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao",dao);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(savedInstanceState);
        dao = getArguments().getParcelable("dao");
        if (savedInstanceState != null)
            onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_plumbing_request_selected, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        tvPlumbing_customerName = rootView.findViewById(R.id.tvPlumbing_customerName);
        tvPlumbing_customerEmail = rootView.findViewById(R.id.tvPlumbing_customerEmail);
        tvPlumbing_phoneNumber = rootView.findViewById(R.id.tvPlumbing_phoneNumber);
        tvPlumbing_workKind = rootView.findViewById(R.id.tvPlumbing_workKind);
        tvPlumbing_workRequired = rootView.findViewById(R.id.tvPlumbing_workRequired);
        tvPlumbing_placeType = rootView.findViewById(R.id.tvPlumbing_placeType);
        tvPlumbing_problem = rootView.findViewById(R.id.tvPlumbing_problem);
        tvPlumbing_moreDetail = rootView.findViewById(R.id.tvPlumbing_moreDetail);

        btnPlumbingFinished = rootView.findViewById(R.id.btnPlumbingFinished);
        btnMapView_plumbing = rootView.findViewById(R.id.btnMapView_plumbing);

        btnMapView_plumbing.setOnClickListener(this);
        btnPlumbingFinished.setOnClickListener(this);


    }

    @Override
    public void onStart() {
        super.onStart();
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
    @SuppressWarnings("UnusedParameters")
    private void onRestoreInstanceState(Bundle savedInstanceState) {
        // Restore Instance State here
    }

    @Override
    public void onClick(View v) {

    }
}
