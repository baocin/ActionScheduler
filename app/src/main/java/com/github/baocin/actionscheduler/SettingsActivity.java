//package com.github.baocin.actionscheduler;
//
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.util.Log;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.Spinner;
//
//import java.lang.reflect.Array;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Comparator;
//import java.util.List;
//
//import me.everything.providers.android.calendar.Calendar;
//import me.everything.providers.android.calendar.CalendarProvider;
//
///**
// * Created by aoi on 1/1/16.
// */
//public class SettingsActivity extends AppCompatActivity {
//    private final static String TAG = "SettingsActivity";
//    private Toolbar toolbar;
//    private ArrayAdapter<CharSequence> adapter;
//    public static final String PREFS_NAME = "GeneralSettings";
//    public static final String CALENDAR_NAME_KEY = "actionCalenderName";
//    public static final String CALENDAR_POSITION_KEY = "actionCalenderPosition";
//    public SharedPreferences settings;
//    public static long currentCalendarId = 1;
//
//    public SettingsActivity(){
//
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.general_settings);
//
//        settings = getSharedPreferences(PREFS_NAME, 0);
//
//        //Access to current app settings
//        Log.d(TAG, "Current Settings: " + settings.getAll().toString());
//
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        Spinner calendarSpinner = (Spinner) findViewById(R.id.calendar_spinner);
//        final CalendarProvider calendarProvider = new CalendarProvider(this.getApplicationContext());
//        final ArrayAdapter<Calendar> rawAdapter = new ArrayAdapter<Calendar>(this, android.R.layout.simple_spinner_item);
//        List<Calendar> calendarsList = calendarProvider.getCalendars().getList();
//        rawAdapter.addAll(calendarsList);
//
//        //Sort so that previously used calendar is selected by default
//
//        rawAdapter.sort(new Comparator<Calendar>() {
//            @Override
//            public int compare(Calendar lhs, Calendar rhs) {
//                int lhsFirstChar = (int) lhs.name.toString().toLowerCase().charAt(0);
//                int rhsFirstChar = (int) rhs.name.toString().toLowerCase().charAt(0);
//                if (lhsFirstChar > rhsFirstChar || rhs.id == currentCalendarId) {
//                    return 1;
//                } else if (lhsFirstChar < rhsFirstChar) {
//                    return -1;
//                }
//                return 0;
//            }
//        });
//
//        final ArrayList<Long> sortOrder = new ArrayList<>();
//        final ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item);
//        for (int i = 0; i < rawAdapter.getCount(); i++){
//            sortOrder.add(i, rawAdapter.getItem(i).id);
//            adapter.add(rawAdapter.getItem(i).name);
//        }
//
//        Log.d(TAG, "Sort order: " + sortOrder.toString());
//
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        calendarSpinner.setAdapter(adapter);
//
//        calendarSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int pos, long id) {
//                if (sortOrder.get(pos) == 0) return;
//                SharedPreferences.Editor editor = settings.edit();
//                String itemText = parentView.getSelectedItem().toString();
//                editor.putString(CALENDAR_NAME_KEY, itemText);
//                editor.putLong(CALENDAR_POSITION_KEY, sortOrder.get(pos));
//                editor.commit();
//                Log.d(TAG, "New Settings: " + settings.getAll().toString());
//
//                currentCalendarId = sortOrder.get(pos);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//            }
//
//        });
////
////        int itemPosition = (int) calendarSpinner.getSelectedItemId();
////
////        Log.d(TAG, "Selected index " + itemPosition);
////        SharedPreferences.Editor editor = settings.edit();
////        editor.putInt(CALENDAR_POSITION_KEY, itemPosition);
////        editor.commit();
////        Log.d(TAG, "New Settings: " + settings.getAll().toString());
//    }
//
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//    }
//}
