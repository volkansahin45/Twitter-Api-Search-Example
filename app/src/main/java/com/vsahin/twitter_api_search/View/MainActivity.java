package com.vsahin.twitter_api_search.View;

import android.arch.lifecycle.LifecycleActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.vsahin.twitter_api_search.R;
import com.vsahin.twitter_api_search.View.TweetList.TweetListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends LifecycleActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        fragmentManager = getSupportFragmentManager();

        if(getFragmentBackStackCount() == 0){
            showFragment(TweetListFragment.newInstance());
        }
    }

    public void showFragment(Fragment nextFragment){
        //be sure to not load same fragment
        if(isLastFragmentInBackstack(nextFragment)){
            return;
        }

        fragmentManager.beginTransaction()
                .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out)
                .replace(R.id.fragment_container , nextFragment)
                .addToBackStack(nextFragment.getClass().getName())
                .commit();

    }

    public boolean isLastFragmentInBackstack(Fragment fragment){
        String currentFragmentName;
        String nextFragmentName = fragment.getClass().getName();

        //if count is 0 it means there isnt any fragment in backstack
        int count = getFragmentBackStackCount();
        if(count != 0){
            currentFragmentName = getLastFragmentNameInBackStack();
            if(currentFragmentName.equals(nextFragmentName)){
                return true;
            }
        }
        return false;
    }

    public String getLastFragmentNameInBackStack(){
        return fragmentManager.getBackStackEntryAt(getFragmentBackStackCount() - 1).getName();
    }

    public int getFragmentBackStackCount(){
        return fragmentManager.getBackStackEntryCount();
    }

    @Override
    public void onBackPressed() {
        if(getFragmentBackStackCount() == 1){
            finish();
        }

        super.onBackPressed();
    }
}
