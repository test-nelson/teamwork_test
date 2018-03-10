package test.nelson.teamwork.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by nelsonnwezeaku on 3/10/18.
 */
@Getter
@Setter
public class UserInfo extends RealmObject {
    @PrimaryKey
    long id = 0;

    String projectListLastUpdateDate;
    String projectListLastUpdateTime;
}
