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
public class FoodRequestSelectedFragment extends Fragment implements View.OnClickListener {

    TextView tvFood_customerName,tvFood_customerEmail,tvFood_phoneNumber
            ,tvFood_foodType,tvFood_order,tvFood_moreDetail;

    Button  btnMapView_food,btnFoodFinish;

    WorkListDataDao dao;

    public FoodRequestSelectedFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static FoodRequestSelectedFragment newInstance(WorkListDataDao dao) {
        FoodRequestSelectedFragment fragment = new FoodRequestSelectedFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_food_request_selected, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here

        tvFood_customerName = rootView.findViewById(R.id.tvFood_customerName);
        tvFood_customerEmail = rootView.findViewById(R.id.tvFood_customerEmail);
        tvFood_phoneNumber = rootView.findViewById(R.id.tvFood_phoneNumber);
        tvFood_foodType = rootView.findViewById(R.id.tvFood_foodType);
        tvFood_order = rootView.findViewById(R.id.tvFood_order);
        tvFood_moreDetail = rootView.findViewById(R.id.tvFood_moreDetail);

        btnMapView_food = rootView.findViewById(R.id.btnMapView_food);
        btnFoodFinish = rootView.findViewById(R.id.btnFoodFinish);

        btnFoodFinish.setOnClickListener(this);
        btnMapView_food.setOnClickListener(this);


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
