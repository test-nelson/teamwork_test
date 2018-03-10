package test.nelson.teamwork;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public abstract class BaseActivity extends AppCompatActivity {


    public void openFragment(Fragment fragment, boolean addToBackStack, int enterRight, int exitLeft, int enterLeft, int exitRight) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(enterRight, exitLeft, enterLeft, exitRight);
        transaction.add(R.id.fragment_container, fragment);
        if (addToBackStack) transaction.addToBackStack(null);
        getSupportFragmentManager().executePendingTransactions();
        transaction.commit();
    }


    public void openFragment(Fragment fragment, boolean addToBackStack) {
        openFragment(fragment, addToBackStack, R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right);
    }


    public void addFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    public void openFragmentDefault(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

}
