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
         * 测试页面
         */
        const val TEST_ACTIVITY_PATH = "$GROUP/dev/test/activity"

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


    }


}