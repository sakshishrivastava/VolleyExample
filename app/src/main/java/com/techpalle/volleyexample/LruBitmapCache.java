package com.techpalle.volleyexample;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by sakshi1 on 22-08-2017.
 */

public class LruBitmapCache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache {
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public  static int getDefaultLruCacheSize(){
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory()/1024);
        final int cacheSize = maxMemory/8;
        return cacheSize;
    }
    public LruBitmapCache(){
        this(getDefaultLruCacheSize());

    }
    public LruBitmapCache(int sizeInKiloBytes) {
           super(sizeInKiloBytes);
    }
    protected int sizeof(String key,Bitmap value){
        return value.getRowBytes()* value.getHeight() / 1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
       put(url,bitmap);
    }
}
