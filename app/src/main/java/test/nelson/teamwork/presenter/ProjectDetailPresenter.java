package test.nelson.teamwork.presenter;

import io.realm.Realm;
import test.nelson.teamwork.contracts.ProjectDetailView;
import test.nelson.teamwork.model.Project;

/**
 * Created by nelsonnwezeaku on 3/8/18.
 */

public class ProjectDetailPresenter extends BasePresenter {
    private ProjectDetailView view;
    private Realm realm;


    public ProjectDetailPresenter(ProjectDetailView view) {
        this.view = view;
    }


    public void onViewCreated() {
        realm = Realm.getDefaultInstance();
        final Project project = cacheHelper.getProject(realm, view.getProjectId());
        setupWithProject(project);

    }

    private void setupWithProject(Project project) {
        if (project == null) return;
        view.setupToolbarWithTitle(project.getName());
        view.setLogo(project.getLogo());
        view.setDescription(project.getDescription());

    }


    public void onViewDestroyed() {
        realm.close();
    }

}
