package com.jay.biz_movie.data

import android.util.Log
import com.google.gson.reflect.TypeToken
import com.jay.base_component.app.BaseComponentApp
import com.jay.base_lib.utils.FileUtils
import com.jay.base_lib.utils.GsonUtils
import com.jay.biz_movie.entity.ActorsItem
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
                mcuOrderedList.add(MovieItem(headerTitle = "无限传奇系列(共23部)"))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN]))
                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THE_INCREDIBLE_HULK]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN_2]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_THE_FIRST_AVENGER]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THE_AVENGERS]))
//
//                mcuOrderedList.add(MovieItem(headerTitle = "MCU第二阶段(共6部)"))
//
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.IRON_MAN_3]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR_THE_DARK_WORLD]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_THE_WINTER_SOLDIER]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.GUARDIANS_OF_THE_GALAXY]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_AGE_OF_ULTRON]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.ANT_MAN]))
//////
//                mcuOrderedList.add(MovieItem(headerTitle = "MCU第三阶段(共11部)"))
//
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_AMERICA_CIVIL_WAR]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.DOCTOR_STRANGE]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.GUARDIANS_OF_THE_GALAXY_VOL_2]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.SPIDER_MAN_HOMECOMING]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.THOR_RAGNAROK]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.BLACK_PANTHER]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_INFINITY_WAR]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.ANT_MAN_AND_THE_WASP]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.CAPTAIN_MARVEL]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.AVENGERS_ENDGAME]))
//                mcuOrderedList.add(findMCUMovieById(nameIdMap[MCUMovieName.SPIDER_MAN_FAR_FROM_HOME]))
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


    /**
     * “钢铁侠” 托尼·斯塔克 Iron Man / Tony Stark
    斯塔克工业的总裁，麻省理工学院荣誉校友，天才智商的军事发明家。自从他对公众揭示了其钢铁侠的英雄身份后，虽然变成了家喻户晓的大明星，但他也从此得到他人的挑战和质疑，就连体内的金属中毒也开始威胁到他的生命

    吉妮雅·“小辣椒”·波兹 Virginia "Pepper" Potts
    托尼的贴身秘书与女友，对托尼一直有着好感但始终不被他注意到，之后并被斯塔克任命为斯塔克工业的总裁。

    “战争机器”詹姆斯·“罗德”·罗德斯斯 War Machine / James "Rhodey" Rhodes
    美国空军军事采购联络人，也是托尼的好友，当年在麻省理工学院进行航空进修学时认识了托尼·斯塔克，是托尼遭受外界质疑时一直不离不弃的挚友。

    “鞭索” 伊凡·万科 Whiplash / Ivan Vanko
    前苏联核子物理学家安东·万科之子，本身也是个物理学家与机械工程天才，在父亲死后自行研发微型电弧反应器与动力服，准备利用它们对托尼·斯塔克进行报复。

    “黑寡妇” 娜塔莎·罗曼诺夫 Black Widow / Natasha Romanoff
    斯塔克工业法务部的新助理，但她也是秘密组织“神盾局”安排在斯塔克工业卧底的特务。被尼克·弗瑞局长任命成为托尼的监督者，并且负责评估他是否有资格参与复仇者计划。

    贾斯汀·汉默 Justin Hammer
    美国军方主要军火供应商“汉克默先进工业”的老板，在斯塔克工业退出军事工业界之后大发利市，为托尼的市场竞争对手。但与原著漫画不同的是，汉克默原本是个年纪比较大的军火商人。

    尼克·弗瑞 Nick Fury
    秘密组织“神盾局”局长，招募托尼加盟“复仇者”集结计划。

    哈罗德斯·“哈皮”·霍根 Harold "Happy" Hogan
    托尼的贴身保镖、司机与好友，拳击高手。除了参与演出外，乔恩·法夫罗实际上也是本片的导演

    霍华德·斯塔克 Howard Stark
    被誉为“军事工业之父”的伟大发明家，托尼的父亲，于托尼青年时期就去世。

    菲尔·考森 Phil Coulson
    神盾局资深特工，从很早开始就在观察托尼·斯塔克，奉命协助他摆脱闯下的祸。

     */
    fun getActorsList(): MutableList<ActorsItem>? {
        val acts = mutableListOf<ActorsItem>()
        val act_1 = ActorsItem()
        act_1.role_image = "https://img1.doubanio.com/view/photo/large/public/p443421887.webp"
        act_1.role_nickname = "钢铁侠 / Iron Man"
        act_1.role_name = "托尼·斯塔克 / Tony Stark"
        act_1.role_descr =
            "斯塔克工业的总裁，麻省理工学院荣誉校友，天才智商的军事发明家。自从他对公众揭示了其钢铁侠的英雄身份后，虽然变成了家喻户晓的大明星，但他也从此得到他人的挑战和质疑，就连体内的金属中毒也开始威胁到他的生命。"
        acts.add(act_1)
        val act_2 = ActorsItem()
        act_2.role_image = "https://img1.doubanio.com/view/photo/large/public/p443422108.webp"
        act_2.role_nickname = "小辣椒 / Pepper"
        act_2.role_name = "维吉尼亚·佩珀·波兹 / Virginia Pepper Potts"
        act_2.role_descr = "托尼的贴身秘书与女友，对托尼一直有着好感但始终不被他注意到，之后并被斯塔克任命为斯塔克工业的总裁。"
        acts.add(act_2)
        val act_3 = ActorsItem()
        act_3.role_image = "https://img1.doubanio.com/view/photo/large/public/p443421959.webp"
        act_3.role_nickname = "战争机器 / War Machine"
        act_3.role_name = "詹姆斯·罗德 / James Rhodes"
        act_3.role_descr = "国海军陆战队上校，他是托尼少数几个值得信赖的朋友，当年在麻省理工学院进行航空进修学时认识了托尼·斯塔克"
        acts.add(act_3)
        val act_4 = ActorsItem()
        act_4.role_image = "https://img9.doubanio.com/view/photo/large/public/p474866986.webp"
        act_4.role_nickname = "鞭索 / Whiplash"
        act_4.role_name = "伊凡·万科 / Ivan Vanko"
        act_4.role_descr = "前苏联核子物理学家安东·万科之子，本身也是个物理学家与机械工程天才，在父亲死后自行研发了方舟核反应堆与鞭索，准备利用它们对托尼·斯塔克进行报复。"
        acts.add(act_4)
        val act_5 = ActorsItem()
        act_5.role_image = "https://img1.doubanio.com/view/photo/large/public/p1984022149.webp"
        act_5.role_nickname = "黑寡妇 / Black Widow"
        act_5.role_name = "娜塔莎·罗曼诺夫 / Natasha Romanoff"
        act_5.role_descr =
            "斯塔克工业法务部的新助理，但她也是秘密组织“神盾局”安排在斯塔克工业卧底的特务。被尼克·弗瑞局长任命成为托尼的监督者，并且负责评估他是否有资格参与复仇者计划。"
        acts.add(act_5)
        val act_6 = ActorsItem()
        act_6.role_image = "https://img1.doubanio.com/view/photo/large/public/p469174877.webp"
        act_6.role_nickname = "阿默"
        act_6.role_name = "贾斯汀·汉默 / Justin Hammer"
        act_6.role_descr =
            "美国军方主要军火供应商“汉默先进工业”的老板，在斯塔克工业退出军事工业界之后大发利市，为托尼的市场竞争对手。但与原著漫画不同的是，汉克默原本是个年纪比较大的军火商人。"
        acts.add(act_6)
        val act_7 = ActorsItem()
        act_7.role_image = "https://img1.doubanio.com/view/photo/large/public/p469172679.webp"
        act_7.role_nickname = "瑞局"
        act_7.role_name = "尼克·弗瑞 / Nick Fury"
        act_7.role_descr = "秘密组织“神盾局”局长，招募托尼加盟“复仇者”联盟计划。"
        acts.add(act_7)
        val act_8 = ActorsItem()
        act_8.role_image = "https://img2.doubanio.com/view/photo/large/public/p469175843.webp"
        act_8.role_nickname = "皮皮哈"
        act_8.role_name = "哈皮·霍根 / Happy Hogan"
        act_8.role_descr = "托尼的贴身保镖、司机与好友，拳击高手。除了参与演出外，乔恩·法夫罗实际上也是本片的导演"
        acts.add(act_8)
        val act_9 = ActorsItem()
        act_9.role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gj5mqmh4cgj31xn0u04qq.jpg"
        act_9.role_nickname = "德叔"
        act_9.role_name = "霍华德·斯塔克 / Howard Stark"
        act_9.role_descr = "被誉为“军事工业之父”的伟大发明家，托尼的父亲，于托尼青年时期就去世。"
        acts.add(act_9)
        val act_10 = ActorsItem()
        act_10.role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gj5mr8cn4sj31x10u07wj.jpg"
        act_10.role_nickname = "森哥"
        act_10.role_name = "菲尔·考森 / Phil Coulson"
        act_10.role_descr = "神盾局资深特工，从很早开始就在观察托尼·斯塔克，奉命协助他摆脱闯下的祸。"
        acts.add(act_10)
        return acts
    }


}