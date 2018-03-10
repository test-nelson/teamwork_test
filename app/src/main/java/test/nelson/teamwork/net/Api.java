package test.nelson.teamwork.net;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import test.nelson.teamwork.model.ProjectListResponse;
import test.nelson.teamwork.model.StarProjectStatus;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public interface Api {

    @GET("projects.json")
    Observable<ProjectListResponse> getProjects(
            @Query("createdAfterDate") String afterDate,
            @Query("createdAfterTime") String afterTime);


    @PUT("projects/{project_id}/star.json")
    Observable<StarProjectStatus> starProject(@Path("project_id") long projectId);

    @PUT("projects/{project_id}/unstar.json")
    Observable<StarProjectStatus> unStarProject(@Path("project_id") long projectId);
}
