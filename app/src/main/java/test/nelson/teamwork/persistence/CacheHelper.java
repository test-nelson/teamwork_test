package test.nelson.teamwork.persistence;

import android.support.annotation.NonNull;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import test.nelson.teamwork.model.Project;
import test.nelson.teamwork.model.ProjectsUpdatedEvent;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class CacheHelper {

    public RealmResults<Project> getProjects(Realm realm) {
        return realm.where(Project.class).findAll();
    }

    public Project getProject(Realm realm, long projectId) {
        return realm.where(Project.class).equalTo("id", projectId).findFirst();

    }

    public void saveProjects(final List<Project> projects) {

        executeTransactionAndClose(new Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealmOrUpdate(projects);
                EventBus.getDefault().post(new ProjectsUpdatedEvent());
            }
        });
    }


    private void executeTransactionAndClose(Transaction transaction) {
        Realm realm = null;
        try {
            realm = Realm.getDefaultInstance();
            realm.executeTransaction(transaction);
        } finally {
            if (realm != null)
                realm.close();
        }
    }


}
