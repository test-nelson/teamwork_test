package test.nelson.teamwork.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import lombok.Getter;

/**
 * Created by nelsonnwezeaku on 3/9/18.
 */
@Getter
public class ProjectTag extends RealmObject {

    @SerializedName("id")
    long id;
    @SerializedName("name")
    String name;
    @SerializedName("color")
    String color;
}
