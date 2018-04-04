package com.seniorproject.kabigonb.mahanoipro.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.seniorproject.kabigonb.mahanoipro.R;
import com.seniorproject.kabigonb.mahanoipro.dao.LogoutDao;
import com.seniorproject.kabigonb.mahanoipro.dao.RequestDataDao;
import com.seniorproject.kabigonb.mahanoipro.dao.ServiceCollectionDao;
import com.seniorproject.kabigonb.mahanoipro.dao.TokenDao;
import com.seniorproject.kabigonb.mahanoipro.dao.WorkListDataDao;
import com.seniorproject.kabigonb.mahanoipro.fragment.AccountFragment;
import com.seniorproject.kabigonb.mahanoipro.fragment.RequestFragment;
import com.seniorproject.kabigonb.mahanoipro.fragment.WorkFragment;
import com.seniorproject.kabigonb.mahanoipro.manager.HttpManager;
import com.seniorproject.kabigonb.mahanoipro.manager.RequestListManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Main2Activity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener
        , Callback<TokenDao>
        ,RequestFragment.FragmentListener
        ,WorkFragment.WorkFragmentListener{

    BottomNavigationView bnvMenu;
    Toolbar toolBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initInstance();

        Intent intent = getIntent();

        Bundle bundle = intent.getBundleExtra("tokenBundle");
        TokenDao tokenDao = new TokenDao();
        tokenDao.setToken(bundle.getString("token"));
        tokenDao.setUserName(bundle.getString("userName"));
        tokenDao.setTypeService(bundle.getInt("typeService"));
        saveToken(tokenDao);



        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contentContainer2, RequestFragment.newInstance())
                    .commit();
        }

        bnvMenu.setOnNavigationItemSelectedListener(this);
        bnvMenu.setSelectedItemId(R.id.item_request);
        
    }

    private void saveToken(TokenDao tokenDao) {

        SharedPreferences prefs = getApplicationContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token",tokenDao.getToken());
        editor.putString("userName",tokenDao.getUserName());
        editor.putInt("typeService",tokenDao.getTypeService());
        editor.apply();
    }


    private void initInstance() {
            toolBar2 = findViewById(R.id.toolbar2);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_logout,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menuLoguot)
        {

            Call<TokenDao> call = HttpManager.getInstance().getService().providerLogout(makeLogOutToken());
            call.enqueue(this);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private LogoutDao makeLogOutToken() {

        LogoutDao logoutDao = new LogoutDao();
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("token", Context.MODE_PRIVATE);

        String value = prefs.getString("token",null);
        String value2 = prefs.getString("userName",null);

        logoutDao.setToken(value);
        logoutDao.setUserName(value2);

        return logoutDao;
    }

    @Override
    public void onResponse(Call<TokenDao> call, Response<TokenDao> response) {

        if(response.isSuccessful())
        {
            TokenDao token = response.body();
            if(token.getErrorMessage() != null)
            {
                Toast.makeText(getApplicationContext(),token.getErrorMessage(),Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(),token.getStatusMessage(),Toast.LENGTH_LONG).show();
                removeToken();
                Intent intentLogIn = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentLogIn);
                finish();
            }
        }
        else
        {
            try {
                Toast.makeText(getApplicationContext(),response.errorBody().string(),Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private void removeToken() {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("token", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("token","");
        editor.putString("userName","");
        editor.putInt("typeService",0);
        editor.apply();
    }

    @Override
    public void onFailure(Call<TokenDao> call, Throwable t) {
        Toast.makeText(getApplicationContext(),t.toString(),Toast.LENGTH_LONG).show();
    }

    @Override
    public void onRequestItemClicked(RequestDataDao dao) {
        Intent intent = new Intent(Main2Activity.this,RequestSelectedActivity.class);
        intent.putExtra("dao",  dao);
        startActivity(intent);
    }

    @Override
    public void onWorkListClicked(WorkListDataDao dao) {

        Intent intent = new Intent(Main2Activity.this,WorkSelectedActivity.class);
        intent.putExtra("dao",dao);
        startActivity(intent);
    }
}

