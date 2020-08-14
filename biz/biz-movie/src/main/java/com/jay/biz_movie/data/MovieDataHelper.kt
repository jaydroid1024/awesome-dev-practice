package com.jay.biz_movie.data

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2020/8/13
 */
object MovieDataHelper {

    /**
     * 获取漫威宇宙电影的豆瓣 <name id>
     * 豆瓣电影详情地址：https://movie.douban.com/subject/{movie_id}/
     */
    fun getMarvelMCUMovieNameIdMap(): HashMap<String, String> {
        val nameIdMap = HashMap<String, String>()
        nameIdMap["钢铁侠 Iron Man"] = "1432146"
        nameIdMap["无敌浩克 The Incredible Hulk"] = "1866475"
        nameIdMap["钢铁侠2 Iron Man 2"] = "3066739"
        nameIdMap["雷神 Thor"] = "1866471"
        nameIdMap["美国队长 Captain America: The First Avenger"] = "2138838"
        nameIdMap["复仇者联盟 The Avengers"] = "1866479"

        nameIdMap["钢铁侠3 Iron Man 3"] = "3231742"
        nameIdMap["雷神2：黑暗世界 Thor: The Dark World"] = "6560058"
        nameIdMap["美国队长2 Captain America: The Winter Soldier"] = "6390823"
        nameIdMap["银河护卫队 Guardians of the Galaxy"] = "7065154"
        nameIdMap["复仇者联盟2：奥创纪元 Avengers: Age of Ultron"] = "10741834"

        nameIdMap["蚁人 Ant-Man"] = "1866473"
        nameIdMap["美国队长3 Captain America: Civil War"] = "25820460"
        nameIdMap["奇异博士 Doctor Strange"] = "3025375"
        nameIdMap["银河护卫队2 Guardians of the Galaxy Vol. 2"] = "25937854"
        nameIdMap["蜘蛛侠：英雄归来 Spider-Man: Homecoming"] = "24753477"
        nameIdMap["雷神3：诸神黄昏 Thor: Ragnarok"] = "25821634"
        nameIdMap["黑豹 Black Panther"] = "6390825"
        nameIdMap["复仇者联盟3：无限战争 Avengers: Infinity War"] = "24773958"

        nameIdMap["蚁人2：黄蜂女现身 Ant-Man and the Wasp"]="26636712"
        nameIdMap["惊奇队长 Captain Marvel"]="26213252"
        nameIdMap["复仇者联盟4：终局之战 Avengers: Endgame"]="26100958"

        nameIdMap["蜘蛛侠：英雄远征 Spider-Man: Far from Home"]="26931786"
        return nameIdMap
    }
}