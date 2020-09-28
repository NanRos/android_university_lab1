package com.codepath.bestsellerlistapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set layout
        setContentView(R.layout.activity_main);
        // returns the Fragment Manager for interacting with fragments with this activity
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        //Start a series of edit operations on the Fragments associated with this FragmentManger
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        //replace an existing fragment that was added to a container
        //replace(int containerViewId, Fragment fragment, String tag)
        fragmentTransaction.replace(R.id.content, new BestSellerBooksFragment(), null).commit();
    }


}
