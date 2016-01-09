package com.github.baocin.actionscheduler.Action;

/**
 * Created by aoi on 12/31/15.
 */
public class TimedAction extends BaseAction {
    public long startTime;
    public long endTime;
    public String[] parameters;

    public TimedAction(){
        super();
        parameters = null;
        startTime = 0;
        endTime = 0;
    }

    public TimedAction(String title, String actionType, long startTime, long endTime) {
        super();
        this.title = title;
        this.actionType = actionType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimedAction(String title, String actionType, long startTime, long endTime, long id) {
        super(title, actionType, id);
        this.title = title;
        this.actionType = actionType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.parameters = parameters;
    }

    public TimedAction(String title, String actionType, String[] parameters, long startTime, long endTime, long id) {
        super(title, actionType, id);
        this.title = title;
        this.actionType = actionType;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimedAction(long startTime, long endTime) {
        this();
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
