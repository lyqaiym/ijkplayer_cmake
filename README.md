# ijkplayer_cmake
使用cmake编译ijkplayer

下载ijkplayer
https://github.com/bilibili/ijkplayer.git
放在app/src/main
在 app\src\main\ijkplayer\ijkmedia\ijkplayer 下面创建 ijkversion.h
添加
#ifndef IJK_VERSION_H
#define IJK_VERSION_H
#define IJKPLAYER_VERSION "1";
#endif

下载soundtouch
git clone --depth 10 -b ijk-r0.1.2-dev https://github.com/Bilibili/soundtouch.git ijksoundtouch
放在app/src/main/ijkplayer/ijkmedia

下载ijkplayer
git clone --depth 10 -b ijk-r0.2.1-dev https://github.com/Bilibili/libyuv.git
放在app/src/main/ijkplayer/ijkmedia