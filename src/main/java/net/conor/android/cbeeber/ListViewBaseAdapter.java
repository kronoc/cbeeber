package net.conor.android.cbeeber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import net.conor.android.cbeeber.model.Schedule;

public class ListViewBaseAdapter extends BaseAdapter
{
    private final Schedule schedule;
    private Context context;
	
	public ListViewBaseAdapter(Context context,Schedule schedule)
	{
		this.context = context;
		this.schedule = schedule;
	}
	
	@Override
	public int getCount()
	{
		return schedule.size();
	}

	@Override
	public Object getItem(int position)
	{
		return this.schedule.getBroadcasts().get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return (long)position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listview, null);
		
		RelativeLayout relativeLayout = (RelativeLayout)convertView.findViewById(R.id.layout_listview_relativelayout);
		if(this.getCount()<=1)
		{
			relativeLayout.setBackgroundResource(R.drawable.shape_listview_element_onlyone);
		}
		else
		{
			if(position==0)
			{
				relativeLayout.setBackgroundResource(R.drawable.shape_listview_element_first);
			}
			else if(position==this.getCount()-1)
			{
				relativeLayout.setBackgroundResource(R.drawable.shape_listview_element_last);
			}
			else
			{
				relativeLayout.setBackgroundResource(R.drawable.shape_listview_element);
			}
		}
		
		ImageView imageView = (ImageView)convertView.findViewById(R.id.layout_listview_imageview);
		imageView.setLayoutParams(new RelativeLayout.LayoutParams(240, 135));
		//FillImageViewAsyncTask fillImageViewAsyncTask = new FillImageViewAsyncTask(this.context, this.arrayList.get(position).getThumbnail(), imageView, 240, 135);
		//fillImageViewAsyncTask.execute();
		
		TextView textViewTop = (TextView)convertView.findViewById(R.id.layout_listview_textview_top);
		//textViewTop.setText(this.arrayList.get(position).getTitle());
		
		TextView textViewBottom = (TextView)convertView.findViewById(R.id.layout_listview_textview_bottom);
		//textViewBottom.setText(this.arrayList.get(position).getPubDate());
		
		return convertView;
	}
}
