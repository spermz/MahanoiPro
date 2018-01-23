package com.seniorproject.kabigonb.mahanoipro.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.fragment.AccountFragment;
import com.seniorproject.kabigonb.mahanoipro.fragment.RequestFragment;
import com.seniorproject.kabigonb.mahanoipro.fragment.WorkFragment;


public class Main2Activity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bnvMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initInstance();

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer2, RequestFragment.newInstance()).commit();
        }
        bnvMenu.setOnNavigationItemSelectedListener(this);
        bnvMenu.setSelectedItemId(R.id.item_request);
        
    }


    private void initInstance() {
            Toolbar toolBar2 = findViewById(R.id.toolbar);
            setSupportActionBar(toolBar2);

            bnvMenu = findViewById(R.id.bnvMenu);
        }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.item_request:
                // do this event
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer2, RequestFragment.newInstance() )
                        .commit();
                return true;
            case R.id.item_work:
                // do this event
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer2, WorkFragment.newInstance() )
                        .commit();
                return true;
            case R.id.item_account:
                // do this event
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.contentContainer2, AccountFragment.newInstance() )
                        .commit();
                return true;
        }
        return false;
    }
}

