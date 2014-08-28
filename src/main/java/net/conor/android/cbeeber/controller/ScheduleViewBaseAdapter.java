package net.conor.android.cbeeber.controller;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import net.conor.android.cbeeber.R;
import net.conor.android.cbeeber.model.Schedule;
import net.conor.android.cbeeber.persistence.CBeeberDatasource;

public class ScheduleViewBaseAdapter extends BaseAdapter {
    private final Schedule schedule;
    private final Context context;
    private final CBeeberDatasource datasource;

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

        /*

        ImageButton favouriteImageButton = (ImageButton)this.findViewById(R.id.activity_programme_imagebutton_favourite);
        favouriteImageButton.setLayoutParams(layoutParams);
        favouriteImageButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        CBeeberDatasource applicationDataSource = new CBeeberDatasource(ProgrammeActivity.this);
                        applicationDataSource.insert(programme);
                        InfoBox.showInfo(ProgrammeActivity.this, "Programme added to the favourite list");
                    }
                }
        );
*/
        TextView textViewTop = (TextView) convertView.findViewById(R.id.layout_schedule_textview_programme_title);
        textViewTop.setText(this.schedule.getBroadcasts().get(position).getTitle());

        boolean isFavourite = (datasource.find(this.schedule.getBroadcasts().get(position).getProgramme().tleo().getPid()) != null);

        if(isFavourite) {
            ImageView favView = (ImageView) convertView.findViewById(R.id.layout_schedule_favourite);
            favView.setPadding(50,5,0,0);
            favView.setLayoutParams(new RelativeLayout.LayoutParams(150, 150));
            favView.setImageBitmap(BitmapFactory.decodeResource(this.context.getResources(), R.drawable.ic_menu_star));
        }

        TextView textViewBottom = (TextView) convertView.findViewById(R.id.layout_schedule_textview_programme_time);
        textViewBottom.setText(this.schedule.getBroadcasts().get(position).getPrettyTime());


        return convertView;
    }
}
