package test.nelson.teamwork.presenter;

import io.realm.Realm;
import test.nelson.teamwork.contracts.ProjectDetailView;
import test.nelson.teamwork.model.Project;

import static test.nelson.teamwork.utils.DateTimeUtils.DATE_FORMAT_SHORT;
import static test.nelson.teamwork.utils.DateTimeUtils.stringToDateFormat1;
import static test.nelson.teamwork.utils.DateTimeUtils.stringToDateFormat2;

/**
 * Created by nelsonnwezeaku on 3/8/18.
 */

public class ProjectDetailPresenter extends BasePresenter {
    private static final String PROJECT_STATUS_ACTIVE = "active";
    private ProjectDetailView view;
    private Realm realm;

    private Project project;


    public ProjectDetailPresenter(ProjectDetailView view) {
        this.view = view;
    }


    public void onViewCreated() {
        realm = Realm.getDefaultInstance();
        project = cacheHelper.getProject(realm, view.getProjectId());
        setupWithProject();

    }

    private void setupWithProject() {
        if (project == null) return;
        view.setupToolbarWithTitle(project.getName());
        view.setLogo(project.getLogo());
        view.setDescription(project.getDescription());
        view.setCompanyName(project.getCompany().getName());
        view.setCreatedDate(stringToDateFormat1(project.getCreatedOn(), DATE_FORMAT_SHORT));
        view.setLastModifiedDate(stringToDateFormat1(project.getLastChangedOn(), DATE_FORMAT_SHORT));
        String executionPeriod = stringToDateFormat2(project.getStartDate(), DATE_FORMAT_SHORT) + " - "
                + stringToDateFormat2(project.getEndDate(), DATE_FORMAT_SHORT);
        view.setExecutionPeriod(executionPeriod);
        view.setActivityState(PROJECT_STATUS_ACTIVE.equals(project.getStatus()));
        view.setTimersEnabled(project.isHarvestTimersEnabled());
        view.setReplyByEmailEnabled(project.isReplyByEmailEnabled());
        setProjectStarStatus();


    }


    public void onViewDestroyed() {
        realm.close();
    }

    public void onProjectStarred() {
        project.setStarred(!project.isStarred());
        setProjectStarStatus();

    }

    private void setProjectStarStatus() {
        // TODO: 3/9/18 Not a good idea to send these requests every time. A better implementation will batch the requests and send later
        if (project.isStarred()) {
            view.starProject();
            repository.starProject(view, project.getId());
        } else {
            view.unstarProject();
            repository.unStarProject(view, project.getId());
        }

    }

    public void onPause() {
        save();
    }

    private void save() {
        cacheHelper.saveProject(project);

        // TODO: 3/9/18 Send changes to server
    }
}
