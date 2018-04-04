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
public class PlumbingOfferSelectedFragment extends Fragment implements View.OnClickListener {

    RequestDataDao dao;

    TextView tvPlumbingServiceReq,tvPlumbingPart,tvPlumbingProblem,tvPlumbingPlaceType,tvPlumbingMoreDetail;
    Button btnPlumbingOffer,btnPlumbingDenied;

    public PlumbingOfferSelectedFragment() {
        super();
    }

    @SuppressWarnings("unused")
    public static PlumbingOfferSelectedFragment newInstance(RequestDataDao dao) {
        PlumbingOfferSelectedFragment fragment = new PlumbingOfferSelectedFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_plumbing_offer_selected, container, false);
        initInstances(rootView, savedInstanceState);
        return rootView;
    }

    private void init(Bundle savedInstanceState) {
        // Init Fragment level's variable(s) here
    }

    @SuppressWarnings("UnusedParameters")
    private void initInstances(View rootView, Bundle savedInstanceState) {

        // Init 'View' instance(s) with rootView.findViewById here
        tvPlumbingServiceReq = rootView.findViewById(R.id.tvPlumbingServiceReq);
        tvPlumbingPart = rootView.findViewById(R.id.tvPlumbingPart);
        tvPlumbingProblem = rootView.findViewById(R.id.tvPlumbingProblem);
        tvPlumbingPlaceType = rootView.findViewById(R.id.tvPlumbingPlaceType);
        tvPlumbingMoreDetail = rootView.findViewById(R.id.tvPlumbingMoreDetail);

        btnPlumbingOffer = rootView.findViewById(R.id.btnPlumbingOffer);
        btnPlumbingDenied = rootView.findViewById(R.id.btnPlumbingDenied);

        btnPlumbingOffer.setOnClickListener(this);
        btnPlumbingDenied.setOnClickListener(this);

        textViewDataSet();

    }

    private void textViewDataSet() {

        tvPlumbingServiceReq.setText(dao.getTypeInfo());
        tvPlumbingPart.setText(dao.getToolsCheck());
        tvPlumbingProblem.setText(dao.getProblem());
        tvPlumbingPlaceType.setText(dao.getPlaceType());
        tvPlumbingMoreDetail.setText(dao.getMoreDetail());

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
        if(v == btnPlumbingOffer)
        {
            Call<ResponseOfferDao> call = HttpManager.getInstance().getService().providerResponseOffer(offerResponseForm(dao));
            call.enqueue(responseCallBack);
        }
        if(v == btnPlumbingDenied)
        {

        }
    }

    private ResponseOfferDao offerResponseForm(RequestDataDao dao) {

        ResponseOfferDao responseOfferDao = new ResponseOfferDao();
        SharedPreferences prefs = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);

        responseOfferDao.setOfferId(dao.getId());
        responseOfferDao.setProviderName(prefs.getString("userName",null));
        responseOfferDao.setToken(prefs.getString("token",null));

        return  responseOfferDao;


    }

    Callback<ResponseOfferDao> responseCallBack = new Callback<ResponseOfferDao>() {
        @Override
        public void onResponse(Call<ResponseOfferDao> call, Response<ResponseOfferDao> response) {

            if (response.isSuccessful())
            {
                ResponseOfferDao dao = response.body();

                if (dao.getErrorMessage() != null) {
                    Toast.makeText(Contextor.getInstance().getContext()
                            , dao.getErrorMessage()
                            , Toast.LENGTH_SHORT)
                            .show();
                } else if (dao.getStatus().equals("save")) {
                    Toast.makeText(Contextor.getInstance().getContext()
                            , "Request has been offered"
                            , Toast.LENGTH_SHORT)
                            .show();
                    getActivity().finish();
                } else {
                    Toast.makeText(Contextor.getInstance().getContext()
                            , dao.getStatus()
                            , Toast.LENGTH_SHORT)
                            .show();
                }
            }

            else
            {
                try {
                    Toast.makeText(Contextor.getInstance().getContext(), response.errorBody().string(), Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void onFailure(Call<ResponseOfferDao> call, Throwable t)
        {
            Toast.makeText(Contextor.getInstance().getContext(), t.toString(), Toast.LENGTH_SHORT).show();
        }
    };
}
