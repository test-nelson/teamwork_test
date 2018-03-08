package test.nelson.teamwork.net;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import test.nelson.teamwork.model.ProjectListResponse;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class ApiService {
    private static ApiService service;
    private Api api;


    public ApiService(String username, String password) {
        api = RetrofitProvider.getInstance().getRetrofit(username, password).create(Api.class);
    }

    public void getProjects(Observer<ProjectListResponse> observer) {
        subscribeObserver(api.getProjects(), observer);
    }


    private <T> void subscribeObserver(Observable<T> observable, Observer<T> observer) {

        observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
