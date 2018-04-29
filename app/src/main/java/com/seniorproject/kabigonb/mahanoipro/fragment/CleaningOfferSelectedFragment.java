package com.seniorproject.kabigonb.mahanoipro.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.dao.RequestDataDao;
import com.seniorproject.kabigonb.mahanoipro.dao.ResponseOfferDao;
import com.seniorproject.kabigonb.mahanoipro.manager.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



@SuppressWarnings("unused")
public class CleaningOfferSelectedFragment extends Fragment implements View.OnClickListener {

    RequestDataDao dao;
    TextView tvCleaningWorkType,tvCleaningPlaceType,tvCleaningRoomAmount,tvCleaningMoreDetails;
    Button btnCleaningOffer;

    public CleaningOfferSelectedFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static CleaningOfferSelectedFragment newInstance(RequestDataDao dao) {
        CleaningOfferSelectedFragment fragment = new CleaningOfferSelectedFragment();
        Bundle args = new Bundle();
        args.putParcelable("dao", dao);
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
        View rootView = inflater.inflate(R.layout.fragment_cleaning_offer_selected, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {
        // Init 'View' instance(s) with rootView.findViewById here
        tvCleaningWorkType = rootView.findViewById(R.id.tvCleaningWorkType);
        tvCleaningPlaceType = rootView.findViewById(R.id.tvCleaningPlaceType);
        tvCleaningRoomAmount = rootView.findViewById(R.id.tvCleaningRoomAmount);
        tvCleaningMoreDetails = rootView.findViewById(R.id.tvCleaningMoreDetails);

        btnCleaningOffer = rootView.findViewById(R.id.btnCleaningOffer);

        btnCleaningOffer.setOnClickListener(this);

        textDataSet(dao);
    }

    private void textDataSet(RequestDataDao dao) {

        tvCleaningWorkType.setText(dao.getTypeInfo());
        tvCleaningPlaceType.setText(dao.getPlaceType());
        tvCleaningRoomAmount.setText(dao.getAmount());
        tvCleaningMoreDetails.setText(dao.getMoreDetail());

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

        if(v == btnCleaningOffer)
        {
            btnCleaningOffer.setEnabled(false);
            Call<ResponseOfferDao> call = HttpManager.getInstance().getService().providerResponseOffer(responseOfferForm(dao));
            call.enqueue(responseCallBack);
        }

    }

    private ResponseOfferDao responseOfferForm(RequestDataDao dao) {

        ResponseOfferDao responseOfferDao = new ResponseOfferDao();
        SharedPreferences prefs = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);

        responseOfferDao.setOfferId(dao.getId());
        responseOfferDao.setProviderName(prefs.getString("userName",null));
        responseOfferDao.setToken(prefs.getString("token",null));

        return responseOfferDao;


    }

    Callback<ResponseOfferDao> responseCallBack = new Callback<ResponseOfferDao>() {
        @Override
        public void onResponse(Call<ResponseOfferDao> call, Response<ResponseOfferDao> response) {

            btnCleaningOffer.setEnabled(true);

            if (response.isSuccessful()) {
                ResponseOfferDao dao = response.body();

                if (dao.getErrorMessage() != null) {
                    Toast.makeText(Contextor.getInstance().getContext()
                            , dao.getErrorMessage()
                            , Toast.LENGTH_SHORT)
                            .show();
                }
                else if (dao.getStatus().equals("save")) {
                    Toast.makeText(Contextor.getInstance().getContext()
                            , "Request has been offered"
                            , Toast.LENGTH_SHORT)
                            .show();
                    getActivity().finish();
                }
                else {
                    Toast.makeText(Contextor.getInstance().getContext()
                            , dao.getStatus()
                            , Toast.LENGTH_SHORT)
                            .show();
                }
            }
            else {
                try {
                    Toast.makeText(Contextor.getInstance().getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onFailure(Call<ResponseOfferDao> call, Throwable t) {

            btnCleaningOffer.setEnabled(true);
            Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
