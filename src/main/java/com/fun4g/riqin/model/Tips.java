package com.fun4g.riqin.model;

import java.util.Date;

public class Tips {
    private Integer id;

    public Iuser getIuser() {
        return iuser;
    }

    public void setIuser(Iuser iuser) {
        this.iuser = iuser;
    }

    private Iuser iuser;
    private String userId;

    private String icomment;

    private Date alertTime;

    private Boolean alerted;

    private Date insertTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getIcomment() {
        return icomment;
    }

    public void setIcomment(String icomment) {
        this.icomment = icomment == null ? null : icomment.trim();
    }

    public Date getAlertTime() {
        return alertTime;
    }

    public void setAlertTime(Date alertTime) {
        this.alertTime = alertTime;
    }

    public Boolean getAlerted() {
        return alerted;
    }

    public void setAlerted(Boolean alerted) {
        this.alerted = alerted;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}