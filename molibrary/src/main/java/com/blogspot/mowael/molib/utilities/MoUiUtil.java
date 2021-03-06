package com.blogspot.mowael.molib.utilities;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

/**
 * Created by moham on 4/20/2017.
 */

public class MoUiUtil {

    private static MoUiUtil instance;
    private Context context;

    public static MoUiUtil getInstance() {
        if (instance == null) {
            instance = new MoUiUtil();
        }
        return instance;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private boolean isContextNull() {
        if (context == null) {
            throw new RuntimeException("you have to setContext after the initialization for the first time of the instance");
        }
        return false;
    }

    public DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public int[] getWindowMetrics(Activity activity) {
        int height = getDisplayMetrics(activity).heightPixels;
        int width = getDisplayMetrics(activity).widthPixels;
        return new int[]{width, height};
    }


    public int getWindowWidth(Activity activity) {
        return getWindowMetrics(activity)[0];
    }

    public int getWindowHeight(Activity activity) {
        return getWindowMetrics(activity)[1];
    }


    /**
     * @param context
     * @param items   array of strings
     * @return arrayAdapter of a spinner with a simple spinner item as a layout for a given array of strings
     */
    public ArrayAdapter<String> getArrayAdapter(Context context, String[] items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;

    }

    /**
     * @param context
     * @param items   array of strings
     * @return arrayAdapter of a ListView with a simple list item as a layout for a given array of strings
     */
    public ArrayAdapter<String> getArrayAdapter(Context context, List<String> items) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, items);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        return adapter;

    }

    /**
     * @param context
     * @return LayoutInflater from the provided context
     */
    public LayoutInflater getLayoutInflater(Context context) {
        return ViewUtils.getLayoutInflater(context);
    }


    /**
     * @return LayoutInflater from the Application context
     */
    public LayoutInflater getLayoutInflater() {
        isContextNull();
        return getLayoutInflater(context);
    }

    public SpannableString makeSpanString(String str, int fontSize, @ColorRes int color) {
        return ViewUtils.makeSpan(str, fontSize, getColor(color));
    }

    public SpannableString makeUnderLineString(String str, int fontSize, @ColorRes int color) {
        SpannableString string = makeSpanString(str, fontSize, getColor(color));
        string.setSpan(new UnderlineSpan(), 0, str.length(), SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
        return string;
    }

    public int getColor(@ColorRes int id) {
        isContextNull();
        return ViewUtils.getColor(context, id);
    }

    public String getString(int id) {
        isContextNull();
        return ViewUtils.getString(context, id);
    }

    public Drawable getDrawable(int id) {
        isContextNull();
        return ViewUtils.getDrawable(context, id);
    }

    public Toast toastMsg(String msg) {
        isContextNull();
        return ViewUtils.toastMsg(context, msg);
    }

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public float convertDpToPixel(float dp) {
        isContextNull();
        return ViewUtils.convertDpToPixel(dp, context);
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @return A float value to represent dp equivalent to px value
     */
    public float convertPixelsToDp(float px) {
        isContextNull();
        return ViewUtils.convertPixelsToDp(px, context);
    }

}
