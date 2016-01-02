package com.github.baocin.actionscheduler.Action;

/**
 * Created by aoi on 12/31/15.
 */
public class TimedAction extends BaseAction {
    public long startDate;
    public long endDate;

    public TimedAction(){
        super();
    }

    public TimedAction(String title, String actionType, long startDate, long endDate) {
        this();
        this.title = title;
        this.actionType = actionType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public TimedAction(long startDate, long endDate) {
        this();
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
