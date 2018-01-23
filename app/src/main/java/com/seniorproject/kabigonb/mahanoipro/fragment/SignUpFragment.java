package com.seniorproject.kabigonb.mahanoipro.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.seniorproject.kabigonb.mahanoipro.R;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener {

    Button register;

    public SignUpFragment() {
        super();
    }

    public static SignUpFragment newInstance() {
        SignUpFragment fragment = new SignUpFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_signup, container, false);
        initInstances(rootView);
        register.setOnClickListener(this);
        return rootView;
    }

    private void initInstances(View rootView) {
        // Init 'View' instance(s) with rootView.findViewById here
        register = rootView.findViewById(R.id.btnRegister);

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
        if(v == register)
        {
            getFragmentManager().beginTransaction()
                    .replace(R.id.contentContainer,MainFragment.newInstance())
                    .commit();
        }
    }
}
