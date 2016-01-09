package com.github.baocin.actionscheduler;

import android.Manifest;
import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.github.baocin.actionscheduler.Action.TimedAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.everything.providers.android.calendar.Calendar;
import me.everything.providers.android.calendar.CalendarProvider;
import me.everything.providers.android.calendar.Event;
import me.everything.providers.core.Data;
import android.content.SharedPreferences;

/**
 * Created by aoi on 1/1/16.
 */
public class CalendarReceiver extends BroadcastReceiver {
    private final static String TAG = "CalendarReceiver";
    private Cursor mCursor = null;
    private static final String[] COLS = new String[]
            {CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART, CalendarContract.Events.DTEND, CalendarContract.Events.DESCRIPTION};
    public static final String[] validActionTypes = new String[]{"audio", "video", "photo"};
    private static ArrayList<TimedAction> activeActionList = new ArrayList<>();

    @Override
    public void onReceive(Context context, Intent intent) {
        // add processing here with some query to content provider
        // in my project I use this selection for getting events:
        Log.d(TAG, "Context: " + context.toString() + "   " + intent.toString());
        this.activeActionList = refreshEvents(context);
    }

    public static ArrayList<TimedAction> refreshEvents(Context context) {
        ArrayList<TimedAction> activeActions = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

        CalendarProvider calendarProvider = new CalendarProvider(context);

        //Get current calendar id from shared preferences
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(context);
        long currentCalendarId = Long.parseLong(settings.getString("calendar_choice_id", ""));

        //get the events of that calendar
        Data<Event> currentCalendarEvents = calendarProvider.getEvents(currentCalendarId);
        Calendar currentCalendar = calendarProvider.getCalendar(currentCalendarId);

        Log.d(TAG, currentCalendar.name);
        Log.d(TAG, currentCalendar.id + "");

        //Scan the calendar for events
        for( Event e : currentCalendarEvents.getList()){
//            Log.d(TAG, e.title);
//            Log.d(TAG, e.description);
//            Log.d(TAG, e.calendarId + "");
//            Log.d(TAG, e.status);
//            Log.d(TAG, e.dTStart + "");
//            Log.d(TAG, e.dTend + "");
//            Log.d(TAG, e.id + "");
//            Log.d(TAG, e.syncId);

            if (e.description != null && e.description.length() > 0) {
                //Check if this is a action event
                Pattern pattern = Pattern.compile("#!(.*):?(.*)\\.");
                Matcher match = pattern.matcher(e.description);
                if (match.find()) {
                    //Group 0 is all, 1 is the action type, and 2 is the parameters list
                    String rawActionType = match.group(1);  //first group
                    String[] parameters = null;
                    if (match.groupCount() >= 2) {
                        parameters = match.group(2).split(",");
                    }
                    if (Arrays.asList(validActionTypes).contains(rawActionType)) {
                        Log.d(TAG, rawActionType);
                        Log.d(TAG, "Found action!");
                        activeActions.add(new TimedAction(e.title, rawActionType, parameters, e.dTStart, e.dTend, e.id));
                    }
                }
            }
        }
        return activeActions;
    }
}

