package test.nelson.teamwork.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import test.nelson.teamwork.R;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public abstract class BaseFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Nullable
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    public void setUpList(RecyclerView recyclerView, RecyclerView.Adapter adapter) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setOnRefreshListener(this);
        setUpList(recyclerView, layoutManager, adapter);
    }

    public void setUpList(RecyclerView recyclerView, RecyclerView.LayoutManager layoutManager, RecyclerView.Adapter adapter) {
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onRefresh() {

    }
}
