package test.nelson.teamwork.presenter;

import io.realm.Realm;
import test.nelson.teamwork.contracts.ProjectListView;
import test.nelson.teamwork.model.ProjectSelectedEvent;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class ProjectListPresenter extends BasePresenter{
    private ProjectListView view;
    private Realm realm;


    public ProjectListPresenter(ProjectListView view) {
        this.view = view;
        repository.downloadProjects();
    }


    public void onViewCreated() {
        realm = Realm.getDefaultInstance();
        view.setProjects(cacheHelper.getProjects(realm));
    }


    public void onViewDestroyed() {
        realm.close();
    }

    public void onSwipeToRefresh() {
        repository.downloadProjects();
    }

    public void onProjectsUpdated() {
        view.stopRefreshing();
    }

    public void onProjectSelectedEvent(ProjectSelectedEvent event) {
        view.openProjectDetailFragment(event.getId());
    }
}
