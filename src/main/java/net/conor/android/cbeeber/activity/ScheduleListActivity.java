package net.conor.android.cbeeber.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import net.conor.android.cbeeber.R;
import net.conor.android.cbeeber.controller.RetrieveScheduleAsyncTask;
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
            Schedule schedule = (Schedule) this.getIntent().getSerializableExtra(Constants.SCHEDULE);
            ScheduleViewBaseAdapter scheduleViewBaseAdapter = new ScheduleViewBaseAdapter(this, schedule);
            ListView listView = (ListView) this.findViewById(R.id.activity_main_listview);
            listView.setAdapter(scheduleViewBaseAdapter);
        } else {
            new AlertDialog
                    .Builder(this, AlertDialog.THEME_HOLO_LIGHT)
                    .setTitle("Schedule Failure")
                    .setMessage("Sorry, we could not retrieve latest tv schedule.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    ScheduleListActivity.this.finish();
                                }
                            }
                    ).show();
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
                InfoBox.showInfo(this, "CBeeber - The CBeebies Schedule App - © 2014 Conor Keegan");
                break;
            case R.id.main_menu_reload:
                InfoBox.showInfo(this, "Reloading today's schedule");
                RetrieveScheduleAsyncTask retrieveAsync = new RetrieveScheduleAsyncTask(this);
                retrieveAsync.execute();
                break;
            case R.id.main_menu_favourites:
                InfoBox.showInfo(this, "favourite programmes");
                break;
            case R.id.main_menu_help:
                InfoBox.showInfo(this, "Help");
                break;
        }
        return true;
    }


}