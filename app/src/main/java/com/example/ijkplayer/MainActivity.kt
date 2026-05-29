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
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView;
import androidx.compose.runtime.*

import androidx.compose.ui.viewinterop.AndroidView
import com.example.ijkplayer.MainActivity.ScreenUtils.getScreenWidth

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
            Column(modifier = Modifier.fillMaxSize()) {
                VideoPlayer(
                    videoUrl = "https://www.w3schools.com/html/mov_bbb.mp4" // 测试视频
                )
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

fun setOnPreparedListener(ctx: Context, ijkMediaPlayer: IjkMediaPlayer, surfaceView: SurfaceView) {
    ijkMediaPlayer.setOnPreparedListener(object :
        IMediaPlayer.OnPreparedListener {
        override fun onPrepared(mp: IMediaPlayer) {
            val videoWidth = mp.videoWidth
            val videoHeight = mp.videoHeight
            val screenWidth = getScreenWidth(ctx)

            val layoutParams = surfaceView.layoutParams
            layoutParams.width = screenWidth
            layoutParams.height = screenWidth * videoHeight / videoWidth
            surfaceView.layoutParams = layoutParams
        }
    })
}

@Composable
fun VideoPlayer(
    videoUrl: String,
    modifier: Modifier = Modifier
) {
    // 1. 定义状态：默认 null，不初始化
    var ijkMediaPlayer: IjkMediaPlayer? by remember {
        mutableStateOf(null)
    }
    var sholder: SurfaceHolder? by remember {
        mutableStateOf(null)
    }
    var surfaceView: SurfaceView? by remember {
        mutableStateOf(null)
    }
    Box {
        AndroidView(factory = { ctx ->
            SurfaceView(ctx).apply {
                surfaceView = this
                holder.addCallback(object : SurfaceHolder.Callback {
                    override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
                        sholder = surfaceHolder;
                        ijkMediaPlayer?.setDisplay(surfaceHolder)
                        ijkMediaPlayer?.start()
                        ijkMediaPlayer?.let { setOnPreparedListener(ctx, it, this@apply) }
                    }

                    override fun surfaceChanged(
                        p0: SurfaceHolder,
                        p1: Int,
                        p2: Int,
                        p3: Int
                    ) {

                    }

                    override fun surfaceDestroyed(p0: SurfaceHolder) {
                        ijkMediaPlayer?.pause()
                    }

                })
            }
        })
        Row(modifier = Modifier.align(androidx.compose.ui.Alignment.BottomCenter)) {
            Button(onClick = {
                if (ijkMediaPlayer == null) {
                    ijkMediaPlayer = IjkMediaPlayer().apply {
                        try {
                            setDataSource(videoUrl)
                            prepareAsync()
                            setDisplay(sholder)
                            surfaceView?.let {
                                setOnPreparedListener(it.context, this, it)
                            }
                        } catch (e: IOException) {
                            e.printStackTrace();
                        }
                    }
                }
                ijkMediaPlayer?.start()
            }) {
                Text("开始")
            }
            Button(onClick = {
                ijkMediaPlayer?.pause()
            }) {
                Text("暂停")
            }
        }
    }
}
