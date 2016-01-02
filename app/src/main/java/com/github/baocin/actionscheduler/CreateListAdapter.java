package com.github.baocin.actionscheduler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.baocin.actionscheduler.Action.BaseAction;

import java.util.ArrayList;
import java.util.List;


public class CreateListAdapter extends BaseAdapter {
    private List<BaseAction> actionList = new ArrayList<BaseAction>();

    public CreateListAdapter(){
        actionList.add(new BaseAction("Record Audio", "record_audio"));
        actionList.add(new BaseAction("Record Video", "record_video"));
        actionList.add(new BaseAction("Take Photo", "take_photo"));
//        actionList.add(new BaseAction("Record Audio", "record_audio"));
    }

    public CreateListAdapter(List<BaseAction> actionList){
        this.actionList = actionList;
    }

    @Override
    public int getCount() {
        return actionList.size();
    }

    @Override
    public BaseAction getItem(int position) {
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
            view = inflater.inflate(R.layout.fragment_create_item, parent, false);
        }
        TextView firstLine = (TextView) view.findViewById(R.id.firstLine);
        ImageView icon = (ImageView) view.findViewById(R.id.icon);
        firstLine.setText(getItem(position).title);



        icon.setImageResource(R.drawable.tmp);

        return view;
    }
}
