package com.example.cartracker.model;

import java.io.Serializable;
import java.util.Date;

public class MandatoryInsurance implements Serializable {
    String name;
    Date startDate;
    Date endDate;

    public MandatoryInsurance(String name, Date startDate, Date endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
