package com.example.ijkplayer;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
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
        final SurfaceView surfaceView = findViewById(R.id.surface);
        findViewById(R.id.bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ijkMediaPlayer == null) {
                    initMediaPlayer(surfaceView);
                    ijkMediaPlayer.setDisplay(surfaceView.getHolder());
                    return;
                }
                if (ijkMediaPlayer.isPlaying()) {
                    ijkMediaPlayer.pause();
                } else {
                    ijkMediaPlayer.start();
                }
            }
        });
        surfaceView.getHolder().setFormat(PixelFormat.RGBA_8888); // 使用RGBA_8888渲染视频(必须调用)
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
                if (ijkMediaPlayer != null) {
                    ijkMediaPlayer.setDisplay(surfaceHolder);
                    ijkMediaPlayer.start();
                }
            }

            @Override
            public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {
                if (ijkMediaPlayer != null) {
                    ijkMediaPlayer.pause();
                }
            }
        });
    }

    private void initMediaPlayer(final SurfaceView surfaceView) {
        ijkMediaPlayer = new IjkMediaPlayer();
        try {
            ijkMediaPlayer.setDataSource("http://cctvalih5ca.v.myalicdn.com/live/cctv1_2/index.m3u8");
            ijkMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ijkMediaPlayer.setOnPreparedListener(new IMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(IMediaPlayer mp) {
                int videoWidth = mp.getVideoWidth();
                int videoHeight = mp.getVideoHeight();
                int screenWidth = getScreenWidth(MainActivity.this);
                ViewGroup.LayoutParams layoutParams = surfaceView.getLayoutParams();
                layoutParams.width = screenWidth;
                layoutParams.height = screenWidth * videoHeight / videoWidth;
                surfaceView.setLayoutParams(layoutParams);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer.release();
        }
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context
                .WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
        return dm.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context
                .WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();// 创建了一张白纸
        windowManager.getDefaultDisplay().getMetrics(dm);// 给白纸设置宽高
        return dm.heightPixels;
    }
}
