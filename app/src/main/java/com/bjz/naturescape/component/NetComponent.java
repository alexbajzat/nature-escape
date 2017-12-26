package com.bjz.naturescape.component;

import com.bjz.naturescape.activity.LoginActivity;
import com.bjz.naturescape.config.AppConfig;
import com.bjz.naturescape.fragments.PostsFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by bjz on 12/26/2017.
 */
@Singleton
@Component(modules = {AppConfig.class})
public interface NetComponent {
    void inject(LoginActivity activity);

    void inject(PostsFragment fragment);

}
