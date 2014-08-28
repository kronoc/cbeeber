package net.conor.android.cbeeber.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import net.conor.android.cbeeber.R;
import net.conor.android.cbeeber.view.HelpFragment;

/**
 * Created by keegac01 on 28/08/2014.
 */
public class HelpActivity extends Activity {

    private HelpFragment helpFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.helpFragment = new HelpFragment();

        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.setContentView(relativeLayout);

        this.getFragmentManager()
                .beginTransaction()
               // .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.activity_help, this.helpFragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.help, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.help_menu_back:
                this.finish();
                break;
        }
        return true;
    }

}