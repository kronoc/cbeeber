package net.conor.android.cbeeber.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import net.conor.android.cbeeber.R;
import net.conor.android.cbeeber.controller.BitmapViewAsyncTask;
import net.conor.android.cbeeber.model.Constants;
import net.conor.android.cbeeber.model.Programme;
import net.conor.android.cbeeber.persistence.CBeeberDatasource;

/**
 * Created by keegac01 on 27/08/2014.
 */
public class ProgrammeActivity extends Activity {


    private Programme programme;
    private CBeeberDatasource applicationDataSource;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.programme, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.programme_menu_favourites:
                if (applicationDataSource.find(this.programme.tleo().getPid()) != null) {
                    applicationDataSource.delete(this.programme);
                    InfoBox.showInfo(ProgrammeActivity.this, "Programme removed from favourites list");
                } else {
                    applicationDataSource.insert(this.programme);
                    InfoBox.showInfo(ProgrammeActivity.this, "Programme added to the favourite list");
                }
                break;
            case R.id.programme_menu_iplayer:
                InfoBox.showInfo(ProgrammeActivity.this, "Taking you to the CBeebies iPlayer");
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(programme.getIplayerLink()));
                ProgrammeActivity.this.startActivity(intent);
                break;
            case R.id.programme_menu_schedule:
                this.finish();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.programme);
        this.applicationDataSource = new CBeeberDatasource(ProgrammeActivity.this);
        this.programme = (Programme) this.getIntent().getSerializableExtra(Constants.PROGRAMME_ITEM);

        TextView titleTextView = (TextView) this.findViewById(R.id.activity_programme_textview_title);
        titleTextView.setText(programme.tleo().getTitle());


        int restScreenWidth = this.getResources().getDisplayMetrics().widthPixels - 330;
        ImageView thumbnailImageView = (ImageView) this.findViewById(R.id.activity_programme_image);
        BitmapViewAsyncTask fillImageViewAsyncTask = new BitmapViewAsyncTask(this, programme.getImageUrl(), thumbnailImageView, restScreenWidth, restScreenWidth * 81 / 144);
        fillImageViewAsyncTask.execute();


        TextView subTitleTextView = (TextView) this.findViewById(R.id.activity_programme_textview_subtitle);
        subTitleTextView.setText(programme.getTitle());

        TextView descriptionTextView = (TextView) this.findViewById(R.id.activity_programme_textview_description);
        descriptionTextView.setText(programme.getShortSynopsis());


    }

}
