package net.conor.android.cbeeber.controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import net.conor.android.cbeeber.R;
import net.conor.android.cbeeber.util.ImageCache;
import net.conor.android.cbeeber.util.fetcher.ImageFetcher;

public class BitmapViewAsyncTask extends AsyncTask<Void, Void, Void> {
    private final ImageCache imageCache;
    private Context context;
    private String imageURL;
    private ImageView imageView;
    private int width;
    private int height;
    private Bitmap bitmap;
    private ImageFetcher imageFetcher;

    public BitmapViewAsyncTask(Context context, ImageCache imageCache, String imageURL, ImageView imageView, int width, int height) {
        this.context = context;
        this.imageCache = imageCache;
        this.imageURL = imageURL;
        this.imageView = imageView;
        this.width = width;
        this.height = height;
        this.imageFetcher = new ImageFetcher();

    }

    public BitmapViewAsyncTask(Context context, String imageURL, ImageView imageView, int width, int height) {
        this.context = context;
        this.imageURL = imageURL;
        this.imageView = imageView;
        this.width = width;
        this.height = height;
        this.imageFetcher = new ImageFetcher();
        this.imageCache = new ImageCache();

    }

    @Override
    protected Void doInBackground(Void... params) {
        Bitmap fallbackBitmap = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.cbeebies_channel);
        if (imageCache.get(this.imageURL)!=null){
            this.bitmap = imageCache.get(this.imageURL);
        }else {
            this.bitmap = imageFetcher.fetchImage(this.imageCache,this.imageURL, fallbackBitmap, this.width, this.height);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        this.imageView.setImageBitmap(this.bitmap);
    }
}
