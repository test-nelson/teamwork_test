package test.nelson.teamwork;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.transition.TransitionInflater;
import android.view.View;

import butterknife.ButterKnife;
import test.nelson.teamwork.fragment.ProjectDetailFragment;
import test.nelson.teamwork.model.ProjectSelectedEvent;

/**
 * Created by nelsonnwezeaku on 3/7/18.
 */

public abstract class BaseActivity extends AppCompatActivity {


    public void openFragment(Fragment fragment, boolean addToBackStack, int enterRight, int exitLeft, int enterLeft, int exitRight) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(enterRight, exitLeft, enterLeft, exitRight);
        transaction.replace(R.id.fragment_container, fragment);
        if (addToBackStack) transaction.addToBackStack(null);
        getSupportFragmentManager().executePendingTransactions();
        transaction.commit();
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



    public void openProjectDetailFragment(Fragment current, ProjectSelectedEvent event) {


        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment projectDetail = ProjectDetailFragment.getInstance(event);
        View sharedView = ButterKnife.findById(this, R.id.project_logo);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            current.setSharedElementReturnTransition(TransitionInflater.from(this).inflateTransition(R.transition.default_transition));
            current.setExitTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));

            projectDetail.setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.default_transition));
            projectDetail.setEnterTransition(TransitionInflater.from(this).inflateTransition(android.R.transition.no_transition));
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, projectDetail, "project_detail");
        fragmentTransaction.addToBackStack("project_detail");
        fragmentTransaction.addSharedElement(sharedView, "transition"+event.getAdapterPosition());
        fragmentTransaction.commit();

    }


}
