package com.github.baocin.actionscheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by aoi on 12/27/15.
 */
public class ActiveListAdapter extends BaseAdapter {
    private List<Action> actionList;

    public ActiveListAdapter(){

    }
    public ActiveListAdapter(List<Action> actionList){
        this.actionList = actionList;
    }
    @Override
    public int getCount() {
        return actionList.size();
    }

    @Override
    public Action getItem(int position) {
        return actionList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.fragment_active_item, parent, false);
        TextView firstLine = (TextView) rowView.findViewById(R.id.firstLine);
        TextView secondLine = (TextView) rowView.findViewById(R.id.secondLine);
        ImageView icon = (ImageView) rowView.findViewById(R.id.icon);
        firstLine.setText(getItem(position).title);
        secondLine.setText(getItem(position).startDate.toString());
        icon.setImageResource(R.drawable.tmp);
        return rowView;
    }
}
