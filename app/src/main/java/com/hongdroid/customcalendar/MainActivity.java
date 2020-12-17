package com.hongdroid.customcalendar;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hongdroid.customcalendar.adapter.CalendarAdapter;
import com.hongdroid.customcalendar.utils.Keys;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    public int mCenterPos;
    private long mCurrentTime;
    public ArrayList<Object> mCalendarList = new ArrayList<>();

    private RecyclerView mRv_calendar;
    private CalendarAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRv_calendar = findViewById(R.id.rv_calendar);
        setCalendarList();
        setRecycler();

    }

    private void setCalendarList() {
        GregorianCalendar cal = new GregorianCalendar();

        for (int i = -300; i < 300; i++) {
            try {
                GregorianCalendar calendar = new GregorianCalendar(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + i, 1, 0, 0, 0);
                if( i == 0 ) {
                    mCenterPos = mCalendarList.size();
                }

                // create title
                mCalendarList.add(calendar.getTimeInMillis());

                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
                int max = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

                // create empty column
                for (int j = 0; j < dayOfWeek; j++) {
                    mCalendarList.add(Keys.EMPTY);
                }

                for (int j = 0; j <= max; j++) {
                    mCalendarList.add(new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), j));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void setRecycler() {
        if(mCalendarList == null) {
            Log.w("Warning", "No Query, not initializing RecyclerView");
        }

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(7, StaggeredGridLayoutManager.VERTICAL);
        mAdapter = new CalendarAdapter(this, mCalendarList);
        mRv_calendar.setLayoutManager(layoutManager);
        mRv_calendar.setAdapter(mAdapter);

        if(mCenterPos >= 0) {
            mRv_calendar.scrollToPosition(mCenterPos);
        }
    }


}