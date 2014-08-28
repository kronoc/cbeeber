package net.conor.android.cbeeber.controller;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import net.conor.android.cbeeber.R;
import net.conor.android.cbeeber.model.Schedule;
import net.conor.android.cbeeber.persistence.CBeeberDatasource;

import java.util.Calendar;

public class ScheduleViewBaseAdapter extends BaseAdapter {
    private final Schedule schedule;
    private final Context context;
    private final CBeeberDatasource datasource;
    private AnimationDrawable bugAnimation;

//    public int getCurrentPosition() {
//        return currentPosition;
//    }
//
//    private int currentPosition = 0;

    public ScheduleViewBaseAdapter(final Context context, final Schedule schedule) {
        this.context = context;
        this.schedule = schedule;
        this.datasource =  new CBeeberDatasource(context);

    }

    @Override
    public int getCount() {
        return schedule.size();
    }

    @Override
    public Object getItem(int position) {
        return this.schedule.getBroadcasts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listview, null);

        RelativeLayout relativeLayout = (RelativeLayout) convertView.findViewById(R.id.layout_listview_relativelayout);

        if (position == 0) {
            relativeLayout.setBackgroundResource(R.drawable.shape_listview_element_first);
        } else if (position == this.getCount() - 1) {
            relativeLayout.setBackgroundResource(R.drawable.shape_listview_element_last);
        } else {
            relativeLayout.setBackgroundResource(R.drawable.shape_listview_element);
        }


        ImageView imageView = (ImageView) convertView.findViewById(R.id.layout_schedule_imageview);
        imageView.setLayoutParams(new RelativeLayout.LayoutParams(340, 195));

        BitmapViewAsyncTask bitmapViewAsyncTask = new BitmapViewAsyncTask(this.context, this.schedule.getBroadcasts().get(position).getImageUrl(), imageView, 340, 195);
        bitmapViewAsyncTask.execute();

        TextView textViewTop = (TextView) convertView.findViewById(R.id.layout_schedule_textview_programme_title);
        textViewTop.setText(this.schedule.getBroadcasts().get(position).getTitle());

        ImageView bugImage = (ImageView) convertView.findViewById(R.id.layout_schedule_now);
        bugImage.setBackgroundResource(R.drawable.dancing_bug);
        bugAnimation = (AnimationDrawable) bugImage.getBackground();


        Calendar now = Calendar.getInstance();
        Calendar broadcastStart = Calendar.getInstance();
        broadcastStart.setTime(this.schedule.getBroadcasts().get(position).getStart());
        Calendar broadcastEnd = Calendar.getInstance();
        broadcastEnd.setTime(this.schedule.getBroadcasts().get(position).getEnd());

        //boolean isFavourite = (datasource.find(this.schedule.getBroadcasts().get(position).getProgramme().tleo().getPid()) != null);

        if(broadcastStart.before(now) && broadcastEnd.after(now)) {
           // this.currentPosition = position;
            bugImage.setVisibility(View.VISIBLE);
            bugAnimation.start();
        }else{
            bugImage.setVisibility(View.INVISIBLE);
        }

        TextView textViewBottom = (TextView) convertView.findViewById(R.id.layout_schedule_textview_programme_time);
        textViewBottom.setText(this.schedule.getBroadcasts().get(position).getPrettyTime());
        return convertView;
    }

}
