# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

add_definitions(-DLIBYUV_DISABLE_NEON)
include_directories(${ijkpath}/ijkmedia/libyuv/include)
include_directories(${ijkpath}/ijkmedia/libyuv/include)

add_library( # Sets the name of the library.
        libyuv

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
        ${ijkpath}/ijkmedia/libyuv/source/compare.cc
        ${ijkpath}/ijkmedia/libyuv/source/compare_common.cc
        ${ijkpath}/ijkmedia/libyuv/source/compare_posix.cc
        ${ijkpath}/ijkmedia/libyuv/source/convert.cc
        ${ijkpath}/ijkmedia/libyuv/source/convert_argb.cc
        ${ijkpath}/ijkmedia/libyuv/source/convert_from.cc
        ${ijkpath}/ijkmedia/libyuv/source/convert_from_argb.cc
        ${ijkpath}/ijkmedia/libyuv/source/convert_to_argb.cc
        ${ijkpath}/ijkmedia/libyuv/source/convert_to_i420.cc
        ${ijkpath}/ijkmedia/libyuv/source/cpu_id.cc
        ${ijkpath}/ijkmedia/libyuv/source/format_conversion.cc
        ${ijkpath}/ijkmedia/libyuv/source/planar_functions.cc
        ${ijkpath}/ijkmedia/libyuv/source/rotate.cc
        ${ijkpath}/ijkmedia/libyuv/source/rotate_argb.cc
        ${ijkpath}/ijkmedia/libyuv/source/rotate_mips.cc
        ${ijkpath}/ijkmedia/libyuv/source/row_any.cc
        ${ijkpath}/ijkmedia/libyuv/source/row_common.cc
        ${ijkpath}/ijkmedia/libyuv/source/row_mips.cc
        ${ijkpath}/ijkmedia/libyuv/source/row_posix.cc
        ${ijkpath}/ijkmedia/libyuv/source/scale.cc
        ${ijkpath}/ijkmedia/libyuv/source/scale_argb.cc
        ${ijkpath}/ijkmedia/libyuv/source/scale_common.cc
        ${ijkpath}/ijkmedia/libyuv/source/scale_mips.cc
        ${ijkpath}/ijkmedia/libyuv/source/scale_posix.cc
        ${ijkpath}/ijkmedia/libyuv/source/video_common.cc

        ${ijkpath}/ijkmedia/libyuv/source/compare_neon.cc
        ${ijkpath}/ijkmedia/libyuv/source/rotate_neon.cc
        ${ijkpath}/ijkmedia/libyuv/source/row_neon.cc
        ${ijkpath}/ijkmedia/libyuv/source/scale_neon.cc
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
        libyuv
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )