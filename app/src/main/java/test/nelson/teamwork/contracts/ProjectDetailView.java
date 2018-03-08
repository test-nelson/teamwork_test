package test.nelson.teamwork.contracts;

/**
 * Created by nelsonnwezeaku on 3/8/18.
 */

public interface ProjectDetailView {
    long getProjectId();

    void setupToolbarWithTitle(String name);

    void setLogo(String logo);


    void setDescription(String description);
}
