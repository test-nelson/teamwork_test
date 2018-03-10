package test.nelson.teamwork.contracts;

/**
 * Created by nelsonnwezeaku on 3/8/18.
 */

public interface ProjectDetailView extends BaseView {
    long getProjectId();

    void setupToolbarWithTitle(String name);

    void setLogo(String logo);


    void setDescription(String description);

    void setCreatedDate(String date);

    void setLastModifiedDate(String date);

    void setExecutionPeriod(String period);

    void setActivityState(boolean active);

    void setTimersEnabled(boolean enabled);

    void setReplyByEmailEnabled(boolean enabled);

    void starProject();

    void unstarProject();

    void setCompanyName(String name);
}
