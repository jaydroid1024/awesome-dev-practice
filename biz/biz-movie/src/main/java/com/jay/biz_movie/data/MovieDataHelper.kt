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
     * 角色介绍
     */
    fun getActorsList(title: String?): MutableList<ActorsItem>? {
        val acts = mutableListOf<ActorsItem>()
        when (title) {
            MovieConstants.RED.建国大业 -> {
                getRed01Banner(acts)
            }
            MovieConstants.RED.建党伟业 -> {
                getRed01Banner(acts)
            }
            MovieConstants.MCU.钢铁侠2 -> {
                getIronMan2Banner(acts)
            }
            else -> {
                getIronMan2Banner(acts)
            }
        }


        return acts
    }

    /**
     * 红色三部曲03-建军大业
     */
    private fun getRed03Banner(acts: MutableList<ActorsItem>) {


    }

    /**
     * 建党伟业
     * 红色三部曲02-建党伟业
     */
    private fun getRed02Banner(acts: MutableList<ActorsItem>) {


    }


    /**
     * 红色三部曲01-建国大业
     * 众多一线明星友情客串零片酬出演
     * 开创了新时期主旋律影视题材的先河
     */
    private fun getRed01Banner(acts: MutableList<ActorsItem>) {
        acts.add(
            ActorsItem(
                actor_name = "唐国强 Guoqiang Tang",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1508739235.25.webp",
                role_name = "毛泽东",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja22dympxj31420ly4qp.jpg",
                role_descr = "中国人民的领袖，伟大的马克思主义者，无产阶级革命家、战略家和理论家，中国共产党、中国人民解放军和中华人民共和国的主要缔造者和领导人，军事家，诗人，书法家"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "张国立 Guoli Zhang",
                actor_image = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1472014969.9.webp",
                role_name = "蒋介石",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja257p64tj31ke0u0x6p.jpg",
                role_descr = "近代中国著名政治人物及军事家，历任黄埔军校校长、国民革命军总司令、国民政府主席、行政院院长、国民政府军事委员会委员长、中华民国特级上将、中国国民党总裁、三民主义青年团团长、第二次世界大战同盟国中国战区最高统帅、中华民国总统等职。"
            )
        )


        acts.add(
            ActorsItem(
                actor_name = "许晴 Qing Xu",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1485778268.65.webp",
                role_name = "宋庆龄",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja9cvlfapj31sa0tqu0x.jpg",
                role_descr = "伟大的爱国主义、民主主义、国际主义和共产主义战士，举世闻名的二十世纪的伟大女性"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "刘劲 Jin Liu",
                actor_image = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p24407.webp",
                role_name = "周恩来",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjab65n4ipj31bu0rke81.jpg",
                role_descr = "伟大的马克思主义者，伟大的无产阶级革命家、政治家、军事家、外交家，党和国家主要领导人之一，中国人民解放军主要创建人之一，中华人民共和国的开国元勋，是以毛泽东同志为核心的党的第一代中央领导集体的重要成员"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "陈坤 Kun Chen",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p42814.webp",
                role_name = "蒋经国",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja2nkrd3hj31il0u0hdt.jpg",
                role_descr = "蒋经国是蒋介石之子，任经济管制委员会委员，督导上海经济"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "王学圻 Xueqi Wang",
                actor_image = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p797.webp",
                role_name = "李宗仁",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja9iyejf1j31hr0u0qv5.jpg",
                role_descr = "中华民国陆军一级上将，中国国民党党员。新桂系首领，曾任首任中华民国副总统、代总统。"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "李连杰 Jet Li",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p48974.webp",
                role_name = "陈绍宽",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjacamhdo0j31oe0u01ky.jpg",
                role_descr = "中华民国海军一级上将，国民政府时期海军部部长、海军总司令。"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "孙红雷 Honglei Sun",
                actor_image = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1560494719.79.webp",
                role_name = "中央日报记者",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjabzxk36hj31tk0tuhdu.jpg",
                role_descr = "胡立伟（《中央日报》记者，虚构人物）"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "尤勇 Yong You",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p14604.webp",
                role_name = "白崇禧",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjaa95dcoyj31ni0u01ky.jpg",
                role_descr = "中华民国陆军一级上将，有“小诸葛”之称。中国国民党桂系（新桂系）将领。地位仅次于李宗仁。1923年起任广西讨逆军参谋长，广西绥靖公署及桂军第二军参谋长，国民革命军副总参谋长、东路军前敌总指挥。保定军官学校毕业。"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "陈凯歌 Kaige Chen",
                actor_image = "https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1451727734.81.webp",
                role_name = "冯玉祥",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjacdkg364j31je0u0hdt.jpg",
                role_descr = "民革常务委员和政治委员会主席。1948年7月回国参加新政协会议筹备工作，9月1日因轮船失火遇难。冯玉祥是蒋介石的结拜兄弟，系国民政府青天白日勋章、美国总统二战银质自由勋章、国民政府首批抗战胜利勋章三大抗战勋章获得者。"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "刘烨 Ye Liu",
                actor_image = "https://img2.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1569318007.83.webp",
                role_name = "老红军战士",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjaawxkbyhj31rm0tyx6p.jpg",
                role_descr = "红军老战士，在西苑机场阅兵时作为红军战士代表向毛泽东敬礼"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "黄圣依 Eva Huang",
                actor_image = "https://img2.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1592125371.43.webp",
                role_name = "女播音员",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja9epe3vcj31ou0tyqv5.jpg",
                role_descr = "新华广播电台播音员"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "黎明 Leon Lai",
                actor_image = "https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1359950838.0.webp",
                role_name = "蔡廷锴",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja9ghteoij31pz0u0x6p.jpg",
                role_descr = "中华民国和中华人民共和国政治家。因指挥了一·二八淞沪抗战及参与领导发起了福建事变而海内外享有极高声誉。"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "陈好 Hao Chen",
                actor_image = "https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p7252.webp",
                role_name = "傅冬菊",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja9anflvkj31gw0tcu0x.jpg",
                role_descr = "傅作义的女儿"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "黄晓明 Xiaoming Huang",
                actor_image = "https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1472787652.32.webp",
                role_name = "李银桥",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja9qh2xu1j31r40u0e82.jpg",
                role_descr = "毛泽东的卫士长"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "范伟 Wei Fan",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1511362485.44.webp",
                role_name = "郭本财",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja2c0oj7mj31au0ty7wh.jpg",
                role_descr = "毛泽东的厨师，虚构人物"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "张涵予 Hanyu Zhang",
                actor_image = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p49047.webp",
                role_name = "刘从文",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja297xgzvj31fu0tu1kx.jpg",
                role_descr = "潜伏在毛泽东身边的军统间谍"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "姜文 Wen Jiang",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1517818343.94.webp",
                role_name = "毛人凤",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja9lk5iqyj31it0u0npd.jpg",
                role_descr = "中华民国陆军上将，军统局局长。"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "徐帆 Fan Xu",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p43516.webp",
                role_name = "廖梦醒",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja9wiel8xj31e00tyu0x.jpg",
                role_descr = "廖仲恺和何香凝的女儿，中共地下党员"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "冯小刚 Xiaogang Feng",
                actor_image = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p45667.webp",
                role_name = "杜月笙",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gja2ost8a7j31qo0u0qv5.jpg",
                role_descr = "近代中国绿林、上海租界青帮中著名的人物，国民革命军少将，“上海青帮三大亨”，素有“黄金荣贪财，张啸林善打，杜月笙会做人”的说法。1931年江淮水灾时，赈灾实际由杜月笙在背后向上海工商界打关系、募款、并在上海发起戏曲义演、赛马募捐，其募款和个人捐款累计53万元，占国民政府水灾救济委员会所募的20.3%，“为水灾做出的贡献无人能出其右”"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "成龙 Jackie Chan",
                actor_image = "https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542339922.02.webp",
                role_name = "记者",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjaa4rdtumj31e30u0kjl.jpg",
                role_descr = "采访李济深的记者（扮演的角色是电影中唯一说粤语的）"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "佟大为 Dawei Tong",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1542346320.44.webp",
                role_name = "孔令侃",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjaa81t9bej31oo0sox6p.jpg",
                role_descr = "扬子建业股份有限公司总经理，蒋经国到上海督导经济管制，以铁腕手段抑制物价，查封孔令侃的扬子公司"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "王宝强 Baoqiang Wang",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1356403251.95.webp",
                role_name = "四野战士",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjaahzqo3oj31lj0u0b1h.jpg",
                role_descr = "把北平城楼当作地主大院的第四野战军战士"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "葛优 You Ge",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p46.webp",
                role_name = "四野团长",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjaagywo78j31pm0tk7wh.jpg",
                role_descr = "解放军第四野战军团长"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "陈道明 Daoming Chen",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p2294.webp",
                role_name = "阎锦文",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjaarrtoayj31m40tyb29.jpg",
                role_descr = "上海警备司令部第三大队特务副队长，后响应中共地下党的号召而投向解放军"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "甄子丹 Donnie Yen",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p10695.webp",
                role_name = "田汉",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjab8ut5wnj31q90u0b2a.jpg",
                role_descr = "中华人民共和国国歌《义勇军进行曲》的作词人。"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "赵薇 Wei Zhao",
                actor_image = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1487850112.67.webp",
                role_name = "政协代表",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjabcubw18j31mg0ruhdt.jpg",
                role_descr = "反对把《义勇军进行曲》定为国歌的政协委员\n"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "邓超 Chao Deng",
                actor_image = "https://img9.doubanio.com/view/celebrity/s_ratio_celebrity/public/p805.webp",
                role_name = "徐悲鸿",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjabe2jy3zj31gd0u0hdt.jpg",
                role_descr = "中国现代画家、美术教育家，兼擅油画及水墨画。中国现代美术的奠基者，与颜文梁、林风眠和刘海粟并称“四大校长”，著名学生有艾中信、吴作人等。"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "冯巩 Gong Feng",
                actor_image = "https://img2.doubanio.com/view/celebrity/s_ratio_celebrity/public/p4973.webp",
                role_name = "政协代表",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjabbnxwd0j31k60t67wh.jpg",
                role_descr = "在讨论国歌方案时与李济深争夺发言机会的政协委员\n"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "郭德纲 Degang Guo",
                actor_image = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p6569.webp",
                role_name = "摄影师",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjabfk6dhhj31tl0u07wi.jpg",
                role_descr = "摄影师"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "宁静 Jing Ning",
                actor_image = "https://img2.doubanio.com/view/celebrity/s_ratio_celebrity/public/p28523.webp",
                role_name = "政协代表",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjabgscjl5j31rg0u07wi.jpg",
                role_descr = "参与政协会议的政协委员"
            )
        )
        acts.add(
            ActorsItem(
                actor_name = "章子怡 Ziyi Zhang",
                actor_image = "https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1359895311.0.webp",
                role_name = "龚澎",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjabhrh2agj31wg0twe82.jpg",
                role_descr = "中华人民共和国第一代女外交官，乔冠华第一任妻子"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "刘德华 Andy Lau",
                actor_image = "https://img3.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1378956633.91.webp",
                role_name = "俞济时",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjabqzu5y6j31t70u0u0x.jpg",
                role_descr = "国军高级将领。负责蒋中正的警卫工作十余年。外放后带出了五大主力之一的第74军。他所统领的第五军88师参与一二八事变，因战功获得青天白日勋章。"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "陈宝国 Baoguo Chen",
                actor_image = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1373425149.09.webp",
                role_name = "周至柔",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjabkk0f52j31rc0u0hdt.jpg",
                role_descr = "国军空军 一级上将"
            )
        )

        acts.add(
            ActorsItem(
                actor_name = "刘桦 Hua Liu",
                actor_image = "https://img9.doubanio.com/view/celebrity/large/public/p1403277374.05.webp",
                role_name = "校场口警官",
                role_image = "https://tva1.sinaimg.cn/large/007S8ZIlly1gjac58320ij31se0u04qq.jpg",
                role_descr = "较场口血案中的国民党政府警官"
            )
        )


        acts.add(
            ActorsItem(
                actor_name = "",
                actor_image = "https://img3.doubanio.com/view/photo/l/public/p1039534802.webp",
                role_name = "",
                role_image = "https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1534848910,364661341&fm=26&gp=0.jpg",
                role_descr = ""
            )
        )




    }


    /**
     * 钢铁侠2
     */
    private fun getIronMan2Banner(acts: MutableList<ActorsItem>) {
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
        act_4.role_descr =
            "前苏联核子物理学家安东·万科之子，本身也是个物理学家与机械工程天才，在父亲死后自行研发了方舟核反应堆与鞭索，准备利用它们对托尼·斯塔克进行报复。"
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
    }


}