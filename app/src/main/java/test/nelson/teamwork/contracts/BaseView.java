package test.nelson.teamwork.contracts;

/**
 * Created by nelsonnwezeaku on 3/10/18.
 */

public interface BaseView {
    void onUnknownError(String message);

    void onTimeout();

    void onNetworkError();
}
