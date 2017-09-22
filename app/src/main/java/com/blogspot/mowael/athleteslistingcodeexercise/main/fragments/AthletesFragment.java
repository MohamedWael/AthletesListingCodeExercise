package com.blogspot.mowael.athleteslistingcodeexercise.main.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.mowael.athleteslistingcodeexercise.R;
import com.blogspot.mowael.athleteslistingcodeexercise.main.Presenter.AthletesPresenter;
import com.blogspot.mowael.athleteslistingcodeexercise.main.adapter.AthletesAdapter;
import com.blogspot.mowael.athleteslistingcodeexercise.main.contract.AthletesContract;
import com.blogspot.mowael.athleteslistingcodeexercise.main.pojo.Athlete;
import com.blogspot.mowael.molib.fragments.MoFragment;
import com.blogspot.mowael.molib.utilities.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class AthletesFragment extends MoFragment<AthletesContract.AthletesPresenter> implements AthletesContract.AthletesView {

    /**
     * Android Views
     **/
    private RecyclerView rvAthleteList;
    /**
     * Android Views
     **/

    private AthletesContract.AthletesPresenter presenter;
    private AthletesAdapter adapter;

    public AthletesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AthletesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AthletesFragment newInstance() {
        AthletesFragment fragment = new AthletesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new AthletesPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_athletes, container, false);
        bindViews(view);
        enableSwipeRefresh(true);
        getPresenter().getData();
        return attachToRootView(view);
    }


    private void bindViews(View rootView) {
        rvAthleteList = rootView.findViewById(R.id.rvAthleteList);
        rvAthleteList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AthletesAdapter(new ArrayList<Athlete>());
        rvAthleteList.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {
        setRefreshing(true);
        presenter.getData();
    }

    @Override
    public AthletesContract.AthletesPresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onShowProgress(boolean show) {
        showRootProgress(show);
    }

    @Override
    public void onHandleSuccessResponse(List<Athlete> athletes) {
        setRefreshing(false);
        if (athletes != null) {
            adapter.setItems((ArrayList<Athlete>) athletes);
        }
    }

    @Override
    public void onHandleError() {
        if (isRunning()) {
            setRefreshing(false);
            ViewUtils.snakeMsg(getView(), getString(R.string.somw_thing_error));
        }
    }
}
