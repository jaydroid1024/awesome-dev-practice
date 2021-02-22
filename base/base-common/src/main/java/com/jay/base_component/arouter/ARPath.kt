package com.jay.base_component.arouter


/**
 * ARPath 帮助类
 * 路由路径
 * @author wangxuejie
 * @date 2019-12-17 14:04
 * @version 1.0
 */
object ARPath {


    /**
     * hide 组件
     */
    object PathHide {

        /**
         * group name
         */
        private const val HIDE = "/hide"

        /**
         * hide 服务
         */
        const val HIDE_SERVICE_PATH = "$HIDE/app_config/service"


    }

    /**
     * dev 主入口组件
     */
    object PathDev {

        /**
         * group name
         */
        private const val GROUP = "/dev"

        /**
         * 主页面
         */
        const val DEV_ACTIVITY_PATH = "$GROUP/dev/dev/activity"

        /**
         * 测试页面
         */
        const val TEST_ACTIVITY_PATH = "$GROUP/dev/test/activity"

    }

    /**
     * dev 主入口组件
     */
    object PathJava {

        /**
         * group name
         */
        private const val GROUP = "/java"

        /**
         * 主页面
         */
        const val JAVA_ACTIVITY_PATH = "$GROUP/java/main/activity"


    }


    /**
     * dev 电影组件
     */
    object PathMovie {

        /**
         * group name
         */
        private const val GROUP = "/movie"

        /**
         * 主页面
         */
        const val MOVIE_ACTIVITY_PATH = "$GROUP/movie/movie/activity"

        /**
         * 主页面
         */
        const val MOVIE_DETAIL_ACTIVITY_PATH = "$GROUP/movie/detail/activity"

        /**
         * 测试页面
         */
        const val DEMO_ACTIVITY_PATH = "$GROUP/movie/demo/activity"

    }

    /**
     * main组件
     */
    object PathMain {

        /**
         * group name
         */
        private const val MAIN = "/main"

        /**
         * MainActivity 页面
         */
        const val MAIN_ACTIVITY_PATH = "$MAIN/main/activity"

        /**
         * HomeFragment 页面
         */
        const val HOME_FRAGMENT_PATH = "$MAIN/home/fragment"


    }

    /**
     * Web 组件
     */
    object PathWeb {

        /**
         * group name
         */
        private const val GROUP = "/base_web"

        /**
         * 通用Web页面
         */
        const val WEB_ACTIVITY_PATH = "$GROUP/web/activity"

        /**
         * 通用Web 测试页面
         */
        const val WEB_TEST_ACTIVITY_PATH = "$GROUP/web/test/activity"

        /**
         * 通用office 文件查看
         */
        const val FILE_VIEW_ACTIVITY_PATH = "${GROUP}/file/activity"

        /**
         * 通用office 文件查看
         */
        const val FILE_VIEW_TEST_ACTIVITY_PATH = "${GROUP}/file/test/activity"


    }


    /**
     * user组件
     */
    object PathUser {

        /**
         * group name
         */
        private const val USER = "/login"

        /**
         * 登录页面
         */
        const val LOGIN_ACTIVITY_PATH = "$USER/login/activity"

        /**
         * 注册页面
         */
        const val REGISTER_ACTIVITY_PATH = "$USER/register/activity"

        /**
         * 用户服务
         */
        const val USER_SERVICE_PATH = "$USER/login/service"


    }

    /**
     * setting组件
     */
    object PathSetting {

        /**
         * group name
         */
        private const val SETTING = "/setting"

        /**
         * 设置页面
         */
        const val SETTING_ACTIVITY_PATH = "$SETTING/setting/activity"


    }

    /**
     * speech 组件
     */
    object PathSpeech {

        /**
         * group name
         */
        private const val GROUP = "/speech"

        /**
         * 文字转语音页面测试页面
         */
        const val MAIN_SPEECH_ACTIVITY_PATH = "$GROUP/main/test/speech/activity"

        /**
         *
         * 文字转语音页面页面
         */
        const val SPEECH_ACTIVITY_PATH = "$GROUP/speech/activity"


    }

    /**
     * favorite组件
     */
    object PathFavorite {

        /**
         * group name
         */
        private const val FAVORITE = "/favorite"

        /**
         * 收藏页面
         */
        const val FAVORITE_ACTIVITY_PATH = "$FAVORITE/favorite/activity"


    }

    /**
     * search组件
     */
    object PathSearch {

        /**
         * group name
         */
        private const val SEARCH = "/search"

        /**
         * 收藏页面
         */
        const val SEARCH_ACTIVITY_PATH = "$SEARCH/search/activity"


    }

    /**
     * detail组件
     */
    object PathDetail {

        /**
         * group name
         */
        private const val DETAIL = "/detail"

        /**
         * 文章详情页面
         */
        const val DETAIL_ACTIVITY_PATH = "$DETAIL/detail/activity"


    }

    /**
     * group 组件
     */
    object PathEnglish {

        /**
         * group name
         */
        private const val GROUP = "/english"

        /**
         * 英语页面主组件
         */
        const val ENGLISH_ACTIVITY_PATH = "$GROUP/en/main/activity"

        /**
         * 音标
         */
        const val PHONETIC_ACTIVITY_PATH = "$GROUP/en/phonetic/activity"

        /**
         * 音标列表
         */
        const val PHONETIC_LIST_ACTIVITY_PATH = "$GROUP/en/phonetic/list/activity"

