package com.blogspot.mowael.athleteslistingcodeexercise.splashScreen;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.blogspot.mowael.athleteslistingcodeexercise.R;
import com.blogspot.mowael.athleteslistingcodeexercise.main.activities.MainActivity;
import com.blogspot.mowael.molib.activities.MoSplashScreenActivity;
import com.blogspot.mowael.molib.activities.SplashScreenSettings;
import com.blogspot.mowael.molib.utilities.MoConfig;
import com.blogspot.mowael.molib.utilities.ViewUtils;

public class SplashScreenActivity extends MoSplashScreenActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setSplashScreenSettings(new SplashScreenSettings() {
            @Override
            public int getSplashScreenLayout() {
                return R.layout.activity_splash_screen;
            }

            @Override
            public int getSplashScreenTimeOut() {
                return MoConfig.DEFAULT_SPLASH_TIME_OUT;
            }

            @Override
            public Class<? extends AppCompatActivity> getMainActivityClass() {
                return MainActivity.class;
            }

            @Override
            public Bundle getBundle() {
                return null;
            }
        });
        super.onCreate(savedInstanceState);
    }
}
