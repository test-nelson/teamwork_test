package test.nelson.teamwork.repository;

import io.realm.Realm;
import lombok.Getter;
import lombok.Setter;
import test.nelson.teamwork.contracts.BaseView;
import test.nelson.teamwork.model.ProjectListResponse;
import test.nelson.teamwork.model.StarProjectStatus;
import test.nelson.teamwork.model.UserInfo;
import test.nelson.teamwork.net.ApiService;
import test.nelson.teamwork.net.CallbackWrapper;
import test.nelson.teamwork.persistence.CacheHelper;
import test.nelson.teamwork.utils.Config;

import static test.nelson.teamwork.utils.Config.USER_NAME;
import static test.nelson.teamwork.utils.DateTimeUtils.getUpdateDateNow;
import static test.nelson.teamwork.utils.DateTimeUtils.getUpdateTimeNow;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class Repository {

    private CacheHelper cacheHelper = new CacheHelper();

    private ApiService apiService;

    public Repository() {
        apiService = new ApiService(USER_NAME, Config.PASSWORD);
    }

    private ProjectUpdateParameters getParams(Realm realm) {
        ProjectUpdateParameters parameters = new ProjectUpdateParameters();
        UserInfo info = cacheHelper.getUserInfo(realm);
        if (info != null) {
            parameters.setDate(info.getProjectListLastUpdateDate());
            parameters.setTime(info.getProjectListLastUpdateTime());
        } else {
            info = new UserInfo();
            info.setProjectListLastUpdateDate(getUpdateDateNow());
            info.setProjectListLastUpdateTime(getUpdateTimeNow());
        }
        parameters.setInfo(info);
        return parameters;
    }

    public void downloadProjects(Realm realm, BaseView view) {

        final ProjectUpdateParameters parameters = getParams(realm);
        apiService.getProjects(
                parameters.date,
                parameters.time,
                new CallbackWrapper<ProjectListResponse>(view) {
                    @Override
                    protected void onSuccess(ProjectListResponse response) {
                        if ("OK".equals(response.getStatus())) {
                            cacheHelper.saveProjects(response.getProjects());
                            cacheHelper.updateProjectParams(parameters.getInfo());
                        }

                    }
                });

    }


    public void starProject(BaseView view, long projectId) {
        apiService.starProject(projectId, new CallbackWrapper<StarProjectStatus>(view) {
            @Override
            protected void onSuccess(StarProjectStatus starProjectStatus) {

            }
        });
    }

    public void unStarProject(BaseView view, long projectId) {
        apiService.unStarProject(projectId, new CallbackWrapper<StarProjectStatus>(view) {
            @Override
            protected void onSuccess(StarProjectStatus starProjectStatus) {

            }
        });
    }

    @Getter
    @Setter
    private class ProjectUpdateParameters {
        private String date;
        private String time;
        private UserInfo info;
    }
}
