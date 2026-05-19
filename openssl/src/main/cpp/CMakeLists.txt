cmake_minimum_required(VERSION 3.4.1)
project(sliver)

enable_language(ASM)

set(BYTEHOOK_BASE ${CMAKE_CURRENT_SOURCE_DIR}/../../../../bytehook)
if(${CMAKE_BUILD_TYPE} STREQUAL "Debug")
    set(BYTEHOOK_LIB_BASE ${BYTEHOOK_BASE}/build/intermediates/cmake/debug/obj)
else()
    set(BYTEHOOK_LIB_BASE ${BYTEHOOK_BASE}/build/intermediates/cmake/release/obj)
endif()

include_directories(include)
include_directories(${BYTEHOOK_BASE}/bytehook/src/main/cpp)
include_directories(${BYTEHOOK_BASE}/bytehook/src/main/cpp/include)
include_directories(${BYTEHOOK_BASE}/bytehook/src/main/cpp/third_party/lss)
include_directories(${BYTEHOOK_BASE}/bytehook/src/main/cpp/third_party/bsd)

add_library(sliver SHARED ${SRC} sliver/sliver.c

        )
target_compile_features(sliver PUBLIC c_std_11)
target_compile_options(sliver PUBLIC -Wunused-function -Wunused-private-field)
#target_include_directories(sliver PUBLIC . include ${BYTEHOOK_BASE}/bytehook/src/main/cpp/include)
target_link_libraries(sliver log)

