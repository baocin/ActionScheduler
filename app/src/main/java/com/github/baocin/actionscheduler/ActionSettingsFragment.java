package com.github.baocin.actionscheduler;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class ActionSettingsFragment extends PreferenceFragment
{
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.action_settings);


    }
}
