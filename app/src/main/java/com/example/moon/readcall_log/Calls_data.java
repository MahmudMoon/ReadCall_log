package com.example.moon.readcall_log;

import java.util.Date;

class Calls_data {
    String duration,number,type;
    Date date;

    public Calls_data(Date date, String duration, String number, String type) {
        this.date = date;
        this.duration = duration;
        this.number = number;
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
