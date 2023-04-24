package com.example.happyheart.object;

import java.util.Date;

public class Appointment {

    private String date;
    private String details;

    private String email;

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    private Date _date;

    public Date get_date() {
       return _date;
    }

    public void set_date(Date _date) {
       this._date = _date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
