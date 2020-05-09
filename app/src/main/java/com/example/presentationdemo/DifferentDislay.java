package com.example.presentationdemo;

import android.app.Presentation;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceView;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
public class DifferentDislay  extends Presentation {
    private static final String TAG = "DifferentDislay";
    private SurfaceView mSurfaceView;

    public DifferentDislay(Context outerContext, Display display) {
        super(outerContext,display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.second_screen);
        mSurfaceView = (SurfaceView) findViewById(R.id.surfaceView);

    }

    public SurfaceView getSurfaceView(){
        return mSurfaceView;
    }
}
