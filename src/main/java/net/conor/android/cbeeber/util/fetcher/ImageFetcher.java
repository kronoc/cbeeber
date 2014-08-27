package net.conor.android.cbeeber.util.fetcher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by keegac01 on 26/08/2014.
 */
public class ImageFetcher {

    public Bitmap fetchImage(String imageURL, Bitmap fallbackImage, int width, int height) {
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
                        image = Bitmap.createScaledBitmap(BitmapFactory.decodeStream(inputStream), width, height, false);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return image;

    }
}
