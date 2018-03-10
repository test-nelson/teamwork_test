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


    public ProjectListPresenter(ProjectListView view) {
        this.view = view;

    }


    public void onCreate() {
        realm = Realm.getDefaultInstance();
        repository.downloadProjects(realm, view);
    }

    public void onViewCreated() {

        final RealmResults<Project> projects = cacheHelper.getProjects(realm);
        if (projects.isEmpty())
            view.startRefreshing();
        view.setProjects(projects);
    }


    public void onDestroy() {
        realm.close();
    }

    public void onSwipeToRefresh() {
        repository.downloadProjects(realm, view);
    }

    public void onProjectsUpdated() {

        if (cacheHelper.getProjects(realm).isEmpty())
            view.showEmptyProjectsView();
        else view.showProjectsView();

        view.stopRefreshing();
    }

    public void onProjectSelectedEvent(ProjectSelectedEvent event) {
        view.openProjectDetailFragment(event.getId());
    }

    public void onNetworkError() {
        onProjectsUpdated();
    }

}
