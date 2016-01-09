package com.github.baocin.actionscheduler;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by aoi on 12/27/15.
 */
public class CreateFragment extends Fragment {
    private static final String TAG = "CreateFragment";

    public CreateFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_create, container, false);
        ListView createActionList = (ListView) v.findViewById(R.id.createActionList);
        CreateListAdapter createListAdapter = new CreateListAdapter();
        createActionList.setAdapter(createListAdapter);

        //
        createActionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View view, int position, long id) {
                Intent calIntent = new Intent(Intent.ACTION_INSERT);
                calIntent.setType("vnd.android.cursor.item/event");
                Log.d(TAG, list.toString() + "  ");
//                calIntent.putExtra(CalendarContract.Events.CALENDAR_ID, );        //Current calendar id
                calIntent.putExtra(CalendarContract.Events.DESCRIPTION, "#!" + CalendarReceiver.validActionTypes[position] + ".");
                startActivity(calIntent);
            }

        });
        return v;
    }
}
