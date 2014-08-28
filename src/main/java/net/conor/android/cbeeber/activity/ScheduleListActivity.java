package net.conor.android.cbeeber.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
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


    private boolean favsOnly = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core);
        prepareList(favsOnly);
    }

    private void prepareList(boolean favsOnly) {
        if (this.getIntent().hasExtra(Constants.SCHEDULE)) {
            final Schedule schedule = (Schedule) this.getIntent().getSerializableExtra(Constants.SCHEDULE);
            ScheduleViewBaseAdapter scheduleViewBaseAdapter = new ScheduleViewBaseAdapter(this, schedule, favsOnly);
            ListView listView = (ListView) this.findViewById(R.id.activity_main_listview);
            Log.i("CBeeber", "Current Broadcast Index:"+schedule.currentBroadcastIndex());
            listView.setAdapter(scheduleViewBaseAdapter);
            if(schedule.currentBroadcastIndex()!=0) {
                listView.setSelection(schedule.currentBroadcastIndex());
            }
            listView.setOnItemClickListener(
                    new AdapterView.OnItemClickListener()
                    {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int positon, long id)
                        {
                            Intent intent = new Intent(ScheduleListActivity.this,ProgrammeActivity.class);
                            intent.putExtra(Constants.PROGRAMME_ITEM, schedule.getBroadcasts().get(positon).getProgramme());
                            ScheduleListActivity.this.startActivity(intent);
                        }
                    }
            );
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
            case R.id.main_menu_help:
                ScheduleListActivity.this.startActivity(new Intent(ScheduleListActivity.this,HelpActivity.class));
                break;
            case R.id.main_menu_favourites:
                if (menuItem.isChecked()){
                    menuItem.setIcon(R.drawable.ic_menu_star);
                    menuItem.setChecked(false);
                    favsOnly=false;
                }
                else{
                    menuItem.setChecked(true);
                    menuItem.setIcon(R.drawable.ic_menu_fav_checked);
                    this.favsOnly= true;
                }
                this.prepareList(favsOnly);

        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.onCreate(null);
    }


}