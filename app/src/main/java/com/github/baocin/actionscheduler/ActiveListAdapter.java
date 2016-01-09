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
//        TimedAction a = new TimedAction();
//        a.title = "Record Audio";
//        a.actionType = "A Name";
////      a.startTime = 389457984;
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

        TextView firstLine = (TextView) view.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) view.findViewById(R.id.secondLine);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        firstLine.setText(getItem(position).title);

        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy  hh:mm:ss");
        String startDate = sdf.format(getItem(position).startTime);
        String endDate = sdf.format(getItem(position).endTime);
        secondLine.setText(startDate + "  to  " + endDate);

        TimedAction current = actionList.get(position);
        if (current.actionType.equals("audio")) {
            icon.setImageResource(R.drawable.audio);
        }else if (current.actionType.equals("photo")){
            icon.setImageResource(R.drawable.camera);
        }else if (current.actionType.equals("video")){
            icon.setImageResource(R.drawable.video);
        }else {
            icon.setImageResource(R.drawable.tmp);
        }

        return view;
    }


}
