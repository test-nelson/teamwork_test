package test.nelson.teamwork.application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class TeamworkApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("teamwork_test")
                .schemaVersion(2)
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
