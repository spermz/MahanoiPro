package com.seniorproject.kabigonb.mahanoipro.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.dao.WorkListDataDao;
import com.seniorproject.kabigonb.mahanoipro.fragment.CleaningRequestSelectedFragment;
import com.seniorproject.kabigonb.mahanoipro.fragment.ElectricRequestSelectedFragment;
import com.seniorproject.kabigonb.mahanoipro.fragment.FoodRequestSelectedFragment;
import com.seniorproject.kabigonb.mahanoipro.fragment.PlumbingRequestSelectedFragment;

public class WorkSelectedActivity extends AppCompatActivity {

    Toolbar toolbarWork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_selected);

        WorkListDataDao dao = getIntent().getParcelableExtra("dao");

        initInstance();
        if(savedInstanceState == null)
        {
           switch(dao.getTypeService())
           {
               case 1:
                   getSupportFragmentManager().beginTransaction()
                           .add(R.id.contentContainerWork, FoodRequestSelectedFragment.newInstance(dao))
                           .commit();
                   break;
               case 2:
                   getSupportFragmentManager().beginTransaction()
                           .add(R.id.contentContainerWork, ElectricRequestSelectedFragment.newInstance(dao))
                           .commit();
                   break;
               case 3:
                   getSupportFragmentManager().beginTransaction()
                           .add(R.id.contentContainerWork, PlumbingRequestSelectedFragment.newInstance(dao))
                           .commit();
                   break;
               case 4:
                   getSupportFragmentManager().beginTransaction()
                           .add(R.id.contentContainerWork, CleaningRequestSelectedFragment.newInstance(dao))
                           .commit();
                   break;
           }
        }
    }

    private void initInstance() {

        toolbarWork = findViewById(R.id.toolbarWork);
        setSupportActionBar(toolbarWork);

    }
}
