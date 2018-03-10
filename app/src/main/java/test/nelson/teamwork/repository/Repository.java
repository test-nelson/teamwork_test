package test.nelson.teamwork.repository;

import test.nelson.teamwork.contracts.BaseView;
import test.nelson.teamwork.model.ProjectListResponse;
import test.nelson.teamwork.model.StarProjectStatus;
import test.nelson.teamwork.net.ApiService;
import test.nelson.teamwork.net.CallbackWrapper;
import test.nelson.teamwork.persistence.CacheHelper;
import test.nelson.teamwork.utils.Config;

import static test.nelson.teamwork.utils.Config.USER_NAME;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class Repository {

    private CacheHelper cacheHelper = new CacheHelper();

    private ApiService apiService;

    public Repository() {
        apiService = new ApiService(USER_NAME, Config.PASSWORD);
    }


    public void downloadProjects(BaseView view) {
        apiService.getProjects(new CallbackWrapper<ProjectListResponse>(view) {
            @Override
            protected void onSuccess(ProjectListResponse response) {
                if ("OK".equals(response.getStatus()))
                    cacheHelper.saveProjects(response.getProjects());
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
}
