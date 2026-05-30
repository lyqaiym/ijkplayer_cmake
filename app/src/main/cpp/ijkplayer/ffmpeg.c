#include "libavcodec/avcodec.h"
#include "libavutil/dict.h"

int test();

int main() {
    void avcodec_register_all(void);
    av_dict_set_int(0, 0, 0, 1);
    test();
    return 0;
}

int test() {
    return 0;
}