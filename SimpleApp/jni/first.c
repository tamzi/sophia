#include <jni.h>
#include "calculate.c"
jint
Java_com_simple_Hym1_Compute( JNIEnv*  env,
                                      jobject  this,
                                     jint     y,
                                     jint     x )
{
    return calculate(y,x);
}
