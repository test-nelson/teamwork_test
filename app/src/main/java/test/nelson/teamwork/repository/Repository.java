package test.nelson.teamwork.repository;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import test.nelson.teamwork.model.ProjectListResponse;
import test.nelson.teamwork.net.ApiService;
import test.nelson.teamwork.persistence.CacheHelper;
import test.nelson.teamwork.utils.Config;

import static test.nelson.teamwork.utils.Config.USER_NAME;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class Repository {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private CacheHelper cacheHelper = new CacheHelper();

    private ApiService apiService;

    public Repository() {
        apiService = new ApiService(USER_NAME, Config.PASSWORD);
    }


    public void downloadProjects() {
        apiService.getProjects(new Observer<ProjectListResponse>() {
            @Override
            public void onSubscribe(Disposable d) {
                compositeDisposable.add(d);

            }

            @Override
            public void onNext(ProjectListResponse response) {
                if ("OK".equals(response.getStatus()))
                    cacheHelper.saveProjects(response.getProjects());


            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();

            }

            @Override
            public void onComplete() {

            }
        });

    }
}
