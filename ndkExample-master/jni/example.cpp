#include <jni.h>
#include <android/log.h>
#define __STDC_FORMAT_MACROS  // for format specifiers from inttypes.h
#include <inttypes.h>  // for format specifiers like PRId64

#define logV(...) \
	__android_log_print(ANDROID_LOG_VERBOSE, "example.cpp", __VA_ARGS__)

extern "C" {

JNIEXPORT jlong JNICALL Java_pl_morgwai_ndkexample_NativeExample_getExampleValue
		(JNIEnv *, jobject) {
	static jlong result = 0;
	result++;
	logV("getExampleValue: %" PRId64, result);
	return result;
}

}  // extern "C"
