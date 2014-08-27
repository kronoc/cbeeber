package net.conor.android.cbeeber.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.programme);

        final Programme programme = (Programme)this.getIntent().getSerializableExtra(Constants.PROGRAMME_ITEM);

        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(90, 90);
        layoutParams.setMargins(15, 15, 15, 15);

        ImageButton favouriteImageButton = (ImageButton)this.findViewById(R.id.activity_programme_imagebutton_favourite);
        favouriteImageButton.setLayoutParams(layoutParams);
        favouriteImageButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        CBeeberDatasource applicationDataSource = new CBeeberDatasource(ProgrammeActivity.this);
                        applicationDataSource.insert(programme);
                        InfoBox.showInfo(ProgrammeActivity.this, "Programme added to the favourite list");
                    }
                }
        );

        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(90, 90);
        layoutParams2.setMargins(15, 150, 15, 15);

        ImageButton iplayerImageButton = (ImageButton)this.findViewById(R.id.activity_programme_imagebutton_iplayer);
        iplayerImageButton.setLayoutParams(layoutParams2);
        iplayerImageButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        InfoBox.showInfo(ProgrammeActivity.this, "Taking you to CBeebies iPlayer");
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(programme.getIplayerLink()));
                        ProgrammeActivity.this.startActivity(intent);
                    }
                }
        );


        TextView titleTextView = (TextView)this.findViewById(R.id.activity_programme_textview_title);
        titleTextView.setText(programme.tleo().getTitle());


        int restScreenWidth = this.getResources().getDisplayMetrics().widthPixels - 330;
        ImageView thumbnailImageView = (ImageView)this.findViewById(R.id.activity_programme_image);
        BitmapViewAsyncTask fillImageViewAsyncTask = new BitmapViewAsyncTask(this,programme.getImageUrl(), thumbnailImageView, restScreenWidth, restScreenWidth*81/144);
        fillImageViewAsyncTask.execute();


        TextView subTitleTextView = (TextView)this.findViewById(R.id.activity_programme_textview_subtitle);
        subTitleTextView.setText(programme.getTitle());

        TextView descriptionTextView = (TextView)this.findViewById(R.id.activity_programme_textview_description);
        descriptionTextView.setText(programme.getShortSynopsis());

        TextView guidTextView = (TextView)this.findViewById(R.id.activity_programme_textview_iplayer_link);
        guidTextView.setText(R.string.iplayer_cta);
        guidTextView.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(programme.getIplayerLink()));
                        ProgrammeActivity.this.startActivity(intent);
                    }
                }
        );

    }


}
