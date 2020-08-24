package com.jay.biz_movie.data

import android.util.Log
import com.google.gson.reflect.TypeToken
import com.jay.base_component.app.BaseComponentApp
import com.jay.base_lib.utils.FileUtils
import com.jay.base_lib.utils.GsonUtils
import com.jay.biz_movie.entity.MovieItem

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2020/8/13
 */
object MovieDataHelper {
    private var cacheMovie: MutableList<MovieItem>? = null

    init {
        cacheMovie = getCacheMovie()
    }


    object MCUMovieName {
        const val THOR = "雷神"
        const val CAPTAIN_AMERICA_CIVIL_WAR = "美国队长3"
        const val GUARDIANS_OF_THE_GALAXY = "银河护卫队"
        const val ANT_MAN = "蚁人"
        const val THE_AVENGERS = "复仇者联盟"
        const val IRON_MAN = "钢铁侠"
        const val DOCTOR_STRANGE = "奇异博士"
        const val ANT_MAN_AND_THE_WASP = "蚁人2：黄蜂女现身"
        const val AVENGERS_ENDGAME = "复仇者联盟4"
        const val SPIDER_MAN_FAR_FROM_HOME = "蜘蛛侠：英雄远征"
        const val IRON_MAN_3 = "钢铁侠3"
        const val BLACK_PANTHER = "黑豹"
        const val AVENGERS_INFINITY_WAR = "复仇者联盟3：无限战争"
        const val SPIDER_MAN_HOMECOMING = "蜘蛛侠：英雄归来"
        const val THE_INCREDIBLE_HULK = "无敌浩克"
        const val CAPTAIN_MARVEL = "惊奇队长"
        const val THOR_THE_DARK_WORLD = "雷神2：黑暗世界"
        const val AVENGERS_AGE_OF_ULTRON = "复仇者联盟2：奥创纪元"
        const val GUARDIANS_OF_THE_GALAXY_VOL_2 = "银河护卫队2"
        const val IRON_MAN_2 = "钢铁侠2"
        const val CAPTAIN_AMERICA_THE_FIRST_AVENGER = "美国队长"
        const val THOR_RAGNAROK = "雷神3：诸神黄昏"
        const val CAPTAIN_AMERICA_THE_WINTER_SOLDIER = "美国队长2"
    }

    /**
     * 漫威电影宇宙系列电影
     * 前三个阶段合称为“无限传奇”（The Infinity Saga）
     * # 漫威电影宇宙（MCU）漫威电影宇宙（英语：Marvel Cinematic Universe，简称MCU）
     * 获取漫威宇宙电影的豆瓣 <name id>
     * 豆瓣电影详情地址：https://movie.douban.com/subject/{movie_id}/
     */
    fun getMarvelMCUMovieNameIdMap(): HashMap<String, String> {
        val nameIdMap = HashMap<String, String>()
        //第一阶段 https://zh.wikipedia.org/wiki/%E6%BC%AB%E5%A8%81%E9%9B%BB%E5%BD%B1%E5%AE%87%E5%AE%99%EF%BC%9A%E7%AC%AC%E4%B8%80%E9%9A%8E%E6%AE%B5
        nameIdMap[MCUMovieName.IRON_MAN] = "1432146"
        nameIdMap[MCUMovieName.THE_INCREDIBLE_HULK] = "1866475"
        nameIdMap[MCUMovieName.IRON_MAN_2] = "3066739"
        nameIdMap[MCUMovieName.THOR] = "1866471"
        nameIdMap[MCUMovieName.CAPTAIN_AMERICA_THE_FIRST_AVENGER] = "2138838"
        nameIdMap[MCUMovieName.THE_AVENGERS] = "1866479"
        //第二阶段 https://zh.wikipedia.org/wiki/%E6%BC%AB%E5%A8%81%E9%9B%BB%E5%BD%B1%E5%AE%87%E5%AE%99%EF%BC%9A%E7%AC%AC%E4%BA%8C%E9%9A%8E%E6%AE%B5
        nameIdMap[MCUMovieName.IRON_MAN_3] = "3231742"
        nameIdMap[MCUMovieName.THOR_THE_DARK_WORLD] = "6560058"
        nameIdMap[MCUMovieName.CAPTAIN_AMERICA_THE_WINTER_SOLDIER] = "6390823"
        nameIdMap[MCUMovieName.GUARDIANS_OF_THE_GALAXY] = "7065154"
        nameIdMap[MCUMovieName.AVENGERS_AGE_OF_ULTRON] = "10741834"
        nameIdMap[MCUMovieName.ANT_MAN] = "1866473"
        //第三阶段 https://zh.wikipedia.org/wiki/%E6%BC%AB%E5%A8%81%E9%9B%BB%E5%BD%B1%E5%AE%87%E5%AE%99%EF%BC%9A%E7%AC%AC%E4%B8%89%E9%9A%8E%E6%AE%B5
        nameIdMap[MCUMovieName.CAPTAIN_AMERICA_CIVIL_WAR] = "25820460"
        nameIdMap[MCUMovieName.DOCTOR_STRANGE] = "3025375"
        nameIdMap[MCUMovieName.GUARDIANS_OF_THE_GALAXY_VOL_2] = "25937854"
        nameIdMap[MCUMovieName.SPIDER_MAN_HOMECOMING] = "24753477"
        nameIdMap[MCUMovieName.THOR_RAGNAROK] = "25821634"
        nameIdMap[MCUMovieName.BLACK_PANTHER] = "6390825"
        nameIdMap[MCUMovieName.AVENGERS_INFINITY_WAR] = "24773958"
        nameIdMap[MCUMovieName.ANT_MAN_AND_THE_WASP] = "26636712"
        nameIdMap[MCUMovieName.CAPTAIN_MARVEL] = "26213252"
        nameIdMap[MCUMovieName.AVENGERS_ENDGAME] = "26100958"
        nameIdMap[MCUMovieName.SPIDER_MAN_FAR_FROM_HOME] = "26931786"
        return nameIdMap
    }

