package net.conor.android.cbeeber.util.fetcher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by keegac01 on 26/08/2014.
 */
public class ImageFetcher {

    public Bitmap fetchImage(final String imageURL, final Bitmap fallbackImage, final int width, final int height) {
        Bitmap image = fallbackImage;
        try {
            if (imageURL != null && imageURL.length() > 1) {
                URL url = new URL(imageURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(10 * 1000);
                httpURLConnection.setConnectTimeout(20 * 1000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() <= 400) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    if (inputStream != null) {
                        Bitmap fetchedBitmap = BitmapFactory.decodeStream(inputStream);
                        if (fetchedBitmap != null) {
                            image = Bitmap.createScaledBitmap(fetchedBitmap, width, height, false);
                        }
                    }
                }
            }
        } catch (Exception e) {
            Log.e("cbeeber", "Exception getting image imageURL", e);
            return fallbackImage;
        }

        return image;

    }
}
