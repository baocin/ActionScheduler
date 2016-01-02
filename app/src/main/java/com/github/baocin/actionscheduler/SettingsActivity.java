package com.github.baocin.actionscheduler;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import me.everything.providers.android.calendar.Calendar;
import me.everything.providers.android.calendar.CalendarProvider;

/**
 * Created by aoi on 1/1/16.
 */
public class SettingsActivity extends AppCompatActivity {
    private final static String TAG = "SettingsActivity";
    private Toolbar toolbar;

    public SettingsActivity(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner calendarSpinner = (Spinner) findViewById(R.id.calendar_spinner);
        //Get all calendars
        CalendarProvider calendarProvider = new CalendarProvider(this.getApplicationContext());
//        ArrayList<CharSequence> calendarNames = new ArrayList();

        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        for (Calendar c : calendarProvider.getCalendars().getList()){
            adapter.add(c.name);
//            calendarNames.add(c.name);
        }
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        calendarSpinner.setAdapter(adapter);

//        calendarSpinner.
//
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
////        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        viewPager = (ViewPager) findViewById(R.id.viewpager);
//        setupViewPager(viewPager);
    }

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            View v = inflater.inflate(R.layout.fragment_create, container, false);
//            ListView createActionList = (ListView) v.findViewById(R.id.createActionList);
//            CreateListAdapter createListAdapter = new CreateListAdapter();
//            createActionList.setAdapter(createListAdapter);
//            return v;
//        }
    }
