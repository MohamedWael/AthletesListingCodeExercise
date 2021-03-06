package com.blogspot.mowael.molib.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.blogspot.mowael.molib.R;
import com.blogspot.mowael.molib.presenter.MoContract;
import com.blogspot.mowael.molib.utilities.Logger;

//// TODO: 6/12/2017 Make sure to remove any allocated variable on the on destroy method

public abstract class MoFragment<T extends MoContract.MoPresenter> extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private View rootView;
    private ProgressBar pbProgress;
    private RelativeLayout rlFragmentRoot;
    private SwipeRefreshLayout srlRoot;
    private LinearLayout llBlockView;

    public MoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_mo, container, false);
        bindViewToRootView(rootView);
        return rootView;
    }

    /**
     * @param rootView
     */
    protected void bindViewToRootView(View rootView) {
        srlRoot = (SwipeRefreshLayout) rootView.findViewById(R.id.srlRoot);
        rlFragmentRoot = (RelativeLayout) rootView.findViewById(R.id.rlFragmentRoot);
        pbProgress = (ProgressBar) rootView.findViewById(R.id.pbProgress);
        llBlockView = (LinearLayout) rootView.findViewById(R.id.llBlockView);
        enableSwipeRefresh(false);
        srlRoot.setOnRefreshListener(this);
        llBlockView.setVisibility(View.GONE);
    }

    public void setRefreshing(boolean refreshing) {
        if (srlRoot != null) {
            srlRoot.setRefreshing(refreshing);
        }
    }

    public boolean isRefreshing() {
        if (srlRoot != null)
            return srlRoot.isRefreshing();
        return false;
    }

    public void enableSwipeRefresh(boolean refreshing) {
        if (srlRoot != null)
            srlRoot.setEnabled(refreshing);
    }

    /**
     * BlockView is linearLayout with vertical orientation on top of the FragmentRoot relativeLayout
     *
     * @param viewRes view to be added on top of fragment
     */
    public void addBlockView(@LayoutRes int viewRes) {
        addBlockView(LayoutInflater.from(getContext()).inflate(viewRes, null));
    }

    /**
     * BlockView is linearLayout with vertical orientation on top of the FragmentRoot relativeLayout
     *
     * @param view to be added on top of fragment
     */
    public void addBlockView(View view) {
        if (llBlockView != null)
            llBlockView.addView(view);
    }

    public void showBlockView(boolean show) {
        if (llBlockView != null)
            llBlockView.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    /**
     * You have to call on super.onCreateView() in your onCreateView before inflating your View
     *
     * @return the root view inflated with the MoFragment
     */
    @Nullable
    public View attachToRootView(View view) {
        if (rootView == null)
            throwMoFragmentException();
        rlFragmentRoot.addView(view);
        return rootView;
    }

    /**
     * @return the root progress bar attached in the root view
     */
    @Nullable
    protected ProgressBar getRootProgressBar() {
        return pbProgress;
    }

    protected void showRootProgress(boolean show) {
        if (getRootProgressBar() != null) {
            getRootProgressBar().setVisibility(show ? View.VISIBLE : View.GONE);
        } else {
            Logger.e("getRootProgressBar()", "is null");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (getPresenter() != null)
            getPresenter().onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void startActivity(Context context, Class<? extends Activity> aClass, @Nullable Bundle extras, boolean finish) {
        Intent intent = new Intent(context, aClass);
        if (extras != null) {
            intent.putExtras(extras);
        }
        startActivity(intent);
        if (finish) ((Activity) context).finish();
    }

    /**
     * start the required Activity and finish the origin
     *
     * @param aClass
     */
    public void startActivity(Class<? extends Activity> aClass) {
        startActivity(aClass, true);
    }

    public void startActivity(Class<? extends Activity> aClass, boolean finish) {
        startActivity(getContext(), aClass, null, finish);
    }

    public void startActivity(Class<? extends Activity> aClass, Bundle extras, boolean finish) {
        startActivity(getContext(), aClass, extras, finish);
    }

    public void startActivity(Context context, Class<? extends Activity> aClass, boolean finish) {
        startActivity(context, aClass, null, finish);
    }

    public void startActivity(Context context, Class<? extends Activity> aClass) {
        startActivity(context, aClass, null, false);
    }

    public <T extends MoFragment> void loadFragment(T fragment, @IdRes int in, String tag, boolean isAddToBackStack) {
        if (isAddToBackStack)
            getFragmentManager().beginTransaction().addToBackStack(null).replace(in, fragment, tag).commit();
        else getFragmentManager().beginTransaction().replace(in, fragment, tag).commit();
    }

    public <T extends MoFragment> void loadFragment(T fragment, @IdRes int in, boolean isAddToBackStack) {
        loadFragment(fragment, in, fragment.getClass().getSimpleName(), isAddToBackStack);
    }

    /**
     * load fragment in the provided layout and add it to the backStack
     *
     * @param fragment
     * @param in
     * @param <T>
     */
    public <T extends MoFragment> void loadFragment(T fragment, @IdRes int in) {
        loadFragment(fragment, in, true);
    }

    public <T extends MoFragment> void loadFragment(T fragment, boolean isAddToBackStack) {
        loadFragment(fragment, R.id.flFragment, isAddToBackStack);
    }

    /**
     * it adds the fragment to backStack by default.
     *
     * @param fragment
     * @param <T>
     */
    public <T extends MoFragment> void loadFragment(T fragment) {
        loadFragment(fragment, true);
    }

    private void throwMoFragmentException() {
        throw new RuntimeException("You have to call on super.onCreateView() in your onCreateView before inflating your View");
    }

    @Override
    public void onRefresh() {

    }
    public boolean isRunning() {
        FragmentActivity activity = getActivity();
        return activity != null && isAdded();
    }
    public void onStartLoadingProgress() {
        // in your base fragment start your own progress view or progress dialog
    }

    public void onLoadingProgressComplete() {
        // in your base fragment stop your own progress view or progress dialog
    }

    protected void finish() {
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        if (pbProgress != null) pbProgress = null;
        if (rlFragmentRoot != null) rlFragmentRoot = null;
        if (srlRoot != null) srlRoot = null;
        if (llBlockView != null) llBlockView = null;
        if (rootView != null) rootView = null;
        if (getPresenter() != null) {
            getPresenter().onDestroy();
            MoContract.MoPresenter presenter = getPresenter();
            presenter = null;
        }
        super.onDestroy();
    }

    public abstract T getPresenter();
}
