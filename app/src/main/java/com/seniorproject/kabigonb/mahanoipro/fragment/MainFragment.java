package com.seniorproject.kabigonb.mahanoipro.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toolbar;

import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.activity.Main2Activity;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    Button btnSignUp;
    Button btnLogin;
    Button btnForgetPassword;
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
            Intent intent = new Intent(getActivity(), Main2Activity.class);
            startActivity(intent);

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
}
