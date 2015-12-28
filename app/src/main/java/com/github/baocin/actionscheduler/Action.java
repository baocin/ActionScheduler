package com.github.baocin.actionscheduler;

import org.suid.SUID;
import java.util.Calendar;
import java.util.Date;

public class Action {
    public String title;
    public String name;
    public Date startDate;
    public Date endDate;
    private long id;

    public Action() {
        //simple unique id
        id = SUID.id().get();
    }

    public long getId() {
        return id;
    }

    public Action(String title, String name, Date startDate, Date endDate) {
        this();
        this.title = title;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
