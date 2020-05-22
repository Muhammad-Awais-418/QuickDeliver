package com.xyz.quickdeliver.activities;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.xyz.quickdeliver.Fragments.HomeFragment;
import com.xyz.quickdeliver.R;

public class Error_Screen_Activity extends AppCompatActivity {

    private Button reload;
    private int Check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error__screen_);

        reload = findViewById(R.id.Reload_button);

        reload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CheckConnection();
                //Value check
                if (Check == 1){
                    Fragment fragment = new HomeFragment();
                    //
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragmentcontainer,fragment)
                            .addToBackStack(null)
                            .commit();
                }
                else if (Check == 0){
                    Toast.makeText(getApplicationContext(),"No Internet", Toast.LENGTH_SHORT).show();
                    recreate();
                }

            }
        });

    }

    //Function to Check Internet connection
    public void CheckConnection(){

        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null != activeNetwork){
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                Toast.makeText(this,"Wifi On", Toast.LENGTH_SHORT).show();
                 Check = 1;
            }
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(this,"Mobile Data On", Toast.LENGTH_SHORT).show();
                Check = 1;
            }
            else{
                Toast.makeText(this,"No Internet Connection", Toast.LENGTH_SHORT).show();
                Check = 0;
            }

        }
    }

}
