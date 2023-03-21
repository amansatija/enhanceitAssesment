package com.example.enhanceittechincaltest.utils.api.picasso;

import android.content.Context;

import com.squareup.picasso.Picasso;


public class PicassoCache {

    /**
     * Static Picasso Instance
     */
    private static Picasso picassoInstance = null;

    /**
     * PicassoCache Constructor
     *
     * @param context application Context
     */
    private PicassoCache(Context context) {

//        Downloader downloader = new OkHttpDownloader(context, Integer.MAX_VALUE);
        Picasso.Builder builder = new Picasso.Builder(context);
//        builder.downloader(downloader);
        builder.loggingEnabled(true);

        picassoInstance = builder.build();
    }

    /**
     * Get Singleton Picasso Instance
     *
     * @param context application Context
     * @return Picasso instance
     */
    public static Picasso getPicassoInstance(Context context) {

        if (picassoInstance == null) {

            new PicassoCache(context);
            return picassoInstance;
        }

        return picassoInstance;
    }

} 