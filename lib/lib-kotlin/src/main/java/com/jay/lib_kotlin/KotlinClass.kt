package com.jay.lib_kotlin

import com.jay.biz_movie.data.MovieDataHelper


fun main() {
    //豆瓣电影详情地址：https://movie.douban.com/subject/{movie_id}/

//    MovieDataHelper.getMarvelMCUMovieNameIdMap().forEach { (name, id) ->
//        println("name: $name")
//        println("id: $id")
//        println("h5 url: https://movie.douban.com/subject/$id/")
//    }

    val mcuMap = MovieDataHelper.getMarvelMCUMovieNameIdMap()
    mcuMap.keys.forEach {

        println(" const val THOR = \"${it.toUpperCase()}\"")
    }

}


