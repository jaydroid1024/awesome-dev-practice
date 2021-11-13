#include "com_jay_biz_android_handler_jni_NDKTools.h"

JNIEXPORT jstring JNICALL Java_com_jay_biz_1android_handler_jni_NDKTools_getStringFromNDK
  (JNIEnv *env, jobject obj){
     return (*env)->NewStringUTF(env,"Hellow World，这是 JNI 返回的字符串");
  }