    fun getMarvelMCUMovieTimeOrderedList(movieType: String): ArrayList<MovieItem> {
        val nameIdMap = getMarvelMCUMovieNameIdMap()
        val mcuOrderedList = ArrayList<MovieItem>()


        when (movieType) {
            "1" -> {
                mcuOrderedList.add(MovieItem(headerTitle = "MCU第一阶段(共6部)"))

                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THE_INCREDIBLE_HULK]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN_2]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_THE_FIRST_AVENGER]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THE_AVENGERS]))
            }
            "2" -> {
                mcuOrderedList.add(MovieItem(headerTitle = "MCU第二阶段(共6部)"))

                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN_3]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR_THE_DARK_WORLD]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_THE_WINTER_SOLDIER]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.GUARDIANS_OF_THE_GALAXY]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_AGE_OF_ULTRON]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.ANT_MAN]))
            }
            "3" -> {
                mcuOrderedList.add(MovieItem(headerTitle = "MCU第三阶段(共11部)"))

                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_CIVIL_WAR]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.DOCTOR_STRANGE]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.GUARDIANS_OF_THE_GALAXY_VOL_2]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.SPIDER_MAN_HOMECOMING]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR_RAGNAROK]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.BLACK_PANTHER]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_INFINITY_WAR]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.ANT_MAN_AND_THE_WASP]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_MARVEL]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_ENDGAME]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.SPIDER_MAN_FAR_FROM_HOME]))
            }
            else -> {
                mcuOrderedList.add(MovieItem(headerTitle = "MCU第一阶段(共6部)"))

                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THE_INCREDIBLE_HULK]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN_2]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_THE_FIRST_AVENGER]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THE_AVENGERS]))
//
                mcuOrderedList.add(MovieItem(headerTitle = "MCU第二阶段(共6部)"))

                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN_3]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR_THE_DARK_WORLD]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_THE_WINTER_SOLDIER]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.GUARDIANS_OF_THE_GALAXY]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_AGE_OF_ULTRON]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.ANT_MAN]))
////
                mcuOrderedList.add(MovieItem(headerTitle = "MCU第三阶段(共11部)"))

                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_CIVIL_WAR]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.DOCTOR_STRANGE]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.GUARDIANS_OF_THE_GALAXY_VOL_2]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.SPIDER_MAN_HOMECOMING]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR_RAGNAROK]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.BLACK_PANTHER]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_INFINITY_WAR]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.ANT_MAN_AND_THE_WASP]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_MARVEL]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_ENDGAME]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.SPIDER_MAN_FAR_FROM_HOME]))
            }
        }
//        mcuOrderedList.add(MovieItem(headerTitle = "MCU第一阶段(共6部)"))
//
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THE_INCREDIBLE_HULK]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN_2]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_THE_FIRST_AVENGER]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THE_AVENGERS]))
////
//        mcuOrderedList.add(MovieItem(headerTitle = "MCU第二阶段(共6部)"))
//
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN_3]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR_THE_DARK_WORLD]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_THE_WINTER_SOLDIER]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.GUARDIANS_OF_THE_GALAXY]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_AGE_OF_ULTRON]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.ANT_MAN]))
//////
//        mcuOrderedList.add(MovieItem(headerTitle = "MCU第三阶段(共11部)"))
//
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_CIVIL_WAR]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.DOCTOR_STRANGE]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.GUARDIANS_OF_THE_GALAXY_VOL_2]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.SPIDER_MAN_HOMECOMING]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR_RAGNAROK]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.BLACK_PANTHER]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_INFINITY_WAR]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.ANT_MAN_AND_THE_WASP]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_MARVEL]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_ENDGAME]))
//        mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.SPIDER_MAN_FAR_FROM_HOME]))

//        mcuOrderedList.reverse()

        return mcuOrderedList
    }

    private fun findMCUMovieById(movieId: String?): MovieItem {
        cacheMovie?.forEach {
            if (it.id == movieId) {
                Log.d("okhttp", it.toString())
                return it
            }
        }
        return MovieItem()
    }

    /**
     * 初始化省市配置
     */
    private fun getCacheMovie(): MutableList<MovieItem>? {
        val string = FileUtils.readFileContent(BaseComponentApp.getApp(), "mcu_movie_23.json")
        val listType = object : TypeToken<List<MovieItem>>() {}.type
        val marvelMCUMovieList = GsonUtils.fromJson<MovieItem>(string, listType)
        Log.d("okhttp", marvelMCUMovieList.toString())
        return marvelMCUMovieList
    }
}