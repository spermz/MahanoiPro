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
public class ElectricRequestSelectedFragment extends Fragment implements View.OnClickListener {

    TextView tvElectric_customerName,tvElectric_customerEmail,tvElectric_phoneNumber
            ,tvElectric_workKind,tvElectric_problem,tvElectric_placeType,tvElectric_moreDetail;
    Button btnMapView_electric,btnElectricFinish;
    WorkListDataDao dao;
    public ElectricRequestSelectedFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static ElectricRequestSelectedFragment newInstance(WorkListDataDao dao) {
        ElectricRequestSelectedFragment fragment = new ElectricRequestSelectedFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_electric_request_selected, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        tvElectric_customerName = rootView.findViewById(R.id.tvElectric_customerName);
        tvElectric_customerEmail = rootView.findViewById(R.id.tvElectric_customerEmail);
        tvElectric_phoneNumber = rootView.findViewById(R.id.tvElectric_phoneNumber);
        tvElectric_workKind = rootView.findViewById(R.id.tvElectric_workKind);
        tvElectric_problem = rootView.findViewById(R.id.tvElectric_problem);
        tvElectric_placeType = rootView.findViewById(R.id.tvElectric_placeType);
        tvElectric_moreDetail = rootView.findViewById(R.id.tvElectric_moreDetail);

        btnMapView_electric = rootView.findViewById(R.id.btnMapView_electric);
        btnElectricFinish = rootView.findViewById(R.id.btnElectricFinish);

        btnElectricFinish.setOnClickListener(this);
        btnMapView_electric.setOnClickListener(this);

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
