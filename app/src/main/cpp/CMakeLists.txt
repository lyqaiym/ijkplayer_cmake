# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(ijkpath ${CMAKE_CURRENT_SOURCE_DIR}/../ijkplayer)
message(STATUS "ijkpath=${ijkpath}")

set(ijkffmpegpath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/libijkffmpeg.so")
message(STATUS "ijkffmpegpath=${ijkffmpegpath}")

set(ijkffmpegincludepath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/include")
message(STATUS "ijkffmpegincludepath=${ijkffmpegincludepath}")
#include_directories(${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/include)

ADD_SUBDIRECTORY(./ijksoundtouch)
ADD_SUBDIRECTORY(./libyuv)
ADD_SUBDIRECTORY(./android-ndk-profiler)
ADD_SUBDIRECTORY(./ijkj4a)
ADD_SUBDIRECTORY(./ijksdl)
ADD_SUBDIRECTORY(./ijkplayer)
#ADD_SUBDIRECTORY(./ffmpeg)