package com.seniorproject.kabigonb.mahanoipro.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.fragment.MainFragment;

public class MainActivity extends AppCompatActivity {

    Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();
        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer, MainFragment.newInstance()).commit();
        }

    }

    private void initInstance() {
        toolBar = findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);

    }
}
