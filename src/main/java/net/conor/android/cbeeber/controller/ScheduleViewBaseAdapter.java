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
    private final boolean favsOnly;
    private AnimationDrawable bugAnimation;

    public ScheduleViewBaseAdapter(final Context context, final Schedule schedule, boolean favsOnly) {
        this.context = context;
        this.schedule = schedule;
        this.datasource =  new CBeeberDatasource(context);
        this.favsOnly = favsOnly;
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

        boolean isFavourite = (datasource.find(this.schedule.getBroadcasts().get(position).getProgramme().tleo().getPid()) != null);

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

        TextView textViewBottom = (TextView) convertView.findViewById(R.id.layout_schedule_textview_programme_time);
        textViewBottom.setText(this.schedule.getBroadcasts().get(position).getPrettyTime());

        ImageView bugImage = (ImageView) convertView.findViewById(R.id.layout_schedule_now);

        Calendar now = Calendar.getInstance();
        Calendar broadcastStart = Calendar.getInstance();
        broadcastStart.setTime(this.schedule.getBroadcasts().get(position).getStart());
        Calendar broadcastEnd = Calendar.getInstance();
        broadcastEnd.setTime(this.schedule.getBroadcasts().get(position).getEnd());

        if(broadcastStart.before(now) && broadcastEnd.after(now)) {
            bugImage.setBackgroundResource(R.drawable.dancing_bug);
            bugAnimation = (AnimationDrawable) bugImage.getBackground();
            bugImage.setVisibility(View.VISIBLE);
            bugAnimation.start();
        }else{
            bugImage.setVisibility(View.GONE);
        }

        if(favsOnly && !isFavourite) {
            bugImage.setVisibility(View.GONE);
            bugImage.setPadding(0, -1000, 0, -1000);
            imageView.setVisibility(View.GONE);
            imageView.setPadding(0, -1000, 0, -1000);
            textViewTop.setVisibility(View.GONE);
            textViewTop.setPadding(0, -1000, 0, -1000);
            textViewBottom.setVisibility(View.GONE);
            textViewBottom.setPadding(0, -1000, 0, -1000);
            convertView.setVisibility(View.GONE);
            convertView.setPadding(0, -1000, 0, -1000);
            relativeLayout.setVisibility(View.GONE);
            relativeLayout.setPadding(0, -1000, 0, -1000);
        }

        return convertView;
    }

}
