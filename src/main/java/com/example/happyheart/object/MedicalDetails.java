package com.example.happyheart.object;

import java.util.Date;

public class MedicalDetails
{
    private float diastolic_pressure;
    private float systolic_pressure;
    private float weight;
    private int heart_rate;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    private String date;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    /*
    {
    "diastolic_pressure" : 0,
    "systolic_pressure" : 0,
    "weight" : 70,
    "heart_rate" : 0,
    "username" : "test"
}
    * */
    
    private Date _date;

    public Date get_date() {
       return _date;
    }

    public void set_date(Date _date) {
       this._date = _date;
    }

    private String email;

    public void setDiastolic_pressure(float diastolic_pressure) {
        this.diastolic_pressure = diastolic_pressure;
    }

    public void setSystolic_pressure(float systolic_pressure) {
        this.systolic_pressure = systolic_pressure;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setHeart_rate(int heart_rate) {
        this.heart_rate = heart_rate;
    }

    public float getDiastolic_pressure() {
        return diastolic_pressure;
    }

    public float getSystolic_pressure() {
        return systolic_pressure;
    }

    public float getWeight() {
        return weight;
    }

    public int getHeart_rate() {
        return heart_rate;
    }
}
