package test.nelson.teamwork.persistence;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmResults;
import test.nelson.teamwork.model.Project;
import test.nelson.teamwork.model.ProjectsUpdatedEvent;
import test.nelson.teamwork.model.UserInfo;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public class CacheHelper {

    public RealmResults<Project> getProjects(Realm realm) {
        return realm.where(Project.class).findAll();
    }

    public Project getProject(Realm realm, long projectId) {
        final Project project = realm.where(Project.class).equalTo("id", projectId).findFirst();
        if (project != null)
            return realm.copyFromRealm(project);
        return null;
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


    public void saveProject(final Project project) {

        executeTransactionAndClose(new Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealmOrUpdate(project);
                EventBus.getDefault().post(new ProjectsUpdatedEvent());
            }
        });
    }

    @Nullable
    public UserInfo getUserInfo(Realm realm) {
        UserInfo info = realm.where(UserInfo.class).findFirst();
        if (info != null) {
            return realm.copyFromRealm(info);
        }
        return null;
    }

    public void updateProjectParams(final UserInfo info) {
        executeTransactionAndClose(new Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealmOrUpdate(info);
            }
        });
    }
}
