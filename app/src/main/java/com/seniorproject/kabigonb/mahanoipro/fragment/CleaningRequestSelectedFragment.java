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
import com.seniorproject.kabigonb.mahanoipro.dao.FinishDao;
import com.seniorproject.kabigonb.mahanoipro.dao.OfferDataDao;
import com.seniorproject.kabigonb.mahanoipro.dao.UserDataDAO;
import com.seniorproject.kabigonb.mahanoipro.dao.WorkListDataDao;
import com.seniorproject.kabigonb.mahanoipro.manager.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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

        if(v == btnMapView_cleaning)
        {
            Intent intent = new Intent(getActivity(), MapsActivity.class);
            intent.putExtra("dao",dao);
            startActivity(intent);
        }
        if(v == btnCleaningFinish)
        {
            Toast.makeText(Contextor.getInstance().getContext()
                    ,"button press"
                    ,Toast.LENGTH_SHORT)
                    .show();

          //  btnCleaningFinish.setEnabled(false);
            Call<FinishDao> call = HttpManager.getInstance().getService().finishService(finishForm());
            call.enqueue(finishDaoCallback);
        }

    }

    private FinishDao finishForm() {

        FinishDao finishDao = new FinishDao();
        SharedPreferences prefs = getActivity().getSharedPreferences("token",Context.MODE_PRIVATE);

        finishDao.setToken(prefs.getString("token",null));
        finishDao.setRequestId(dao.getRequestId());
        finishDao.setProviderName(dao.getProviderName());

        return finishDao;

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

    Callback<FinishDao> finishDaoCallback = new Callback<FinishDao>() {
        @Override
        public void onResponse(Call<FinishDao> call, Response<FinishDao> response) {

          //  btnCleaningFinish.setEnabled(true);

            if(response.isSuccessful())
            {
                FinishDao dao = response.body();

                if(dao.getErrorMessage() != null)
                {
                    Toast.makeText(Contextor.getInstance().getContext()
                            ,dao.getErrorMessage()
                            ,Toast.LENGTH_SHORT)
                            .show();
                }
                else if(!dao.getStatusMessage().equals("บันทึกสำเร็จ"))
                {
                    Toast.makeText(Contextor.getInstance().getContext()
                            ,dao.getStatusMessage()
                            ,Toast.LENGTH_SHORT)
                            .show();
                }
                else
                {
                    Toast.makeText(Contextor.getInstance().getContext()
                            ,dao.getStatusMessage()
                            ,Toast.LENGTH_SHORT)
                            .show();
                    getActivity().finish();
                }

            }

            else
            {
                try {
                    Toast.makeText(Contextor.getInstance().getContext()
                            ,response.errorBody().string()
                            ,Toast.LENGTH_SHORT)
                            .show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onFailure(Call<FinishDao> call, Throwable t) {

           // btnCleaningFinish.setEnabled(true);

            Toast.makeText(Contextor.getInstance().getContext()
                    ,t.toString()
                    ,Toast.LENGTH_SHORT)
                    .show();
        }
    };

    private void tvSetTextOffer(OfferDataDao offerDataDao) {

        tvCleaning_serviceWanted.setText(offerDataDao.getTypeInfo());
        tvCleaning_placeType.setText(offerDataDao.getPlaceType());
        tvCleaning_moreDetail.setText(offerDataDao.getMoreDetail());

    }

    private void tvSetTextUser(UserDataDAO userDataDAO) {

        tvCleaning_CustomerName.setText(userDataDAO.getFirstName() + " " + userDataDAO.getLastName());
        tvCleaning_CustomerEmail.setText(userDataDAO.getEmail());
        tvCleaning_CustomerNumber.setText(userDataDAO.getPhoneNumber());
    }
}
