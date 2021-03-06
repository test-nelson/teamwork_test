package test.nelson.teamwork.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */
@Getter
@Setter
public class Project extends RealmObject {

    @PrimaryKey
    @SerializedName("id")
    long id;

    @SerializedName("name")
    String name;

    @SerializedName("company")
    Company company;

    @SerializedName("starred")
    boolean starred;

    @SerializedName("description")
    String description;

    @SerializedName("status")
    String status;

    @SerializedName("startDate")
    String startDate;

    @SerializedName("created-on")
    String createdOn;

    @SerializedName("logo")
    String logo;

    @SerializedName("last-changed-on")
    String lastChangedOn;

    @SerializedName("endDate")
    String endDate;


    @SerializedName("replyByEmailEnabled")
    boolean replyByEmailEnabled;

    @SerializedName("show-announcement")
    boolean showAnnouncement;

    @SerializedName("harvest-timers-enabled")
    boolean harvestTimersEnabled;

    @SerializedName("tags")
    RealmList<ProjectTag> tags;

    @SerializedName("privacyEnabled")
    boolean privacyEnabled;

}
