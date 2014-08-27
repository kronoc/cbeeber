package net.conor.android.cbeeber.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import net.conor.android.cbeeber.R;
import net.conor.android.cbeeber.controller.ScheduleViewBaseAdapter;
import net.conor.android.cbeeber.model.Constants;
import net.conor.android.cbeeber.model.Schedule;

/**
 * Created by keegac01 on 02/07/2014.
 */
public class ScheduleListActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core);


        if (this.getIntent().hasExtra(Constants.SCHEDULE)) {
            Schedule schedule = (Schedule)this.getIntent().getSerializableExtra(Constants.SCHEDULE);
  /*
            TextView descriptionTextView = (TextView)this.findViewById(R.id.activity_main_textview_description);
            if (descriptionTextView == null){
                System.out.println("descriptionTextView is null");
            }
            descriptionTextView.setPadding(10, 10, 10, 10);

*/
            ScheduleViewBaseAdapter scheduleViewBaseAdapter = new ScheduleViewBaseAdapter(this,schedule);
            ListView listView = (ListView) this.findViewById(R.id.activity_main_listview);
            listView.setAdapter(scheduleViewBaseAdapter);



        }
        else{

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.main_menu_about:
                //loadSchedule("bbc1");
                break;
            case R.id.main_menu_reload:
//                loadSchedule("bbc2");
                break;
            case R.id.main_menu_favourites:
//                loadSchedule("bbc3");
                break;
            case R.id.main_menu_help:
//                 loadSchedule("bbc4");
                break;
        }
        return true;
    }



}