# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.

cmake_minimum_required(VERSION 3.4.1)

include_directories(${ijkpath}/ijkmedia/ijkj4a)
include_directories(${ijkpath}/ijkmedia/ijkj4a/j4a)
include_directories(${ijkpath}/ijkmedia/ijkj4a/j4a/class/java/nio)

add_library( # Sets the name of the library.
        ijkj4a

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
        ${ijkpath}/ijkmedia/ijkj4a/j4a/j4a_allclasses.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/j4a_base.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/android/media/AudioTrack.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/android/media/MediaCodec.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/android/media/MediaFormat.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/android/media/PlaybackParams.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/android/os/Build.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/android/os/Bundle.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/java/nio/Buffer.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/java/nio/ByteBuffer.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/java/util/ArrayList.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/tv/danmaku/ijk/media/player/misc/IMediaDataSource.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/tv/danmaku/ijk/media/player/misc/IAndroidIO.c
        ${ijkpath}/ijkmedia/ijkj4a/j4a/class/tv/danmaku/ijk/media/player/IjkMediaPlayer.c
        ${ijkpath}/ijkmedia/ijkj4a/j4au/class/android/media/AudioTrack.util.c
        ${ijkpath}/ijkmedia/ijkj4a/j4au/class/java/nio/ByteBuffer.util.c
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
        ijkj4a
                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )