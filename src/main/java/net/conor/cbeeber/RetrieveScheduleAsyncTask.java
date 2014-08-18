package net.conor.cbeeber;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import net.conor.cbeeber.parser.Schedule;
import net.conor.cbeeber.parser.ScheduleBuilder;

/**
* Created by keegac01 on 02/07/2014.
*/
public class RetrieveScheduleAsyncTask extends AsyncTask<String, Void, Schedule>
{


    private Activity callingActivity;

    public RetrieveScheduleAsyncTask(Activity callingActivity) {
        this.callingActivity = callingActivity;
    }

    @Override
    protected Schedule doInBackground(String... params)
    {
        ScheduleBuilder scheduleBuilder = new ScheduleBuilder();
//        try
//        {
//            Thread.sleep(1*1000);
//        }
//        catch(Exception exception)
//        {
//            Log.e("cbeeber", "Exception", exception);
//        }
        return scheduleBuilder.build(params[0]);
    }

    @Override
    protected void onPostExecute(Schedule schedule)
    {
        Intent intent = new Intent(callingActivity, CoreActivity.class);
        intent.putExtra("TV_SCHEDULE", schedule);
        callingActivity.startActivity(intent);
        callingActivity.finish();
    }
}
