package com.example.ijkplayer;

import android.app.Activity;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends Activity {
    IjkMediaPlayer ijkMediaPlayer;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("ijkplayer");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Example of a call to a native method
        SurfaceView surfaceView = findViewById(R.id.surface);
        ijkMediaPlayer = new IjkMediaPlayer();
        try {
            ijkMediaPlayer.setDataSource("http://cctvalih5ca.v.myalicdn.com/live/cctv1_2/index.m3u8");
            ijkMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        surfaceView.getHolder().setFormat(PixelFormat.RGBA_8888); // 使用RGBA_8888渲染视频(必须调用)
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                ijkMediaPlayer.setDisplay(surfaceHolder);
                ijkMediaPlayer.start();
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                ijkMediaPlayer.pause();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ijkMediaPlayer.release();
    }
}
