package com.example.numera;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

public class AppLifecycleListener implements Application.ActivityLifecycleCallbacks {

    private int resumed = 0; // number of activities currently resumed

    @Override
    public void onActivityResumed(Activity activity) {
        resumed++;
        if (resumed == 1) {
            // App just came to foreground
            if (!MusicManager.isPlaying()) {
                MusicManager.resume();
            }
        }
    }

    @Override
    public void onActivityPaused(Activity activity) {
        resumed--;
        if (resumed == 0) {
            // App just went to background
            MusicManager.pause();
        }
    }

    // Not needed, can leave empty
    @Override public void onActivityCreated(Activity activity, Bundle savedInstanceState) {}
    @Override public void onActivityStarted(Activity activity) {}
    @Override public void onActivityStopped(Activity activity) {}
    @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {}
    @Override public void onActivityDestroyed(Activity activity) {}
}

