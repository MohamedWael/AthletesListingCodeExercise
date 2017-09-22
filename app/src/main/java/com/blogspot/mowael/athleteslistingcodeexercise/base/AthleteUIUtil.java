package com.blogspot.mowael.athleteslistingcodeexercise.base;

import android.content.Context;
import android.widget.ImageView;

import com.blogspot.mowael.athleteslistingcodeexercise.R;
import com.blogspot.mowael.molib.utilities.Logger;
import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;

/**
 * Created by moham on 9/22/2017.
 */

public class AthleteUIUtil {
    public static void downloadImage(Context activity, String url, ImageView imageView) {
        downloadImage(activity, url, R.mipmap.place_holder).into(imageView);
    }

    private static DrawableRequestBuilder<String> downloadImage(Context context, String url, int placeHolder) {
        Logger.d("ImageUrl", url);
        return Glide.with(context).load(url).placeholder(placeHolder);
    }
}
