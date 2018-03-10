package test.nelson.teamwork;


import android.os.Bundle;
import android.support.v4.app.Fragment;

import test.nelson.teamwork.fragment.ProjectListFragment;

public class MainActivity extends BaseActivity {

    public static final String KEY_CURRENT_FRAGMENT = "current_fragment_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Definitely should be improved
        if (savedInstanceState == null)
            openFragmentDefault(new ProjectListFragment());

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        getSupportFragmentManager().putFragment(outState, KEY_CURRENT_FRAGMENT, getCurrentFragment());

    }

    private Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragment_container);
    }
}
