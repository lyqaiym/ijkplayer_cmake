# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

set(ijkpath ${CMAKE_CURRENT_SOURCE_DIR}/../ijkplayer)
message(STATUS "ijkpath=${ijkpath}")

set(pathname ".so")

set(ijkffmpegpath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/libijkffmpeg.so")
message(STATUS "ijkffmpegpath=${ijkffmpegpath}")

set(libavcodecpath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/libavcodec${pathname}")
message(STATUS "libavcodecpath=${libavcodecpath}")

set(libavdevicepath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/libavdevice${pathname}")
message(STATUS "libavcodecpath=${libavcodecpath}")

set(libavfilterpath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/libavfilter${pathname}")
message(STATUS "libavfilterpath=${libavfilterpath}")

set(libavformatpath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/libavformat${pathname}")
message(STATUS "libavformatpath=${libavformatpath}")

set(libavutilpath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/libavutil${pathname}")
message(STATUS "libavutilpath=${libavutilpath}")

set(libswresamplepath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/libswresample${pathname}")
message(STATUS "libswresamplepath=${libswresamplepath}")

set(libswscalepath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/libswscale${pathname}")
message(STATUS "libswscalepath=${libswscalepath}")

set(libcryptopath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/libcrypto.a")
message(STATUS "libswscalepath=${libcryptopath}")

set(libsslpath "${CMAKE_CURRENT_SOURCE_DIR}/ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/libssl.a")
message(STATUS "libswscalepath=${libsslpath}")

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