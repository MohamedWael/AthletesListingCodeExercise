package com.blogspot.mowael.athleteslistingcodeexercise.main.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blogspot.mowael.athleteslistingcodeexercise.R;
import com.blogspot.mowael.athleteslistingcodeexercise.base.AthleteUIUtil;
import com.blogspot.mowael.athleteslistingcodeexercise.main.pojo.Athlete;

/**
 * Created by moham on 9/22/2017.
 */

public class AthleteView extends LinearLayout {

    /**
     * Android Views
     **/
    private ImageView ivAthleteImage;
    private TextView tvName;
    private TextView tvBrief;

    /**
     * Android Views
     **/


    public AthleteView(Context context) {
        super(context);
        initView(context);
    }

    public AthleteView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public AthleteView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public AthleteView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_athlete, this);
        bindViews();
    }

    private void bindViews() {
        ivAthleteImage = findViewById(R.id.ivAthleteImage);
        tvName = findViewById(R.id.tvName);
        tvBrief = findViewById(R.id.tvBrief);
    }

    public void setAthleteItem(Athlete athleteItem) {
        tvName.setText(athleteItem.getName());
        tvBrief.setText(athleteItem.getBrief());
        AthleteUIUtil.downloadImage(getContext(), athleteItem.getImage(), ivAthleteImage);
    }
}
