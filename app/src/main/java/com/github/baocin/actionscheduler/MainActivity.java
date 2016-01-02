package com.github.baocin.actionscheduler;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.github.baocin.actionscheduler.Action.TimedAction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Cursor mCursor = null;
    private static final String[] COLS = new String[]
        {CalendarContract.Events.TITLE, CalendarContract.Events.DTSTART, CalendarContract.Events.DTEND, CalendarContract.Events.DESCRIPTION};
    private String[] validActionTypes = new String[]{"record_audio", "record_video", "take_photo"};
    private static ArrayList<TimedAction> activeActionList = new ArrayList<>();

    public static ArrayList<TimedAction> getActiveActionList() {
        return activeActionList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_HOME_AS_UP | ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

//        getSupportActionBar().
//        getSupportActionBar().setIcon(R.drawable.common_google_signin_btn_icon_dark);
//        getActionBar().
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Get all calendars
//        CalendarProvider calendarProvider = new CalendarProvider(this.getApplicationContext());
//        for (Calendar c : calendarProvider.getCalendars().getList()){
//            Data<Event> events = calendarProvider.getEvents(c.id);
//            Log.d(TAG, c.name + "  " + c.accountName + "  " + c.toString());
//        }
//        this.activeActionList = refreshEvents();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public ArrayList<TimedAction> refreshEvents() {
        ArrayList<TimedAction> activeActions = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALENDAR) != PackageManager.PERMISSION_GRANTED) {
            return null;
        }

//        String selectionQuery = "(dtend = ?)";// + Calendar.getInstance().get(Calendar.MILLISECOND) + TimeUnit.DAYS.toMillis(7);
//        Log.d("selection query: ", selectionQuery);
//        String[] selectionArguments = new String[]{Calendar.getInstance().get(Calendar.MILLISECOND) + TimeUnit.DAYS.toMillis(7)+""};

        Log.d(TAG, CalendarContract.Calendars.NAME);
        Log.d(TAG, CalendarContract.Calendars.CALENDAR_LOCATION);

        mCursor = getContentResolver().query(
                CalendarContract.Events.CONTENT_URI, COLS, null, null , null);  //selectionQuery, selectionArguments

        while(mCursor.moveToNext()) {
            String title = "";
            String description = "";
            Long start = 0l;
            Long end = 0l;
            try {
                title = mCursor.getString(0);
                start = mCursor.getLong(1);
                end = mCursor.getLong(2);
                description = mCursor.getString(3);
            }catch (CursorIndexOutOfBoundsException e){
                Log.e(TAG, "Calendar cursor Out of Bounds - no data?");
                mCursor.moveToNext();
                continue;
            }

            Log.d("Processing Events", title);

//            if (description.length() == 0) continue;
            //Check if this is a action event
            Pattern pattern = Pattern.compile("#!(.*)\\.");
            Matcher match = pattern.matcher(description);
            if(match.find()){
                String rawActionType = match.group(1);  //first group
                if (Arrays.asList(validActionTypes).contains(rawActionType)){
                    Log.d(TAG, "Found action!");
                    activeActions.add(new TimedAction(title, rawActionType, start, end));
                }
            }


        }
//        this.activeActionList = activeActions;
        return activeActions;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ActiveFragment(), "Active");
        adapter.addFragment(new CreateFragment(), "Create");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }


//        public void googleSignIn(View v){
//            startActivity(new Intent(this, SignInActivity.class));
//        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
