package net.conor.android.cbeeber.util;

import android.graphics.Bitmap;
import android.util.LruCache;

import java.io.Serializable;

/**
 * Created by keegac01 on 29/08/2014.
 */
public class ImageCache implements Serializable{

    private LruCache<String, Bitmap> imageMemCache;
    private static final int CACHE_SIZE = (((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8);

    public ImageCache() {
        imageMemCache = new InternalImageCache(CACHE_SIZE) {
            @Override
            protected int sizeOf(String url, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public Bitmap get(String key) {
        return imageMemCache.get(key);
    }

    public void put(String key, Bitmap image) {
        imageMemCache.put(key,image);
    }


    protected class InternalImageCache extends LruCache<String, Bitmap> implements Serializable{
        public InternalImageCache(int maxSize) {
            super(maxSize);
        }
    }
}
