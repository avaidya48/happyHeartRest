package com.example.happyheart.object;

import java.sql.Timestamp;


public class Survey {

    private String parkingId;
    private Boolean safety;
    private Long rate;
    private String userName;
    private Timestamp surveyTime;

    public String getParkingId() {
        return parkingId;
    }

    public void setParkingId(String parkingId) {
        this.parkingId = parkingId;
    }

    public Boolean getSafety() {
        return safety;
    }

    public void setSafety(Boolean safety) {
        this.safety = safety;
    }

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getSurveyTime() {
        return surveyTime;
    }

    public void setSurveyTime(Timestamp surveyTime) {
        this.surveyTime = surveyTime;
    }

}
