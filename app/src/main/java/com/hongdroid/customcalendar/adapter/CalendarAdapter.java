package com.hongdroid.customcalendar.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.hongdroid.customcalendar.R;
import com.hongdroid.customcalendar.model.DayInfo;
import com.hongdroid.customcalendar.model.EmptyInfo;
import com.hongdroid.customcalendar.model.HeaderInfo;

import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter {
    private final int VIEW_TYPE_HEADER = 0;
    private final int VIEW_TYPE_EMPTY = 1;
    private final int VIEW_TYPE_DAY = 2;

    private List<Object> mCalendarList;

    public CalendarAdapter(List<Object> mCalendarList) {
        this.mCalendarList = mCalendarList;
    }

    public void setCalendarList(List<Object> _calendarList) {
        this.mCalendarList = _calendarList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View holder = null;
        if (viewType == VIEW_TYPE_HEADER) {
            // 날짜 타입

            holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calendar_header, parent, false);
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.getRootView().getLayoutParams();
            params.setFullSpan(true); // span을 하나로 통합
            holder.getRootView().setLayoutParams(params);
            return new HeaderViewHolder(holder);
        } else if (viewType == VIEW_TYPE_EMPTY) {
            // 비어있는 일자 타입
            holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_empty, parent, false);
            return new EmptyViewHolder(holder);
        } else {
            holder = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day, parent, false);
            return new DayViewHolder(holder);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        if (viewType == VIEW_TYPE_HEADER) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            Object item = mCalendarList.get(position);
            HeaderInfo model = new HeaderInfo();
            if (item instanceof Long) {
                model.setHeader((Long) item);
            }
            headerViewHolder.bind(model);

        } else if (viewType == VIEW_TYPE_EMPTY) {
            EmptyViewHolder emptyViewHolder = (EmptyViewHolder) holder;
            EmptyInfo model = new EmptyInfo();
            emptyViewHolder.bind(model);

        } else if (viewType == VIEW_TYPE_DAY) {
            DayViewHolder dayViewHolder = (DayViewHolder) holder;
            Object item = mCalendarList.get(position);
            DayInfo model = new DayInfo();
            if (item instanceof Calendar) {
                model.setCalendar((Calendar) item);
            }
            dayViewHolder.bind(model);
            dayViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("TEST", model.getDay());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mCalendarList.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mCalendarList.get(position);
        if (item instanceof Long) {
            return VIEW_TYPE_HEADER; // 날짜 타입
        } else if (item instanceof String) {
            return VIEW_TYPE_EMPTY; // 비어있는 일자 타입
        } else {
            return VIEW_TYPE_DAY; // 일자 타입
        }
    }


    // ============================ View Holder Area ============================================ //

    //날짜 타입 ViewHolder
    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView itemHeaderTitle;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            itemHeaderTitle = itemView.findViewById(R.id.item_header_title);
        }

        public void bind(ViewModel model) {
            // 일자 값 가져오기
            String header = ((HeaderInfo) model).getHeader();
            // header에 표시하기, ex : 2018년 8월
            itemHeaderTitle.setText(header);
        }
    }


    // 비어있는 요일 타입 ViewHolder
    private class EmptyViewHolder extends RecyclerView.ViewHolder {


        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(ViewModel model) { }
    }

    // 요일  타입 ViewHolder
    private class DayViewHolder extends RecyclerView.ViewHolder {

        TextView itemDay;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);
            itemDay = itemView.findViewById(R.id.item_day);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    int curPos = getAdapterPosition();
//                    DayInfo item = (DayInfo) mCalendarList.get(curPos);
//                    Log.e("DAY INFO ITEM", item.getDay());
//                }
//            });

        }

        public void bind(ViewModel model) {
            // 일자 값 가져오기
            String day = ((DayInfo) model).getDay();
            // 일자 값 View에 보이게하기
            itemDay.setText(day);
        }
    }
}
