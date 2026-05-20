cmake_minimum_required(VERSION 3.4.1)
project(ffmpeg)

set(pathname ".so")

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


add_library(libavcodec STATIC IMPORTED)
set_target_properties(
        libavcodec
        PROPERTIES IMPORTED_LOCATION
        ${libavcodecpath})

add_library(libavdevice STATIC IMPORTED)
set_target_properties(
        libavdevice
        PROPERTIES IMPORTED_LOCATION
        ${libavdevicepath})

add_library(libavfilter STATIC IMPORTED)
set_target_properties(
        libavfilter
        PROPERTIES IMPORTED_LOCATION
        ${libavfilterpath})

add_library(libavformat STATIC IMPORTED)
set_target_properties(
        libavformat
        PROPERTIES IMPORTED_LOCATION
        ${libavformatpath})

add_library(libavutil STATIC IMPORTED)
set_target_properties(
        libavutil
        PROPERTIES IMPORTED_LOCATION
        ${libavutilpath})

add_library(libswresample STATIC IMPORTED)
set_target_properties(
        libswresample
        PROPERTIES IMPORTED_LOCATION
        ${libswresamplepath})

add_library(libswscale STATIC IMPORTED)
set_target_properties(
        libswscale
        PROPERTIES IMPORTED_LOCATION
        ${libswscalepath})

add_library(libcrypto STATIC IMPORTED)
set_target_properties(
        libcrypto
        PROPERTIES IMPORTED_LOCATION
        ${libcryptopath})

add_library(libssl STATIC IMPORTED)
set_target_properties(
        libssl
        PROPERTIES IMPORTED_LOCATION
        ${libsslpath})

include_directories(include)
include_directories(ffmpeg/${CMAKE_ANDROID_ARCH_ABI}/include)

add_library(ijkffmpeg SHARED ${SRC} ffmpeg.c

        )
#target_compile_features(ijkffmpeg PUBLIC c_std_11)
#target_compile_options(ijkffmpeg PUBLIC -Wunused-function -Wunused-private-field)
#target_include_directories(sliver PUBLIC . include ${BYTEHOOK_BASE}/bytehook/src/main/cpp/include)
target_link_libraries(ijkffmpeg
        libavcodec
        libavdevice
        libavfilter
        libavformat
        libavutil
        libswresample
        libswscale
        libcrypto
        libssl
        log)

