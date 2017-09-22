package com.blogspot.mowael.athleteslistingcodeexercise.main.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.blogspot.mowael.athleteslistingcodeexercise.R;
import com.blogspot.mowael.athleteslistingcodeexercise.main.fragments.AthletesFragment;
import com.blogspot.mowael.molib.activities.MoActivity;

public class MainActivity extends MoActivity {

    private AthletesFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enableToolbar();
        setTitle(R.string.main);
        loadFragment(getFragment(), R.id.flFragment, false);
    }

    public AthletesFragment getFragment() {
        if (fragment == null) {
            fragment = AthletesFragment.newInstance();
        }
        return fragment;
    }

    @Override
    protected void onDestroy() {
        if (fragment != null) fragment = null;
        super.onDestroy();
    }
}
