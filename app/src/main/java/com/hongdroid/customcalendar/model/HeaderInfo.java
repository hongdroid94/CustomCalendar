package com.hongdroid.customcalendar.model;

import androidx.lifecycle.ViewModel;

import com.hongdroid.customcalendar.utils.DateUtil;

public class HeaderInfo extends ViewModel {
    private String header;
    private long mCurrentTime;

    public HeaderInfo() { }

    public String getHeader() {
        return header;
    }

    public void setHeader(long time) {
        this.header = DateUtil.getDate(time, DateUtil.CALENDAR_HEADER_FORMAT);
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
