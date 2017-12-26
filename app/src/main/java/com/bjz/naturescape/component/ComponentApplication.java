package com.bjz.naturescape.component;

import android.app.Application;

/**
 * Created by bjz on 12/26/2017.
 */

public class ComponentApplication extends Application {
    private NetComponent mNetComponent;


    @Override
    public void onCreate() {
        super.onCreate();
        mNetComponent = DaggerNetComponent.builder()
                .build();
    }

    public NetComponent getmNetComponent() {
        return mNetComponent;
    }
}
