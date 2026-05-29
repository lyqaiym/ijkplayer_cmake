package com.example.ijkplayer;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView;

import androidx.compose.ui.unit.dp

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

class MainActivity : ComponentActivity() {
    var ijkMediaPlayer: IjkMediaPlayer? = null;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Example of a call to a native method
        val surfaceView = findViewById<SurfaceView>(R.id.surface);
        val bt = findViewById<View>(R.id.bt)
        bt.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                if (ijkMediaPlayer == null) {
                    initMediaPlayer(surfaceView)
                    ijkMediaPlayer?.setDisplay(surfaceView.holder)
                }
                if (ijkMediaPlayer!!.isPlaying) {
                    ijkMediaPlayer!!.pause()
                } else {
                    ijkMediaPlayer!!.start()
                }
            }
        })
        surfaceView.getHolder().setFormat(PixelFormat.RGBA_8888); // 使用RGBA_8888渲染视频(必须调用)
        surfaceView.getHolder().addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
                if (ijkMediaPlayer != null) {
                    ijkMediaPlayer?.setDisplay(surfaceHolder);
                    ijkMediaPlayer?.start();
                }
            }

            override fun surfaceChanged(
                surfaceHolder: SurfaceHolder,
                i: Int,
                i1: Int,
                i2: Int
            ) {

            }

            override fun surfaceDestroyed(surfaceHolder: SurfaceHolder) {
                if (ijkMediaPlayer != null) {
                    ijkMediaPlayer?.pause();
                }
            }
        });
        // 1. 找到 XML 里的 ComposeView
        val composeView = findViewById<ComposeView>(R.id.compose_view);
        // 2. 设置 Compose 内容
        composeView.setContent {
            Button(onClick = { /* 点击事件 */ }) {
                Text("Compose 按钮")
            }
        }
    }

    private fun initMediaPlayer(surfaceView: SurfaceView) {
        ijkMediaPlayer = IjkMediaPlayer();
        try {
//            ijkMediaPlayer.setDataSource("https://test-streams.mux.dev/x36xhzz/x36xhzz.m3u8");
            ijkMediaPlayer?.setDataSource("https://www.w3schools.com/html/mov_bbb.mp4");
            ijkMediaPlayer?.prepareAsync();
        } catch (e: IOException) {
            e.printStackTrace();
        }
        ijkMediaPlayer?.setOnPreparedListener(object : IMediaPlayer.OnPreparedListener {
            override fun onPrepared(mp: IMediaPlayer) {
                val videoWidth = mp.videoWidth
                val videoHeight = mp.videoHeight
                val screenWidth = getScreenWidth(this@MainActivity)

                val layoutParams = surfaceView.layoutParams
                layoutParams.width = screenWidth
                layoutParams.height = screenWidth * videoHeight / videoWidth
                surfaceView.layoutParams = layoutParams
            }
        })
    }

    @Override
    override fun onDestroy() {
        super.onDestroy();
        if (ijkMediaPlayer != null) {
            ijkMediaPlayer?.release();
        }
    }

    companion object ScreenUtils {
        // 这个就是 Java 的 static{} ！！！
        init {
            // 类加载时自动执行一次
            System.loadLibrary("ijkplayer");
            Log.d("Player", "静态初始化")
        }

        fun getScreenWidth(context: Context): Int {
            val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val dm = DisplayMetrics()
            windowManager.defaultDisplay.getMetrics(dm)
            return dm.widthPixels
        }

        fun getScreenHeight(context: Context): Int {
            val windowManager = context.getSystemService(
                Context
                    .WINDOW_SERVICE
            ) as WindowManager
            val dm = DisplayMetrics();// 创建了一张白纸
            windowManager.defaultDisplay.getMetrics(dm);// 给白纸设置宽高
            return dm.heightPixels;
        }
    }
}
