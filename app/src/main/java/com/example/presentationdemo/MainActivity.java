package com.example.presentationdemo;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Presentation;
import android.content.Context;
import android.hardware.display.DisplayManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener, SurfaceHolder.Callback {

    private static final String TAG = "Bradlley";
    private DifferentDislay mPresentation;
    private MediaPlayer mMediaPlayer;
    private SurfaceView mSurfaceView;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayManager manager = (DisplayManager) getSystemService(Context.DISPLAY_SERVICE);
        Display[] displays = manager.getDisplays();
        // displays[0] 主屏
        // displays[1] 副屏

        //开启副屏
        DifferentDislay differentDislay = new DifferentDislay(this,displays[1]);//displays[1]是副屏
        differentDislay.getWindow().setType(
                WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        differentDislay.show();

        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setOnPreparedListener(this);

        mSurfaceView= differentDislay.getSurfaceView();
        mSurfaceView.getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d(TAG, "surfaceCreated: ");
        try {
            mMediaPlayer.setDataSource("/sdcard/Movies/mv.wmv");
            mMediaPlayer.setDisplay(mSurfaceView.getHolder());
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mMediaPlayer.start();
    }
}
