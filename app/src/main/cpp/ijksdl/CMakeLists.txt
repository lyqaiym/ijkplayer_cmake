# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

include_directories(${ijkpath}/ijkmedia)
include_directories(${ijkpath}/ijkmedia/ijkj4a)
include_directories(${ijkpath}/ijkmedia/libyuv/include)
include_directories(${ijkpath}/ijkmedia/ijkplayer)
include_directories(${ijkffmpegincludepath})
#include_directories(${ijkpath}/ijkmedia/ijkplayer/android/pipeline)
# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.

set_target_properties(
        ijkj4a
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/libijkj4a.so)

set_target_properties(
        libyuv
        PROPERTIES IMPORTED_LOCATION
        ${CMAKE_LIBRARY_OUTPUT_DIRECTORY}/liblibyuv.so)

add_library(ijkffmpeg SHARED IMPORTED)
set_target_properties(
        ijkffmpeg
        PROPERTIES IMPORTED_LOCATION
        ${ijkffmpegpath})

add_library( # Sets the name of the library.
        ijksdl

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
        ${ijkpath}/ijkmedia/ijksdl/ijksdl_aout.c
        ${ijkpath}/ijkmedia/ijksdl/ijksdl_audio.c
        ${ijkpath}/ijkmedia/ijksdl/ijksdl_egl.c
        ${ijkpath}/ijkmedia/ijksdl/ijksdl_error.c
        ${ijkpath}/ijkmedia/ijksdl/ijksdl_mutex.c
        ${ijkpath}/ijkmedia/ijksdl/ijksdl_stdinc.c
        ${ijkpath}/ijkmedia/ijksdl/ijksdl_thread.c
        ${ijkpath}/ijkmedia/ijksdl/ijksdl_timer.c
        ${ijkpath}/ijkmedia/ijksdl/ijksdl_vout.c
        ${ijkpath}/ijkmedia/ijksdl/ijksdl_extra_log.c
        ${ijkpath}/ijkmedia/ijksdl/gles2/color.c
        ${ijkpath}/ijkmedia/ijksdl/gles2/common.c
        ${ijkpath}/ijkmedia/ijksdl/gles2/renderer.c
        ${ijkpath}/ijkmedia/ijksdl/gles2/renderer_rgb.c
        ${ijkpath}/ijkmedia/ijksdl/gles2/renderer_yuv420p.c
        ${ijkpath}/ijkmedia/ijksdl/gles2/renderer_yuv444p10le.c
        ${ijkpath}/ijkmedia/ijksdl/gles2/shader.c
        ${ijkpath}/ijkmedia/ijksdl/gles2/fsh/rgb.fsh.c
        ${ijkpath}/ijkmedia/ijksdl/gles2/fsh/yuv420p.fsh.c
        ${ijkpath}/ijkmedia/ijksdl/gles2/fsh/yuv444p10le.fsh.c
        ${ijkpath}/ijkmedia/ijksdl/gles2/vsh/mvp.vsh.c
        ${ijkpath}/ijkmedia/ijksdl/dummy/ijksdl_vout_dummy.c
        ${ijkpath}/ijkmedia/ijksdl/ffmpeg/ijksdl_vout_overlay_ffmpeg.c
        ${ijkpath}/ijkmedia/ijksdl/ffmpeg/abi_all/image_convert.c
        ${ijkpath}/ijkmedia/ijksdl/android/android_audiotrack.c
        ${ijkpath}/ijkmedia/ijksdl/android/android_nativewindow.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_android_jni.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_aout_android_audiotrack.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_aout_android_opensles.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_codec_android_mediacodec_dummy.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_codec_android_mediacodec_internal.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_codec_android_mediacodec_java.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_codec_android_mediacodec.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_codec_android_mediadef.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_codec_android_mediaformat_java.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_codec_android_mediaformat.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_vout_android_nativewindow.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_vout_android_surface.c
        ${ijkpath}/ijkmedia/ijksdl/android/ijksdl_vout_overlay_android_mediacodec.c
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
        ijksdl
        ijkj4a
        libyuv
        ijkffmpeg
        android
        OpenSLES
        EGL
        GLESv2
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )