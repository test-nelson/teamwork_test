package test.nelson.teamwork.presenter;

import test.nelson.teamwork.persistence.CacheHelper;
import test.nelson.teamwork.repository.Repository;

/**
 * Created by nelsonnwezeaku on 3/8/18.
 */

class BasePresenter {
    CacheHelper cacheHelper = new CacheHelper();
    Repository repository = new Repository();
}
