package com.xyz.quickdeliver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.xyz.quickdeliver.Fragments.HomeFragment;
import com.xyz.quickdeliver.Fragments.ProfileFragment;
import com.xyz.quickdeliver.activities.Error_Screen_Activity;
import com.xyz.quickdeliver.utils.Tools;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private int Check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Checking Internet Connection
        CheckConnection();
        //To Check Internet Connection
        if (Check == 1) {
            setContentView(R.layout.activity_main);
        } else {
            //Calling Error Screen Activity
            Intent intent = new Intent(getApplicationContext(), Error_Screen_Activity.class);
            startActivity(intent);
        }
        //Calling Toolbar initialization Function
        initToolbar();


        //Adding a bottom navigation bar
        BottomNavigationView navView = findViewById(R.id.nav_view);
        //Navigation Bar listener
        navView.setOnNavigationItemSelectedListener(this);
        //Loading Fragments
        loadFragment(new HomeFragment());

    }

    //Function to load Fragments
    public boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentcontainer, fragment).addToBackStack(null)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int op = 0;

        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                fragment = new HomeFragment();
                break;

            case R.id.nav_acc:
                if (getloginprefference() == true) {
                    fragment = new ProfileFragment();
                    break;
                } else {

                }
        }

        if (op != 1)
            return loadFragment(fragment);
        return false;
    }

    private boolean getloginprefference() {

        loginPreferences = getSharedPreferences("loginPref", MODE_PRIVATE);

        loginPrefsEditor = loginPreferences.edit();
        String username = loginPreferences.getString("username", "");
        String pass = loginPreferences.getString("password", "");
        if (username != null || pass != null) {
            return true;
        } else {
            return false;
        }

    }

    //Internet Connection Check
    public void CheckConnection() {

        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null != activeNetwork) {
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI) {
                Toast.makeText(this, "Wifi On", Toast.LENGTH_SHORT).show();
                Check = 1;
            } else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE) {
                Toast.makeText(this, "Mobile Data On", Toast.LENGTH_SHORT).show();
                Check = 1;
            } else {
                Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                Check = 0;
            }

        }
    }

    //Initializing Toolbar
    private void initToolbar() {
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }


}
