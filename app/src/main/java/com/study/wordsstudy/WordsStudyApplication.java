package com.study.wordsstudy;

import android.app.Application;

/**
 * Created by vmakhitko on 04.06.16.
 */

public class WordsStudyApplication extends Application {
    private static String TAG = WordsStudyApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
