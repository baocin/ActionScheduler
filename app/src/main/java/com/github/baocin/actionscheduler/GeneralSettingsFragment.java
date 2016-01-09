package com.github.baocin.actionscheduler;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.everything.providers.android.calendar.Calendar;
import me.everything.providers.android.calendar.CalendarProvider;

public class GeneralSettingsFragment extends PreferenceFragment
{
    private final static String TAG = "GeneralSettingsFragment";
    SharedPreferences settings = null;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.general_settings);

        settings = PreferenceManager.getDefaultSharedPreferences(getActivity());

        final long currentCalendarId = Long.parseLong(settings.getString("calendar_choice_id", ""));
        ListPreference calendarChoice = (ListPreference) findPreference("calendar_choice_id");

        final CalendarProvider calendarProvider = new CalendarProvider(getActivity());
        List<Calendar> calendars = calendarProvider.getCalendars().getList();

        //Sort
        Collections.sort(calendars, new Comparator<Calendar>() {
            @Override
            public int compare(Calendar lhs, Calendar rhs) {
                int lhsFirstChar = (int) lhs.name.toString().toLowerCase().charAt(0);
                int rhsFirstChar = (int) rhs.name.toString().toLowerCase().charAt(0);
                if (lhsFirstChar > rhsFirstChar || rhs.id == currentCalendarId) {
                    return 1;
                } else if (lhsFirstChar < rhsFirstChar) {
                    return -1;
                }
                return 0;
            }
        });



        CharSequence[] calendarNames = new CharSequence[calendars.size()];
        CharSequence[] calendarValues = new CharSequence[calendars.size()];
//        calendarNames[0] = "Testing";
//        calendarValues[0] = "Tesitdskfj";
        for (int i = 0; i < calendars.size(); i++){
//            Log.d(TAG, "Calendar " + calendars.get(i).name);
            calendarNames[i] = calendars.get(i).name;
            calendarValues[i] = "" + calendars.get(i).id;
        }

//        Log.d(TAG, "Choice: " + calendarChoice);
        calendarChoice.setEntries(calendarNames);
        calendarChoice.setEntryValues(calendarValues);


    }

    @Override
    public void onResume() {
        super.onResume();
        if (settings.getBoolean("firstrun", true)) {
            settings.edit().putBoolean("firstrun", false).commit();
        }
    }
}
