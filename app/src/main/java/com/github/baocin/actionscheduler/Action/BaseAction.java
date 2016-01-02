package com.github.baocin.actionscheduler.Action;

import org.suid.SUID;

public class BaseAction {
    public String title;
    public String actionType;
    private long id;

    public BaseAction() {
        //simple unique id
        id = SUID.id().get();
    }

    public long getId() {
        return id;
    }

    public BaseAction(String title, String actionType) {
        this();
        this.title = title;
        this.actionType = actionType;
    }

    @Override
    public String toString() {
        return "Action{" +
                "title='" + title + '\'' +
                ", actionType='" + actionType + '\'' +
                ", id=" + id +
                '}';
    }
}
