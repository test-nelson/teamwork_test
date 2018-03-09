package test.nelson.teamwork.contracts;

import io.realm.RealmResults;
import test.nelson.teamwork.model.Project;
import test.nelson.teamwork.model.ProjectSelectedEvent;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public interface ProjectListView {
    void setProjects(RealmResults<Project> projects);

    void stopRefreshing();

    void openProjectDetailFragment(ProjectSelectedEvent id);

    void startRefreshing();
}
