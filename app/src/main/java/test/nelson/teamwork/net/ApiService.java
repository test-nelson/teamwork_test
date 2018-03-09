package test.nelson.teamwork.net;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
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

    public void getProjects(Observer<ProjectListResponse> observer) {
        subscribeObserver(api.getProjects(), observer);
    }

    public void starProject(long projectId, Observer<StarProjectStatus> observer) {
        subscribeObserver(api.starProject(projectId), observer);
    }

    public void unStarProject(long projectId, Observer<StarProjectStatus> observer) {
        subscribeObserver(api.unStarProject(projectId), observer);

    }


    private <T> void subscribeObserver(Observable<T> observable, Observer<T> observer) {

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
