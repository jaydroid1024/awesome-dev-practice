package com.jay.lib_algorithm

import com.jay.lib_algorithm.json_file.ByteDanceAlgorithmJsonStr
import java.net.URLDecoder
import java.net.URLEncoder


/**
 * Description:
 *
 * @author xuejiewang
 * @date 2019-09-24
 * @version 1.0
 */


fun main(args: Array<String>) {

    test()


}


fun test() {

    var url = "https://ct-dev.o3cloud.cn/#/room/type/detail/update/housing/name?" +
            "id=600bc37bab9ccd132db48273&" +
            "name=%E5%8F%AF%E8%BE%BE%E9%B8%AD04&" +
            "roomId=600bc37bab9ccd132db4827b"

    val a = URLEncoder.encode(url)
    val b = URLDecoder.decode(url)
    val c = UrlParse.getUrlParamsNew(b)

    println("url$url")
    println("a: $a")
    println("b: $b")
    println("c: $c")

    val json = ByteDanceAlgorithmJsonStr.getLinkListJsonStr()

    println(json)


}

class C constructor(var c: String)

