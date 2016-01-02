package com.github.baocin.actionscheduler.Action;

/**
 * Created by aoi on 12/31/15.
 */
public class TriggerAction extends BaseAction{
    public String triggerEvent;

    public TriggerAction(){
        super();
        triggerEvent = "none";
    }
    public TriggerAction(String trigger) {
        super();
        this.triggerEvent = trigger;
    }

}
