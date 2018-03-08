package test.nelson.teamwork.presenter;

import io.realm.Realm;
import test.nelson.teamwork.contracts.ProjectListView;
import test.nelson.teamwork.persistence.CacheHelper;
import test.nelson.teamwork.repository.Repository;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class ProjectListPresenter {
    private ProjectListView view;
    private Realm realm;
    private CacheHelper cacheHelper = new CacheHelper();
    private Repository repository = new Repository();

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
}
