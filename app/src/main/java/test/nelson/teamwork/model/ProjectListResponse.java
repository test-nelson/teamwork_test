package test.nelson.teamwork.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */
@Getter
public class ProjectListResponse {
    @SerializedName("STATUS")
    String status;
    @SerializedName("projects")
    List<Project> projects;
}
