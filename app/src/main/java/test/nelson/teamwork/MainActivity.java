package test.nelson.teamwork;


import android.os.Bundle;

import test.nelson.teamwork.fragment.ProjectListFragment;

public class MainActivity extends BaseActivity {

    public static final String KEY_CURRENT_FRAGMENT = "current_fragment_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Definitely need to improve this
        if (savedInstanceState == null)
            openFragmentDefault(new ProjectListFragment());

    }



}
