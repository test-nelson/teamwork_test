package test.nelson.teamwork.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;

/**
 * Created by nelsonnwezeaku on 3/8/18.
 */
@Getter
public class Company extends RealmObject{

    @PrimaryKey
    @SerializedName("id")
    long id;

    @SerializedName("name")
    String name;


}
