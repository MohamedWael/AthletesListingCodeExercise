package com.blogspot.mowael.athleteslistingcodeexercise.main.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.mowael.athleteslistingcodeexercise.R;
import com.blogspot.mowael.athleteslistingcodeexercise.base.AthleteUIUtil;
import com.blogspot.mowael.athleteslistingcodeexercise.main.pojo.Athlete;


public class DetailsDialogFragment extends BottomSheetDialogFragment {


    /**
     * Android Views
     **/
    private ImageView ivAthleteImage;
    private TextView tvName;
    private TextView tvBrief;
    /**
     * Android Views
     **/


    private static final String ARG_ITEM = "athleteItem";


    public static DetailsDialogFragment newInstance(Athlete athlete) {
        final DetailsDialogFragment fragment = new DetailsDialogFragment();
        final Bundle args = new Bundle();
        args.putSerializable(ARG_ITEM, athlete);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_dialog, container, false);
        bindViews(view);
        if (getArguments() != null) {
            Athlete athlete = (Athlete) getArguments().getSerializable(ARG_ITEM);
            populateView(athlete);
        } else dismiss();

        return view;
    }

    public void showDetails(FragmentManager manager) {
        show(manager, this.getClass().getSimpleName());
    }

    private void bindViews(View rootView) {
        ivAthleteImage = (ImageView) rootView.findViewById(R.id.ivAthleteImage);
        tvName = (TextView) rootView.findViewById(R.id.tvName);
        tvBrief = (TextView) rootView.findViewById(R.id.tvBrief);

    }

    private void populateView(Athlete athlete) {
        if (athlete != null) {
            ivAthleteImage.setVisibility(TextUtils.isEmpty(athlete.getImage()) ? View.GONE : View.VISIBLE);
            AthleteUIUtil.downloadImage(getContext(), athlete.getImage(), ivAthleteImage);
            tvName.setText(athlete.getName());
            tvBrief.setText(athlete.getBrief());
        } else dismiss();
    }
}
