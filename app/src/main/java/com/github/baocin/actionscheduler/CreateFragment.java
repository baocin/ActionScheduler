package com.github.baocin.actionscheduler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by aoi on 12/27/15.
 */
public class CreateFragment extends Fragment {
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
        return v;
    }
}
