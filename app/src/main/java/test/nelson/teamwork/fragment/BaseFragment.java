package test.nelson.teamwork.fragment;

import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import test.nelson.teamwork.BaseActivity;
import test.nelson.teamwork.R;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public abstract class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Nullable
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Nullable
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Nullable
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    Snackbar snackbar;


    EventBus eventBus = EventBus.getDefault();

    public void setUpList(RecyclerView.Adapter adapter) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        setUpList(layoutManager, adapter);
    }


    public void setUpList(RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {
        setupSwipeToRefresh();
        if (recyclerView == null) return;
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupSwipeToRefresh() {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setOnRefreshListener(this);
    }

    //Temporary workaround
    public void disableDefaultRecyclerViewAnimations() {
        if (recyclerView == null) return;
        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) animator).setSupportsChangeAnimations(false);
        }
    }

    BaseActivity getBaseActivity() {
        return (BaseActivity) getActivity();
    }


    public void setupToolbarWithTitle(String title) {
        if (toolbar == null) return;
        getBaseActivity().setSupportActionBar(toolbar);
        getBaseActivity().getSupportActionBar().setTitle(title);

    }

    @Override
    public void onRefresh() {

    }


    public void onUnknownError(String message) {
        showMessage(message);
        onError();


    }

    public void onTimeout() {
        showMessage("Network timeout");
        onError();

    }

    public void onNetworkError() {
        showMessage("Network Error");
        onError();
    }

    public void onError() {
    }


    public void stopRefreshing() {
        setRefreshing(false);
    }

    public void startRefreshing() {
        setRefreshing(true);
    }

    private void setRefreshing(boolean refreshing) {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(refreshing);
    }

    public void showMessage(String message) {
        if (snackbar != null)
            snackbar.dismiss();
        final View view = getView();
        if (view != null)
            snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
