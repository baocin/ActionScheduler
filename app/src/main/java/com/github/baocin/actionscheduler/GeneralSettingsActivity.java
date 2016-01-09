package com.github.baocin.actionscheduler;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by aoi on 1/4/16.
 */
public class GeneralSettingsActivity extends AppCompatActivity{
    public Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_settings);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        getFragmentManager().beginTransaction().replace(R.id.fragment, new GeneralSettingsFragment()).commit();
    }
}

