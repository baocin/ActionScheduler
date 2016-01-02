package com.github.baocin.actionscheduler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by aoi on 12/27/15.
 */
public class ActiveFragment extends Fragment {
    public ActiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

//        Class clicked = CLASSES[position];
//        startActivity(new Intent(this, clicked));

    public void onItemClick (AdapterView < ? > adapter, View view,int position, long arg) {
        View v = getActivity().findViewById(R.id.firstLine);
        Toast.makeText(this.getContext(), "selected Item Name is " + v.toString(), Toast.LENGTH_LONG).show();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_active, container, false);
        ListView activeActionList = (ListView) v.findViewById(R.id.activeActionList);
        ActiveListAdapter activeListAdapter = new ActiveListAdapter();
        activeActionList.setAdapter(activeListAdapter);
//        activeActionList.setOnItemClickListener(this.onItemClick);
        return v;
    }



//    public void onClick(View v) {
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestEmail()
//            .build();
//            startActivity(new Intent(this, SignInActivity.class));
//    }



}
