package com.bjz.naturescape.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.bjz.naturescape.R;
import com.bjz.naturescape.fragments.PostsFragment;
import com.bjz.naturescape.fragments.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends FragmentActivity {
    @BindView(R.id.main_bottom_navigation)
    BottomNavigationView bottomNavigationView;
    PostsFragment postsFragment;
    ProfileFragment profileFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        postsFragment = new PostsFragment();
        profileFragment = new ProfileFragment();

        FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_content_fragment, postsFragment)
                .add(R.id.main_content_fragment, profileFragment);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction = getSupportFragmentManager()
                        .beginTransaction();

                switch (item.getItemId()) {
                    case R.id.action_home:
                        fragmentTransaction.replace(R.id.main_content_fragment, postsFragment);
                        break;
                    case R.id.action_profile:
                        fragmentTransaction.replace(R.id.main_content_fragment, profileFragment);
                        break;
                    case R.id.action_settings:
                        break;
                }
                return true;
            }
        });
    }
}
