package net.conor.android.cbeeber.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import net.conor.android.cbeeber.R;
import net.conor.android.cbeeber.controller.ListViewBaseAdapter;
import net.conor.android.cbeeber.model.Constants;
import net.conor.android.cbeeber.model.Schedule;

/**
 * Created by keegac01 on 02/07/2014.
 */
public class CoreActivity extends Activity {


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
            ListViewBaseAdapter listViewBaseAdapter = new ListViewBaseAdapter(this,schedule);
            ListView listView = (ListView) this.findViewById(R.id.activity_main_listview);
            listView.setAdapter(listViewBaseAdapter);



        }
        else{

        }

    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        this.getMenuInflater().inflate(R.menu.core, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem menuItem) {
//        switch (menuItem.getItemId()) {
//            case R.id.menu_item_bbc1:
//                loadSchedule("bbc1");
//                break;
//            case R.id.menu_item_bbc2:
//                loadSchedule("bbc2");
//                break;
//            case R.id.menu_item_bbc3:
//                loadSchedule("bbc3");
//                break;
//            case R.id.menu_item_bbc4:
//                 loadSchedule("bbc4");
//                break;
//        }
//        return true;
//    }



}