package com.hongdroid.customcalendar.model;

import androidx.lifecycle.ViewModel;

import com.hongdroid.customcalendar.utils.DateUtil;

import java.util.Calendar;

public class DayInfo extends ViewModel {
    String year;
    String month;
    String day;

    public DayInfo() { }


    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setCalendar(Calendar calendar) {
        year = DateUtil.getDate(calendar.getTimeInMillis(), DateUtil.YEAR_FORMAT);
        month = DateUtil.getDate(calendar.getTimeInMillis(), DateUtil.MONTH_FORMAT);
        day = DateUtil.getDate(calendar.getTimeInMillis(), DateUtil.DAY_FORMAT);
    }
}
