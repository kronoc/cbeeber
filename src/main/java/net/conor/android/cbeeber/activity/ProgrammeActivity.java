package net.conor.android.cbeeber.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
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
        MenuInflater menuInflater = this.getMenuInflater();
        menuInflater.inflate(R.menu.programme, menu);
        MenuItem favItem = menu.findItem(R.id.programme_menu_favourites);
        if (isFavourite()) {
            favItem.setIcon(R.drawable.ic_menu_fav_checked);
            favItem.setTitle(R.string.programme_menu_favourite_remove);
        } else {
            favItem.setIcon(R.drawable.ic_menu_star);
            favItem.setTitle(R.string.programme_menu_favourite_add);
        }

        return true;
    }

    private boolean isFavourite() {
        return applicationDataSource.find(this.programme.tleo().getPid()) != null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.programme_menu_favourites:
                if (isFavourite()) {
                    applicationDataSource.delete(this.programme);
                    menuItem.setIcon(R.drawable.ic_menu_star);
                    menuItem.setTitle(R.string.programme_menu_favourite_add);
                    InfoBox.showInfo(ProgrammeActivity.this, "Programme removed from the favourite list");
                } else {
                    applicationDataSource.insert(this.programme);
                    menuItem.setIcon(R.drawable.ic_menu_fav_checked);
                    menuItem.setTitle(R.string.programme_menu_favourite_remove);
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
            case R.id.programme_menu_help:
                ProgrammeActivity.this.startActivity(new Intent(ProgrammeActivity.this, HelpActivity.class));
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
