package com.github.baocin.actionscheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.baocin.actionscheduler.Action.TimedAction;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class ActiveListAdapter extends BaseAdapter {
    private List<TimedAction> actionList = new ArrayList<>();

    public ActiveListAdapter(){
        actionList = MainActivity.getActiveActionList();
//        TimedAction a = new TimedAction();
//        a.title = "Record Audio";
//        a.actionType = "A Name";
////      a.startDate = 389457984;
//        actionList.add(a);
    }
    public ActiveListAdapter(List<TimedAction> actionList){
        this.actionList = actionList;
    }

    @Override
    public int getCount() {
        return actionList.size();
    }

    @Override
    public TimedAction getItem(int position) {
        return actionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            view = inflater.inflate(R.layout.fragment_active_item, parent, false);
        }
//        LayoutInflater inflater = (LayoutInflater) .this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View rowView = inflater.inflate(R.layout.fragment_active_item, parent, false);

        TextView firstLine = (TextView) view.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) view.findViewById(R.id.secondLine);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        firstLine.setText(getItem(position).title);

//        Log.d("getView", getItem(position).toString());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy");
        String startDate = sdf.format(getItem(position).startDate);
        secondLine.setText(startDate);


        icon.setImageResource(R.drawable.tmp);

        return view;
    }

    public void refreshList(){
        actionList = MainActivity.getActiveActionList();
    }
}
