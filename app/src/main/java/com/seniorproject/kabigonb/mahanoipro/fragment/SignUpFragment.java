package com.seniorproject.kabigonb.mahanoipro.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.dao.RegisterDao;
import com.seniorproject.kabigonb.mahanoipro.manager.HttpManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by nuuneoi on 11/16/2014.
 */
public class SignUpFragment extends Fragment implements View.OnClickListener, Callback<RegisterDao> {

    Button register;
    EditText etEmail,etPassword,etName,etLastname,etCitizenId,etPhoneNumber,etUsername;
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

        etEmail = rootView.findViewById(R.id.etEmail);
        etCitizenId = rootView.findViewById(R.id.etCitizenId);
        etName = rootView.findViewById(R.id.etName);
        etLastname = rootView.findViewById(R.id.etLastName);
        etPassword = rootView.findViewById(R.id.etPassword);
        etPhoneNumber = rootView.findViewById(R.id.etPhoneNumber);
        etUsername = rootView.findViewById(R.id.etUsername);

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
            Call<RegisterDao> call = HttpManager.getInstance().getService().registerProvider(regFormDAO());
            call.enqueue(this);

        }
    }

    private RegisterDao regFormDAO() {

        RegisterDao registerForm = new RegisterDao();
        registerForm.setUsername(etUsername.getText().toString());
        registerForm.setEmail(etEmail.getText().toString());
        registerForm.setPassword(etPassword.getText().toString());
        registerForm.setFirstname(etName.getText().toString());
        registerForm.setLastname(etLastname.getText().toString());
        registerForm.setCitizenId(etCitizenId.getText().toString());
        registerForm.setNumber(etPhoneNumber.getText().toString());
        return registerForm;

    }

    @Override
    public void onResponse(Call<RegisterDao> call, Response<RegisterDao> response) {

        if(response.isSuccessful())
        {
            RegisterDao signupResponse = response.body();
            if(signupResponse.getErrorMessage() != null)
            {
                Toast.makeText(getActivity(), signupResponse.getErrorMessage(), Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getActivity(), "Register Successful", Toast.LENGTH_LONG).show();
                getFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer,MainFragment.newInstance())
                        .commit();
            }

        }
        else
        {
            try {
                Toast.makeText(getActivity(),response.errorBody().string(),Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onFailure(Call<RegisterDao> call, Throwable t) {
        Toast.makeText(getActivity(),t.toString(),Toast.LENGTH_LONG).show();

    }

    /*            getFragmentManager().beginTransaction().
                    replace(R.id.contentContainer, MainFragment.newInstance())
                    .commit();
                    */
}
