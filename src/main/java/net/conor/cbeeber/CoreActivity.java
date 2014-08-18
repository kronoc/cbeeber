package net.conor.cbeeber;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.cbeeber.R;

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
            ListViewBaseAdapter listViewBaseAdapter = new ListViewBaseAdapter(this.getIntent().getSerializableExtra("TV_SCHEDULE"));
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


    private void RetrieveScheduleAsyncTask(String channel) {
        RetrieveScheduleAsyncTask retrieveAsync = new RetrieveScheduleAsyncTask(this);
        retrieveAsync.execute("http://www.bbc.co.uk/cbeebies/programmes/schedules.xml");
    }

    private String buildImageURL(String id){
        if (id != null && ! "".equals(id)){
            return String.format(id);
        }
        return "";

    }
}