        /**
         * 字幕
         */
        const val LETTER_ACTIVITY_PATH = "$GROUP/en/letter/activity"


    }

    /**
     * group 组件
     */
    object PathPattern {

        /**
         * group name
         */
        private const val GROUP = "/pattern"

        /**
         * 页面主组件
         */
        const val PATTERN_ACTIVITY_PATH = "${GROUP}/pattern/main/activity"

        /**
         * 列表
         */
        const val PATTERN_LIST_ACTIVITY_PATH = "${GROUP}/pattern/pattern/list/activity"


    }


    /**
     * group 组件
     */
    object PathCard {

        /**
         * group name
         */
        private const val GROUP = "/card"

        /**
         * 页面主组件
         */
        const val CARD_ACTIVITY_PATH = "${GROUP}/card/main/activity"

        /**
         * id
         */
        const val CARD_ID_ACTIVITY_PATH = "${GROUP}/card/id/activity"

        /**
         * 识别
         */
        const val CARD_RECOGNITION_ACTIVITY_PATH = "${GROUP}/card/recognition/activity"


    }


    /**
     * group 组件
     */
    object PathMap {

        /**
         * group name
         */
        private const val GROUP = "/map"


        /**
         * 组件主页面
         */
        const val MAP_ACTIVITY_PATH = "${GROUP}/map/main/activity"


        /**
         * 百度
         */
        const val BAIDU_MAP_ACTIVITY_PATH = "${GROUP}/map/baidu/activity"


        /**
         * 高德
         */
        const val GAODE_MAP_ACTIVITY_PATH = "${GROUP}/map/gaode/activity"


    }

    /**
     * group 组件
     */
    object PathMVVM {

        /**
         * group name
         */
        private const val GROUP = "/mvvp"


        /**
         * 组件主页面
         */
        const val MVVM_ACTIVITY_PATH = "${GROUP}/mvvm/main/activity"


        /**
         * 数据绑定页面
         */
        const val BIND_ACTIVITY_PATH = "${GROUP}/mvvm/databinding/activity"

    }

    /**
     * group 组件
     */
    object PathJetPack {

        /**
         * group name
         */
        private const val GROUP = "/jetpack"


        /**
         * 组件主页面
         */
        const val JET_PACK_MAIN_ACTIVITY_PATH = "${GROUP}/jetpack/main/activity"


        /**
         * 数据绑定页面
         */
        const val DATA_BINDING_ACTIVITY_PATH = "${GROUP}/jetpack/databinding/activity"

        /**
         * 数据绑定页面
         */
        const val DATA_BINDING_USE_ACTIVITY_PATH = "${GROUP}/jetpack/databinding/use/activity"

        /**
         * 数据绑定页面
         */
        const val DATA_BINDING_OBSERVABLE_ACTIVITY_PATH = "${GROUP}/jetpack/databinding/observable/activity"

        /**
         * 数据绑定页面
         */
        const val DATA_BINDING_VIEW_MODEL_ACTIVITY_PATH =
            "${GROUP}/jetpack/databinding/viewnodel/activity"

        /**
         * 数据绑定页面
         */
        const val DATA_BINDING_LIVE_DATA_ACTIVITY_PATH =
            "${GROUP}/jetpack/databinding/livedata/activity"


        /**
         * 依赖注入页面
         */
        const val HINT_ACTIVITY_PATH = "${GROUP}/jetpack/hint/activity"

        /**
         * 依赖注入页面
         */
        const val HINT_USE_ACTIVITY_PATH = "${GROUP}/jetpack/hint/use/activity"


        /**
         * Room 持久性库页面
         */
        const val ROOM_ACTIVITY_PATH = "${GROUP}/jetpack/room/activity"

        /**
         * Room 持久性库页面
         */
        const val ROOM_USE_ACTIVITY_PATH = "${GROUP}/jetpack/room/use/activity"


    }


    /**
     * kotlin 组件
     */
    object PathKotlin {

        /**
         * group name
         */
        private const val GROUP = "/kotlin"

        /**
         * 组件主页面
         */
        const val KOTLIN_MAIN_ACTIVITY_PATH = "${GROUP}/kotlin/main/activity"

        /**
         * 组件主页面
         */
        const val COROUTINE_MAIN_ACTIVITY_PATH = "${GROUP}/kotlin/coroutine/activity"

        /**
         * 组件主页面
         */
        const val COROUTINE_USE_ACTIVITY_PATH = "${GROUP}/kotlin/coroutine/use/activity"


    }


    /**
     * group 组件
     */
    object PathAndroid {

        /**
         * group name
         */
        private const val GROUP = "/android"

        /**
         * 组件主页面
         */
        const val ANDROID_MAIN_ACTIVITY_PATH = "${GROUP}/android/main/activity"

        /**
         * 组件主页面
         */
        const val TOUCH_EVENT_MAIN_ACTIVITY_PATH = "${GROUP}/android/touch_event/main/activity"

        /**
         * 组件主页面
         */
        const val TOUCH_EVENT_DISPATCH_ACTIVITY_PATH =
            "${GROUP}/android/touch_event/dispatch/activity"

    }


    /**
     * group 组件
     */
    object PathAlgorithm {

        /**
         * group name
         */
        private const val GROUP = "/algorithm"

        /**
         * 组件主页面
         */
        const val ALGORITHM_MAIN_ACTIVITY_PATH = "${GROUP}/algorithm/main/activity"


    }


}