# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

add_definitions(-D__GNUC__)
add_definitions(-D__lint__)
add_definitions(-DOPENSSL_SYS_BEOS)

include_directories(./cpu)
include_directories(${ijkpath}/ijkmedia)
#include_directories(${ijkpath}/extra/winpthread/include)
include_directories(${ijkpath}/ijkmedia/ijkj4a)
include_directories(${ijkpath}/ijkmedia/ijkplayer)
include_directories(${ijkpath}/ijkmedia/ijksdl)
include_directories(${ijkpath}/ijkmedia/extensions/dispatch_config)
include_directories(${ijkpath}/ijkmedia/ijkplayer/android/pipeline)
include_directories(${ijkffmpegincludepath})
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

# https://stackoverflow.com/questions/41554511/how-to-add-cpufeatures-to-android-jni-cmake-gradle-build
#include(AndroidNdkModules)
#android_ndk_import_module_cpufeatures()

set_target_properties(
        ijkj4a
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libijkj4a.so)

set_target_properties(
        ijksdl
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/liijksdl.so)

set_target_properties(
        soundtouch
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libsoundtouch.so)

set_target_properties(
        android-ndk-profiler
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libandroid-ndk-profiler.so)

add_library(ijkffmpeg SHARED IMPORTED)
set_target_properties(
        ijkffmpeg
        PROPERTIES IMPORTED_LOCATION
        ${ijkffmpegpath})

add_library( # Sets the name of the library.
        ijkplayer

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             cpu/cpu-features.c
        ${ijkpath}/ijkmedia/ijkplayer/ff_cmdutils.c
        ${ijkpath}/ijkmedia/ijkplayer/ff_ffplay.c
        ${ijkpath}/ijkmedia/ijkplayer/ff_ffpipeline.c
        ${ijkpath}/ijkmedia/ijkplayer/ff_ffpipenode.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkmeta.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkplayer.c

        ${ijkpath}/ijkmedia/ijkplayer/pipeline/ffpipeline_ffplay.c
        ${ijkpath}/ijkmedia/ijkplayer/pipeline/ffpipenode_ffplay_vdec.c
        ${ijkpath}/ijkmedia/ijkplayer/android/ffmpeg_api_jni.c
        ${ijkpath}/ijkmedia/ijkplayer/android/ijkplayer_android.c
        ${ijkpath}/ijkmedia/ijkplayer/android/ijkplayer_jni.c
        ${ijkpath}/ijkmedia/ijkplayer/android/pipeline/ffpipeline_android.c
        ${ijkpath}/ijkmedia/ijkplayer/android/pipeline/ffpipenode_android_mediacodec_vdec.c

        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/allformats.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/cJson.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijklas.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijklivehook.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijkmediadatasource.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijkio.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijkiomanager.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijkiocache.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijkioffio.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijkioandroidio.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijkioprotocol.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijkioapplication.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijkiourlhook.c

        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijkasync.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijkurlhook.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijklongurl.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavformat/ijksegment.c

        ${ijkpath}/ijkmedia/ijkplayer/ijkavutil/ijkdict.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavutil/ijkutils.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavutil/ijkthreadpool.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavutil/ijktree.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavutil/ijkfifo.c
        ${ijkpath}/ijkmedia/ijkplayer/ijkavutil/ijkstl.cpp
         )

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.

find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.

target_link_libraries( # Specifies the target library.
                       ijkplayer
        ijkffmpeg
        ijkj4a
        ijksdl
        soundtouch
        android-ndk-profiler
        android
        EGL
        GLESv2
        GLESv1_CM
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )