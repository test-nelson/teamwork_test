package test.nelson.teamwork.net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import test.nelson.teamwork.model.ProjectListResponse;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public interface Api {

    @GET("projects.json")
    public Observable<ProjectListResponse> getProjects();
}
