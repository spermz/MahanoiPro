package com.seniorproject.kabigonb.mahanoipro.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;
import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.activity.Main2Activity;
import com.seniorproject.kabigonb.mahanoipro.dao.LoginDao;
import com.seniorproject.kabigonb.mahanoipro.dao.TokenDao;
import com.seniorproject.kabigonb.mahanoipro.manager.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainFragment extends Fragment implements View.OnClickListener, Callback<TokenDao> {

    Button btnSignUp;
    Button btnLogin;
    Button btnForgetPassword;

    EditText etUserName,etPassword;
  //  android.support.v7.widget.Toolbar toolbar;

    public MainFragment() {
        super();
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initInstances(rootView);

        btnSignUp.setOnClickListener(this);
        btnForgetPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
     //   toolbar = rootView.findViewById(R.id.toolbar);
        btnSignUp =  rootView.findViewById(R.id.btnSignUp);
        btnLogin =  rootView.findViewById(R.id.btnLogin);
        btnForgetPassword =  rootView.findViewById(R.id.btnForgetPassword);

        etUserName = rootView.findViewById(R.id.etUsername_login);
        etPassword = rootView.findViewById(R.id.etPassword_login);
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
        if(v == btnLogin) {
            Call<TokenDao> call = HttpManager.getInstance().getService().providerLogin(loginForm());
            call.enqueue(this);
        }
        else if(v == btnSignUp) {
            getFragmentManager().beginTransaction().
                    replace(R.id.contentContainer, SignUpFragment.newInstance())
                    .addToBackStack(null)
                    .commit();
        }
        else if(v == btnForgetPassword) {

            getFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer,ForgetPasswordFragment.newInstance())
                    .addToBackStack(null)
                    .commit();

        }

    }

    private LoginDao loginForm() {

        LoginDao loginForm = new LoginDao();
        loginForm.setUsername(etUserName.getText().toString());
        loginForm.setPassword(etPassword.getText().toString());
        return loginForm;
    }


    @Override
    public void onResponse(Call<TokenDao> call, Response<TokenDao> response) {
        if(response.isSuccessful())
        {
            TokenDao loginResponse = response.body();
            if(loginResponse.getErrorMessage() != null)
            {
                Toast.makeText(Contextor.getInstance().getContext(), loginResponse.getErrorMessage(), Toast.LENGTH_LONG).show();
            }
            else if(loginResponse.getStatusMessage() != null)
            {
                Toast.makeText(Contextor.getInstance().getContext(), loginResponse.getStatusMessage(), Toast.LENGTH_LONG).show();
            }
            else
            {
                Bundle bundle = new Bundle();
                bundle.putString("token",loginResponse.getToken());
                bundle.putString("userName",etUserName.getText().toString());
                bundle.putInt("typeService",loginResponse.getTypeService());

                Intent intentLogIn = new Intent(getActivity(), Main2Activity.class);
                intentLogIn.putExtra("tokenBundle",bundle);
                startActivity(intentLogIn);
                getActivity().finish();
            }

        }
        else
        {
            try {
                Toast.makeText(Contextor.getInstance().getContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<TokenDao> call, Throwable t) {
        Toast.makeText(Contextor.getInstance().getContext(),t.toString(),Toast.LENGTH_LONG).show();
    }
}
