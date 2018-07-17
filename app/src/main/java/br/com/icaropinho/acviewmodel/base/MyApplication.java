package br.com.icaropinho.acviewmodel.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by icaro on 11/07/2018.
 */

public class MyApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.create();
    }

    public static ApplicationComponent getApplicationComponent(Context context) {
        return ((MyApplication) context.getApplicationContext()).component;
    }
}
