package net.conor.android.cbeeber;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import net.conor.android.cbeeber.model.Schedule;

/**
 * Created by keegac01 on 02/07/2014.
 */
public class CoreActivity extends Activity {

    private final static String IMAGE_URL_TEMPLATE = "http://ichef.bbci.co.uk/images/ic/192x108/%s.jpg";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.core);


        if (this.getIntent().hasExtra("TV_SCHEDULE")) {

            Schedule schedule = (Schedule)this.getIntent().getSerializableExtra("TV_SCHEDULE");
            ListViewBaseAdapter listViewBaseAdapter = new ListViewBaseAdapter(this,schedule);
            ListView listView = (ListView) this.findViewById(R.id.activity_main_listview);
            listView.setAdapter(listViewBaseAdapter);
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