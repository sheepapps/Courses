package com.example.user.catrunner;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import fragments.HistoryFragment;
import fragments.HomeFragment;
import fragments.InfoFragment;
import fragments.ProfileFragment;
import fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity {

    public ImageButton btnHome;
    public ImageButton btnProfile;
    public ImageButton btnHistory;
    public ImageButton btnSettings;
    public ImageButton btnInfo;
    public HomeFragment homeFragment;
    public HistoryFragment historyFragment;
    public FragmentTransaction fragmentTransaction;
    public ProfileFragment profileFragment;
    public SettingsFragment settingsFragment;
    public InfoFragment infoFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnHistory = findViewById(R.id.btn_history);
        btnHome = findViewById(R.id.btn_home);
        btnProfile = findViewById(R.id.btn_profile);
        btnSettings = findViewById(R.id.btn_settings);
        btnInfo = findViewById(R.id.btn_info);
        homeFragment = new HomeFragment();
        historyFragment = new HistoryFragment();
        profileFragment = new ProfileFragment();
        settingsFragment = new SettingsFragment();
        infoFragment = new InfoFragment();
//
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frgmCont, homeFragment);
        btnHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_25dp_selected));
        fragmentTransaction.commit();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frgmCont, homeFragment);
        fragmentTransaction.commit();
    }

    public void onClick(View v) {
        fragmentTransaction = getFragmentManager().beginTransaction();
        switch (v.getId()) {
            case R.id.btn_home:
                fragmentTransaction.replace(R.id.frgmCont, homeFragment);
                btnHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_25dp_selected));
                Toast.makeText(MainActivity.this, getResources().getText(R.string.kek), Toast.LENGTH_LONG).show();
                setTitle(getResources().getString(R.string.app_name));
                break;
            case R.id.btn_history:
                btnHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_25dp));
                fragmentTransaction.replace(R.id.frgmCont, historyFragment);
                setTitle(getResources().getString(R.string.title_history));
                break;
            case R.id.btn_profile:
                btnHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_25dp));
                fragmentTransaction.replace(R.id.frgmCont, profileFragment);
                setTitle(getResources().getString(R.string.title_profile));
                break;
            case R.id.btn_settings:
                btnHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_25dp));
                fragmentTransaction.replace(R.id.frgmCont, settingsFragment);
                setTitle(getResources().getString(R.string.title_settings));
                break;
            case R.id.btn_info:
                btnHome.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_25dp));
                fragmentTransaction.replace(R.id.frgmCont, infoFragment);
                setTitle(getResources().getString(R.string.title_info));
                break;
            default:
                break;
        }
//        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}