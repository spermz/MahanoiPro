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
public class CleaningRequestSelectedFragment extends Fragment implements View.OnClickListener {

    TextView tvCleaning_CustomerName,tvCleaning_CustomerEmail,tvCleaning_CustomerNumber
            ,tvCleaning_serviceWanted,tvCleaning_placeType,tvCleaning_moreDetail;
    WorkListDataDao dao;
    Button btnCleaningFinish,btnMapView_cleaning;

    public CleaningRequestSelectedFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CleaningRequestSelectedFragment newInstance(WorkListDataDao dao) {
        CleaningRequestSelectedFragment fragment = new CleaningRequestSelectedFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_cleaning_request_fragment, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        tvCleaning_CustomerName = rootView.findViewById(R.id.tvCleaning_CustomerName);
        tvCleaning_CustomerEmail = rootView.findViewById(R.id.tvCleaning_CustomerEmail);
        tvCleaning_CustomerNumber = rootView.findViewById(R.id.tvCleaning_CustomerNumber);
        tvCleaning_serviceWanted = rootView.findViewById(R.id.tvCleaning_serviceWanted);
        tvCleaning_placeType = rootView.findViewById(R.id.tvCleaning_placeType);
        tvCleaning_moreDetail = rootView.findViewById(R.id.tvCleaning_moreDetail);


        btnCleaningFinish = rootView.findViewById(R.id.btnCleaningFinish);
        btnMapView_cleaning = rootView.findViewById(R.id.btnMapView_cleaning);

        btnCleaningFinish.setOnClickListener(this);
        btnMapView_cleaning.setOnClickListener(this);

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
