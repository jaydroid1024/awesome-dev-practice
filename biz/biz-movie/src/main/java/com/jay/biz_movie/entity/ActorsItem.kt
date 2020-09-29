package com.jay.biz_movie.entity

/**
 * 演员信息
 * @author wangxuejie
 * @version 1.0
 * @date 2020/8/15
 */
data class ActorsItem(

    //演员名字
    var actor_name: String = "",
    //演员简介
    var actor_desc: String = "",
    //演员昵称
    var actor_nickname: String = "",
    //演员图片
    var actor_image: String = "",

    //角色名字
    var role_name: String = "",
    //角色简介
    var role_descr: String = "",
    //角色昵称
    var role_nickname: String = "",
    //角色图片
    var role_image: String = "",

    var photos: List<String>? = null
)