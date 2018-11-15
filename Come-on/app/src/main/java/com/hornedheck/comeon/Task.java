package com.hornedheck.comeon;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class Task {
    private String name;
    private String info;
    private Date date;
    private boolean done;
    private String id;
    final static DateFormat dateFormat = DateFormat.getDateTimeInstance();
    public Task(String name, String info, String done, String date, String id){
        this.name = name;
        this.info = info;
        this.done = Boolean.parseBoolean(done);
        this.id = id;
        try {
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public Task(String name, String info, boolean done, Date date){
        this.name = name;
        this.info = info;
        this.done = done;
        this.date = date;
        this.id = generateId();

    }
    private String generateId(){
        long currentTime = Calendar.getInstance().getTimeInMillis();
        return Long.toHexString(currentTime);
    }
    public String getName() {
        return name;
    }

    public Date getDate() {
        return this.date;
    }
    public String getDateString() {
        return dateFormat.format(date);
    }
    public String getInfo() {
        return info;
    }

    public String getId() {
        return id;
    }

    public String getString() {
        return name + Tasks.DELIMETER + info + Tasks.DELIMETER + Boolean.toString(done) + Tasks.DELIMETER + getDateString() + Tasks.DELIMETER + getId();
    }

    public boolean isDone() {
        return done;
    }
    public String getDateForUser(){
        return dateFormat.format(date);
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
