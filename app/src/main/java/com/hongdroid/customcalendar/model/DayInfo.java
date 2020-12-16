package com.hongdroid.customcalendar.model;

import androidx.lifecycle.ViewModel;

import com.hongdroid.customcalendar.utils.DateUtil;

import java.util.Calendar;

public class DayInfo extends ViewModel {
    String day;

    public DayInfo() { }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setCalendar(Calendar calendar) {
        day = DateUtil.getDate(calendar.getTimeInMillis(), DateUtil.DAY_FORMAT);
    }
}
