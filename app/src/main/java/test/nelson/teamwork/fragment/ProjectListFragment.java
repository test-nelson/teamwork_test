package test.nelson.teamwork.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmResults;
import test.nelson.teamwork.R;
import test.nelson.teamwork.adapter.ProjectListAdapter;
import test.nelson.teamwork.contracts.ProjectListView;
import test.nelson.teamwork.model.Project;
import test.nelson.teamwork.presenter.ProjectListPresenter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProjectListFragment extends BaseFragment implements ProjectListView {

    @BindView(R.id.recycler_view)
    RecyclerView projectList;

    private ProjectListAdapter adapter;

    private ProjectListPresenter presenter;

    public ProjectListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_list, container, false);
        ButterKnife.bind(this, view);
        presenter = new ProjectListPresenter(this);


        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onViewDestroyed();
    }

    @Override
    public void setProjects(RealmResults<Project> projects) {
        adapter = new ProjectListAdapter(projects);
        setUpList(projectList, adapter);
    }
}
