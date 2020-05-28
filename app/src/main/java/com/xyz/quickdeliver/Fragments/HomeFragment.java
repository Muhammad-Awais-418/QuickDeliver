package com.xyz.quickdeliver.Fragments;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.xyz.quickdeliver.R;
import com.xyz.quickdeliver.activities.Error_Screen_Activity;

public class HomeFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //Connection Check
        CheckConnection();


           return view;
    }

    //Function to check internet connection
    public void CheckConnection(){

        ConnectivityManager manager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null != activeNetwork){
            if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                Toast.makeText(getContext(),"Wifi On", Toast.LENGTH_SHORT).show();
            }
            else if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                Toast.makeText(getContext(),"Mobile Data On", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent intent = new Intent(getContext(), Error_Screen_Activity.class);
                startActivity(intent);
                Toast.makeText(getContext(),"No Internet Connection", Toast.LENGTH_SHORT).show();
            }

        }

    }


}
