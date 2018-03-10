package test.nelson.teamwork.contracts;

import io.realm.RealmResults;
import test.nelson.teamwork.model.Project;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public interface ProjectListView extends BaseView{
    void setProjects(RealmResults<Project> projects);

    void stopRefreshing();

    void openProjectDetailFragment(long id);

    void startRefreshing();

    void showEmptyProjectsView();

    void showProjectsView();
}
