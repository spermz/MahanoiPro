package com.seniorproject.kabigonb.mahanoipro.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.dao.RegisterDao;
import com.seniorproject.kabigonb.mahanoipro.manager.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AccountFragment extends Fragment implements View.OnClickListener {

    EditText etAccountFragment_Name,etAccountFragment_phoneNumber,etAccountFragment_detail,etAccountFragment_lastName;
    Button btnAccount_save;

    public AccountFragment() {
        super();
    }

    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);
        initInstances(rootView);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        etAccountFragment_Name = rootView.findViewById(R.id.etAccountFragment_Name);
        etAccountFragment_phoneNumber = rootView.findViewById(R.id.etAccountFragment_phoneNumber);
        etAccountFragment_detail = rootView.findViewById(R.id.etAccountFragment_detail);
        etAccountFragment_lastName = rootView.findViewById(R.id.etAccountFragment_lastName);

        btnAccount_save = rootView.findViewById(R.id.btnAccount_save);

        btnAccount_save.setOnClickListener(this);

        Call<RegisterDao> call = HttpManager.getInstance().getService().loadProviderDetail(requestForm());
        call.enqueue(callbackDetailLoad);

    }

    private RegisterDao requestForm() {

        RegisterDao dao = new RegisterDao();
        SharedPreferences prefs = getActivity().getSharedPreferences("token",Context.MODE_PRIVATE);

        dao.setToken(prefs.getString("token",null));
        dao.setUsername(prefs.getString("userName",null));

        return dao;
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
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore Instance State here
        }
    }

    @Override
    public void onClick(View v) {
        if(v == btnAccount_save)
        {
            btnAccount_save.setEnabled(false);

            Call<RegisterDao> call = HttpManager.getInstance().getService().providerUpdate(updateForm());
            call.enqueue(callbackUpdate);

        }
    }

    private RegisterDao updateForm() {

        RegisterDao dao = new RegisterDao();
        SharedPreferences prefs = getActivity().getSharedPreferences("token", Context.MODE_PRIVATE);

        dao.setToken(prefs.getString("token",null));
        dao.setUsername(prefs.getString("userName",null));

        dao.setFirstname(etAccountFragment_Name.getText().toString());
        dao.setLastname(etAccountFragment_lastName.getText().toString());
        dao.setNumber(etAccountFragment_phoneNumber.getText().toString());
        dao.setDetail(etAccountFragment_detail.getText().toString());

        return dao;

    }

    Callback<RegisterDao> callbackUpdate = new Callback<RegisterDao>() {
        @Override
        public void onResponse(Call<RegisterDao> call, Response<RegisterDao> response) {
            btnAccount_save.setEnabled(true);

            if(response.isSuccessful())
            {
                RegisterDao dao = response.body();

                if(dao.getErrorMessage() != null)
                {
                    Toast.makeText(Contextor.getInstance().getContext()
                            ,dao.getErrorMessage()
                            ,Toast.LENGTH_SHORT)
                            .show();
                }

                else
                {
                    Toast.makeText(Contextor.getInstance().getContext()
                            ,dao.getStatusMessage()
                            ,Toast.LENGTH_SHORT)
                            .show();
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
        public void onFailure(Call<RegisterDao> call, Throwable t) {
            btnAccount_save.setEnabled(true);

            Toast.makeText(Contextor.getInstance().getContext()
                    ,t.toString()
                    ,Toast.LENGTH_SHORT)
                    .show();

        }
    };

    Callback<RegisterDao> callbackDetailLoad = new Callback<RegisterDao>() {
        @Override
        public void onResponse(Call<RegisterDao> call, Response<RegisterDao> response) {

            if(response.isSuccessful())
            {
                RegisterDao dao = response.body();
                etAccountFragment_Name.setText(dao.getFirstname());
                etAccountFragment_phoneNumber.setText(dao.getNumber());
                etAccountFragment_detail.setText(dao.getDetail());
                etAccountFragment_lastName.setText(dao.getLastname());

            }
            else
            {
                try {
                    Toast.makeText(Contextor.getInstance().getContext(),response.errorBody().string(),Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        @Override
        public void onFailure(Call<RegisterDao> call, Throwable t) {
            Toast.makeText(Contextor.getInstance().getContext(),t.toString(),Toast.LENGTH_SHORT).show();
        }
    };
}
