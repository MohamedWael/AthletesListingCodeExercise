package com.blogspot.mowael.athleteslistingcodeexercise.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.mowael.athleteslistingcodeexercise.main.activities.MainActivity;
import com.blogspot.mowael.athleteslistingcodeexercise.main.fragments.DetailsDialogFragment;
import com.blogspot.mowael.athleteslistingcodeexercise.main.pojo.Athlete;
import com.blogspot.mowael.athleteslistingcodeexercise.main.view.AthleteView;
import com.blogspot.mowael.molib.adapters.MoRecyclerBaseAdapter;

import java.util.ArrayList;

/**
 * Created by moham on 9/21/2017.
 */

public class AthletesAdapter extends MoRecyclerBaseAdapter<Athlete, AthletesAdapter.AthleteViewHolder> {

    public AthletesAdapter(ArrayList<Athlete> items) {
        super(items);
    }

    @Override
    public void onBindViewHolder(final AthleteViewHolder holder, final int position) {
        ((AthleteView) holder.itemView).setAthleteItem(getItem(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsDialogFragment.newInstance(getItem(position)).showDetails(((MainActivity) holder.itemView.getContext()).getSupportFragmentManager());
            }
        });
    }

    @Override
    public AthleteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        AthleteView athleteView = new AthleteView(parent.getContext());
        return new AthleteViewHolder(athleteView);
    }

    class AthleteViewHolder extends RecyclerView.ViewHolder {

        public AthleteViewHolder(AthleteView itemView) {
            super(itemView);
        }
    }
}
