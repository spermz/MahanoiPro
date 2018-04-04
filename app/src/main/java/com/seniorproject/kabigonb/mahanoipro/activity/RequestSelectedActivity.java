package com.seniorproject.kabigonb.mahanoipro.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;


import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.dao.RequestDataDao;
import com.seniorproject.kabigonb.mahanoipro.fragment.CleaningOfferSelectedFragment;
import com.seniorproject.kabigonb.mahanoipro.fragment.ElectricOfferSelectedFragment;
import com.seniorproject.kabigonb.mahanoipro.fragment.FoodOfferSelectedFragment;
import com.seniorproject.kabigonb.mahanoipro.fragment.PlumbingOfferSelectedFragment;

public class RequestSelectedActivity extends AppCompatActivity {

    Toolbar toolbarRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_selected);

        initInstance();

        RequestDataDao dao = getIntent().getParcelableExtra("dao");


        if(savedInstanceState == null)
        {
            switch(dao.getTypeService()) {
                case 1:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contentContainerRequest, FoodOfferSelectedFragment.newInstance(dao))
                            .commit();
                    break;
                case 2:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contentContainerRequest, ElectricOfferSelectedFragment.newInstance(dao))
                            .commit();
                    break;
                case 3:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contentContainerRequest, PlumbingOfferSelectedFragment.newInstance(dao))
                            .commit();
                    break;
                case 4:
                    getSupportFragmentManager().beginTransaction()
                            .add(R.id.contentContainerRequest, CleaningOfferSelectedFragment.newInstance(dao))
                            .commit();
                    break;
            }

        }

    }

    private void initInstance() {

        toolbarRequest = findViewById(R.id.toolbarRequest);
        setSupportActionBar(toolbarRequest);

    }
}
