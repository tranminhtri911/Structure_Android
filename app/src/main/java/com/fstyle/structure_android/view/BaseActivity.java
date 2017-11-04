package com.fstyle.structure_android.view;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by le.quang.dao on 10/03/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void onStarted();

    protected abstract void onStopped();

    @Override
    protected void onStart() {
        super.onStart();
        onStarted();
    }

    @Override
    protected void onStop() {
        onStopped();
        super.onStop();
    }
}
