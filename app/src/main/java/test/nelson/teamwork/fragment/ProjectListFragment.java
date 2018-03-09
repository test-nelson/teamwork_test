package test.nelson.teamwork.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.Subscribe;

import butterknife.ButterKnife;
import io.realm.RealmResults;
import test.nelson.teamwork.R;
import test.nelson.teamwork.adapter.ProjectListAdapter;
import test.nelson.teamwork.contracts.ProjectListView;
import test.nelson.teamwork.model.Project;
import test.nelson.teamwork.model.ProjectSelectedEvent;
import test.nelson.teamwork.model.ProjectsUpdatedEvent;
import test.nelson.teamwork.presenter.ProjectListPresenter;
import test.nelson.teamwork.utils.CustomLinearLayoutManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectListFragment extends BaseFragment implements ProjectListView {


    private ProjectListPresenter presenter = new ProjectListPresenter();

    public ProjectListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);
        ButterKnife.bind(this, view);
        setupToolbarWithTitle("Projects");


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        eventBus.unregister(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated(this);
    }

    @Override
    public void onRefresh() {
        super.onRefresh();
        presenter.onSwipeToRefresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onViewDestroyed();
    }

    @Override
    public void setProjects(RealmResults<Project> projects) {
        ProjectListAdapter adapter = new ProjectListAdapter(projects);
        setUpList(new CustomLinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false), adapter);
        disableDefaultRecyclerViewAnimations();

    }

    @Override
    public void openProjectDetailFragment(ProjectSelectedEvent event) {
        getBaseActivity().openProjectDetailFragment(this, event);
    }

    @Subscribe
    public void onProjectSelectedEvent(ProjectSelectedEvent event) {

        presenter.onProjectSelectedEvent(event);
    }

    @Subscribe
    public void onProjectsUpdatedEvent(ProjectsUpdatedEvent event) {
        presenter.onProjectsUpdated();
    }


}
