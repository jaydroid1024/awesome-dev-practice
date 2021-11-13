LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE    := NDKTools-jni

LOCAL_SRC_FILES := NDKTools.c

include $(BUILD_SHARED_LIBRARY)

#LOCAL_PATH := $(call my-dir)：每个Android.mk文件必须以定义开始。它用于在开发tree中查找源文件。宏my-dir则由Build System 提供。返回包含Android.mk目录路径。
 #include $(CLEAR_VARS) ：CLEAR_VARS变量由Build System提供。并指向一个指定的GNU Makefile，由它负责清理很多LOCAL_xxx。例如LOCAL_MODULE，LOCAL_SRC_FILES，LOCAL_STATIC_LIBRARIES等等。但不是清理LOCAL_PATH。这个清理是必须的，因为所有的编译控制文件由同一个GNU Make解析和执行，其变量是全局的。所以清理后才能便面相互影响。
 #LOCAL_MODULE := ndkdemotest-jni：LOCAL_MODULE模块必须定义，以表示Android.mk中的每一个模块。名字必须唯一且不包含空格。Build System 会自动添加适当的前缀和后缀。例如，demo，要生成动态库，则生成libdemo.so。但请注意：如果模块名字被定义为libabd，则生成libabc.so。不再添加前缀。
 #LOCAL_SRC_FILES := ndkdemotest.c：这行代码表示将要打包的C/C++源码。不必列出头文件，build System 会自动帮我们找出依赖文件。缺省的C++ 源码的扩展名为.cpp。
 #include $(BUILD_SHARED_LIBRARY)：BUILD_SHARED_LIBRARY是Build System提供的一个变量，指向一个GUN Makefile Script。它负责收集自从上次调用include $(CLEAR_VARS)后的所有LOCAL_xxxxinx。并决定编译什么类型
 #BUILD_STATIC_LIBRARY：编译为静态库
 #BUILD_SHARED_LIBRARY：编译为动态库
 #BUILD_EXECUTABLE：编译为Native C 可执行程序
 #BUILD_PREBUILT：该模块已经预先编译
 #
 #作者：隔壁老李头
 #链接：https://www.jianshu.com/p/b4431ac22ec2
 #来源：简书
 #著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。