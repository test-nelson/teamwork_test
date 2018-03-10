package test.nelson.teamwork.net;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.nelson.teamwork.model.BaseResponse;
import test.nelson.teamwork.model.ProjectListResponse;
import test.nelson.teamwork.model.StarProjectStatus;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class ApiService {
    private Api api;


    public ApiService(String username, String password) {
        api = RetrofitProvider.getInstance().getRetrofit(username, password).create(Api.class);
    }

    public void getProjects(CallbackWrapper<ProjectListResponse> observer) {
        subscribeObserver(api.getProjects(), observer);
    }

    public void starProject(long projectId, CallbackWrapper<StarProjectStatus> observer) {
        subscribeObserver(api.starProject(projectId), observer);
    }

    public void unStarProject(long projectId, CallbackWrapper<StarProjectStatus> observer) {
        subscribeObserver(api.unStarProject(projectId), observer);

    }


    private <T extends BaseResponse> void subscribeObserver(Observable<T> observable, CallbackWrapper<T> observer) {

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
