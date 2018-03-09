package test.nelson.teamwork.presenter;

import io.realm.Realm;
import io.realm.RealmResults;
import test.nelson.teamwork.contracts.ProjectListView;
import test.nelson.teamwork.model.Project;
import test.nelson.teamwork.model.ProjectSelectedEvent;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class ProjectListPresenter extends BasePresenter {
    private ProjectListView view;
    private Realm realm;


    public ProjectListPresenter() {
        repository.downloadProjects();
    }


    public void onViewCreated(ProjectListView view) {
        this.view = view;
        realm = Realm.getDefaultInstance();
        final RealmResults<Project> projects = cacheHelper.getProjects(realm);
        if (projects.size() == 0)
            view.startRefreshing();
        view.setProjects(projects);
    }


    public void onViewDestroyed() {
        view = null;
        realm.close();
    }

    public void onSwipeToRefresh() {
        repository.downloadProjects();
    }

    public void onProjectsUpdated() {
        view.stopRefreshing();
    }

    public void onProjectSelectedEvent(ProjectSelectedEvent event) {
        view.openProjectDetailFragment(event);
    }
}
