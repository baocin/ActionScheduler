package com.github.baocin.actionscheduler.Action;

import org.suid.SUID;

public class BaseAction {
    public String title;
    public String actionType;
    private long id;

    public BaseAction() {
        id = 0;
        title = "";
        actionType = "";
    }

    public BaseAction(String title, String actionType) {
        this();
        this.title = title;
        this.actionType = actionType;
    }

    public long getId() {
        return id;
    }

    public BaseAction(String title, String actionType, long id) {
        this();
        this.title = title;
        this.actionType = actionType;
        this.id = id;
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
