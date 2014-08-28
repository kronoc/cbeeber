package net.conor.android.cbeeber.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import net.conor.android.cbeeber.activity.ScheduleListActivity;
import net.conor.android.cbeeber.model.Constants;
import net.conor.android.cbeeber.model.Schedule;
import net.conor.android.cbeeber.util.parser.ScheduleProvider;

/**
 * Created by keegac01 on 02/07/2014.
 */
public class RetrieveScheduleAsyncTask extends AsyncTask<String, Void, Schedule> {

    private Activity callingActivity;
    public RetrieveScheduleAsyncTask(Activity callingActivity) {
        this.callingActivity = callingActivity;
    }

    @Override
    protected Schedule doInBackground(String... params) {
        ScheduleProvider scheduleProvider = new ScheduleProvider();
        try {
            Thread.sleep(3 * 1000);
        } catch (Exception exception) {
            Log.e("cbeeber", "Exception", exception);
        }
        return scheduleProvider.getSchedule();
    }

    @Override
    protected void onPostExecute(Schedule schedule) {
        Intent intent = new Intent(callingActivity, ScheduleListActivity.class);
        intent.putExtra(Constants.SCHEDULE, schedule);
        callingActivity.startActivity(intent);
        callingActivity.finish();
    }
}
