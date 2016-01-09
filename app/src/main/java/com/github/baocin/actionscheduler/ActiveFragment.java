package com.github.baocin.actionscheduler;

import android.content.ContentUris;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.github.baocin.actionscheduler.Action.TimedAction;

import java.util.ArrayList;

/**
 * Created by aoi on 12/27/15.
 */
public class ActiveFragment extends Fragment {
    private static final String TAG = "ActiveFragment";
    ArrayList<TimedAction> timedActions;

    public ActiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.contextual_menu, menu);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Inflate initial view
        View v = inflater.inflate(R.layout.fragment_active, container, false);

        //Populate listview
        ListView activeActionList = (ListView) v.findViewById(R.id.activeActionList);
        timedActions = CalendarReceiver.refreshEvents(this.getContext());

        if (timedActions.size() == 0){
            v = inflater.inflate(R.layout.fragment_zero_active, container, false);
        }

        ActiveListAdapter activeListAdapter = new ActiveListAdapter(timedActions);
        activeActionList.setAdapter(activeListAdapter);
        activeActionList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        //Contextual Menu
        registerForContextMenu(activeActionList);

        return v;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
//        Log.d(TAG, "Timed Action: " + timedActions.get(info.position));
        TimedAction selectedAction = timedActions.get(info.position);

        switch (item.getItemId()) {
            case R.id.edit:
                Log.d(TAG, "you pressed the edit button");
                Intent calendarIntent = new Intent(this.getActivity(), ActionSettingsActivity.class);
                calendarIntent.putExtra("selected_action_id", selectedAction.getId());
                calendarIntent.putExtra("selected_action_start_time", selectedAction.startTime);
                calendarIntent.putExtra("selected_action_end_time", selectedAction.endTime);
                calendarIntent.putExtra("selected_action_type", selectedAction.actionType);
                calendarIntent.putExtra("selected_action_parameters", selectedAction.parameters);
                startActivity(calendarIntent);
                return true;
            case R.id.show:
                Log.d(TAG, "you pressed the show button");
                Intent calIntent = new Intent(Intent.ACTION_VIEW);

                calIntent.setData(ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, selectedAction.getId()));
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, selectedAction.startTime);
                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, selectedAction.endTime);

                startActivity(calIntent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
