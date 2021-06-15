package com.jay.lib_kotlin

import com.google.gson.Gson
import com.jay.lib_kotlin.data.MovieDataHelper


fun main() {
    //豆瓣电影详情地址：https://movie.douban.com/subject/{movie_id}/

//    MovieDataHelper.getMarvelMCUMovieNameIdMap().forEach { (name, id) ->
//        println("name: $name")
//        println("id: $id")
//        println("h5 url: https://movie.douban.com/subject/$id/")
//    }

    val mcuMap = MovieDataHelper.getMarvelMCUMovieNameIdMap()
    mcuMap.keys.forEach {

//        println(" const val THOR = \"${it.toUpperCase()}\"")
    }

    val json =
        "[{\"1\": \"Quhuo Limited\", \"2\": \"北京趣活科技有限公司\", \"3\": \"北京趣活信息技术科技有限公司\", \"4\": \"上海趣活趣顺信息技术有限公司\", \"5\": \"上海易即达网络科技有限公司\", \"6\": \"宁波趣活网络科技有限公司\", \"7\": \"南通润达营销策划有限公司\", \"8\": \"宁波兴达智送网络科技有限公司\", \"9\": \"宁波盛吉茂网络科技有限公司\", \"10\": \"宁波德盛万春网络科技有限公司\", \"11\": \"海南新盈科技有限公司\", \"12\": \"宁波新盈网络科技有限公司\", \"13\": \"鲜巧（上海）信息科技有限公司\", \"14\": \"上海趣活网络科技有限公司\", \"15\": \"宁波大功网络科技有限公司\", \"16\": \"海南趣活科技有限公司\", \"17\": \"合肥嘉晨网络科技有限公司\", \"18\": \"北京平艺顺达物流有限公司\", \"19\": \"江西优客汽车租赁服务有限公司\", \"20\": \"趣兴网络科技（上海）有限公司\", \"21\": \"宁波嗷嗷网络科技有限公司\", \"22\": \"海口橙途网络科技有限公司\", \"23\": \"盈鑫融资租赁（天津）有限公司\", \"24\": \"宁波煦暖网络科技有限公司\", \"25\": \"宁波大盈企业管理有限公司\", \"26\": \"宁波云哥网络科技有限公司\", \"27\": \"宁波凡煜网络科技有限公司\", \"28\": \"海南煜凡网络科技有限公司\", \"29\": \"宁波暖暖网络科技有限公司\", \"30\": \"上海易即达网络科技有限公司分公司\", \"31\": \"锦州兴达科技有限公司\", \"32\": \"宁波创擎投资管理有限公司\", \"33\": \"北京欧客云科技有限公司\", \"34\": \"上海魁刻网络科技有限公司\", \"35\": \"宁波迈肯投资管理合伙企业（有限合伙）\", \"36\": \"宁波兰开企业管理合伙企业（有限合伙）\", \"37\": \"宁波唯鼎企业管理咨询合伙企业（有限合伙）\", \"38\": \"江西优客汽车租赁服务有限公司武汉分公司\", \"39\": \"江西优客汽车租赁服务有限公司宁波分公司\", \"40\": \"江西优客汽车租赁服务有限公司苏州分公司\", \"41\": \"长沙捷信诚汽车科技有限公司\", \"42\": \"赣州优客汽车租赁服务有限公司\", \"43\": \"宁波千麟企业管理合伙企业（有限合伙）\", \"44\": \"宁波洪润企业管理合伙企业（有限合伙）\", \"45\": \"宁波心是明企业管理合伙企业（有限合伙）\", \"46\": \"宁波善聚企业管理合伙企业（有限合伙）\"}]"

    val listMap = ArrayList<Map<String, String>>()

    val map = HashMap<String, String>()
    map.put("1", "sssss")
    map.put("2", "sssss")
    map.put("3", "sssss")
    listMap.add(map)

//    println(Gson().toJson(listMap))

    testMap()

}

fun testMap() {

    val listMap = ArrayList<Map<Int, ArrayList<String>>>()

    val map = HashMap<Int, ArrayList<String>>()
    val list = ArrayList<String>()
    list.add("11:00-12:00")
    list.add("12:00-13:00")
    list.add("13:00-14:00")

    map.put(0, list)
    map.put(1, list)
    map.put(2, list)

    listMap.add(map)

    listMap.add(map)
    listMap.add(map)
    listMap.add(map)

    println(Gson().toJson(listMap))
}

data class Item(var a: String? = null, var b: String? = null) {
}


