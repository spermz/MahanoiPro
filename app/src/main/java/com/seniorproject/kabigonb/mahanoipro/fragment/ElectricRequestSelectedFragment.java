package com.seniorproject.kabigonb.mahanoipro.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.activity.MapsActivity;
import com.seniorproject.kabigonb.mahanoipro.dao.OfferDataDao;
import com.seniorproject.kabigonb.mahanoipro.dao.UserDataDAO;
import com.seniorproject.kabigonb.mahanoipro.dao.WorkListDataDao;
import com.seniorproject.kabigonb.mahanoipro.manager.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        loadUserData();
        loadOfferData();

    }


    private void loadUserData() {

        Call<UserDataDAO> call = HttpManager.getInstance().getService().loadUserDetail(userLoadDataForm());
        call.enqueue(loadUserCallBack);

    }

    private void loadOfferData() {

        Call<OfferDataDao> call = HttpManager.getInstance().getService().loadRequest(offerDataForm());
        call.enqueue(offerCallBack);

    }

    private OfferDataDao offerDataForm() {

        OfferDataDao requestDataDao = new OfferDataDao();
        SharedPreferences prefs = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);

        requestDataDao.setToken(prefs.getString("token",null));
        requestDataDao.setOfferId(dao.getOfferId());
        requestDataDao.setTypeService(dao.getTypeService());
        requestDataDao.setProviderName(dao.getProviderName());

        return requestDataDao;

    }

    private UserDataDAO userLoadDataForm() {

        UserDataDAO userDataDAO = new UserDataDAO();
        SharedPreferences prefs = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);

        userDataDAO.setToken(prefs.getString("token",null));
        userDataDAO.setUserName(dao.getUserName());
        userDataDAO.setProviderName(prefs.getString("userName",null));
        return userDataDAO;
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

        if(v == btnMapView_electric)
        {
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            intent.putExtra("dao",dao);
            startActivity(intent);
        }

    }

    Callback<UserDataDAO> loadUserCallBack = new Callback<UserDataDAO>() {
        @Override
        public void onResponse(Call<UserDataDAO> call, Response<UserDataDAO> response) {
            if(response.isSuccessful())
            {

                UserDataDAO dao = response.body();

                if(dao.getErrorMessage() != null)
                {
                    Toast.makeText(Contextor.getInstance().getContext()
                            ,dao.getErrorMessage()
                            ,Toast.LENGTH_SHORT).show();
                }
                else {
                    tvSetTextUser(dao);
                }
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
        public void onFailure(Call<UserDataDAO> call, Throwable t) {
            Toast.makeText(Contextor.getInstance().getContext()
                    ,t.toString()
                    ,Toast.LENGTH_SHORT).show();
        }
    };

    Callback<OfferDataDao> offerCallBack = new Callback<OfferDataDao>() {
        @Override
        public void onResponse(Call<OfferDataDao> call, Response<OfferDataDao> response) {

            if(response.isSuccessful())
            {
                OfferDataDao dao = response.body();
                if(dao.getErrorMessage() != null)
                {
                    Toast.makeText(Contextor.getInstance().getContext()
                            ,dao.getErrorMessage()
                            ,Toast.LENGTH_SHORT).show();
                }
                else {
                    tvSetTextOffer(dao);
                    Log.d("offerCallBack","has call");
                }
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
        public void onFailure(Call<OfferDataDao> call, Throwable t) {
            Toast.makeText(Contextor.getInstance().getContext()
                    ,t.toString()
                    ,Toast.LENGTH_SHORT).show();
        }
    };

    private void tvSetTextOffer(OfferDataDao offerDataDao) {

        tvElectric_workKind.setText(offerDataDao.getTypeInfo());
        tvElectric_problem.setText(offerDataDao.getProblem());
        tvElectric_placeType.setText(offerDataDao.getPlaceType());
        tvElectric_moreDetail.setText(offerDataDao.getMoreDetail());
    }

    private void tvSetTextUser(UserDataDAO userDataDAO) {

        tvElectric_customerName.setText(userDataDAO.getFirstName() + " " + userDataDAO.getLastName());
        tvElectric_customerEmail.setText(userDataDAO.getEmail());
        tvElectric_phoneNumber.setText(userDataDAO.getPhoneNumber());
    }
}
