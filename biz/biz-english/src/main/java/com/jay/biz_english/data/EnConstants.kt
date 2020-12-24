package com.jay.biz_english.data

/**
 * 电影常量
 * @author wangxuejie
 * @version 1.0
 * @date 2020/10/1
 */
object EnConstants {

    /**
     * 音标类型
     */
    object PhoneticType {
        const val TYPE_VOWEL_1 = "元音"
        const val TYPE_VOWEL_2_1 = "元音-单"
        const val TYPE_VOWEL_2_2 = "元音-双"
        const val TYPE_CONSONANT_1 = "辅音"
    }

    /**
     * 英语在线播放地址
     * 有道单词发音接口
     * 美式： http://dict.youdao.com/dictvoice?audio=单词或句子&type=2
     * 英式： http://dict.youdao.com/dictvoice?audio=单词或句子&type=1
     * 例如:
     * 美式： http://dict.youdao.com/dictvoice?audio=ask&type=2
     * 英式： http://dict.youdao.com/dictvoice?audio=ask&type=1
     * type=1代表英式发音，type=2代表美式发音
     *
     *
     *
     */

    /**
     * 扇贝单词发音
    🇺🇸 美式：http://media.shanbay.com/audio/us/单词.mp3
    🇬🇧 英式：http://media.shanbay.com/audio/uk/单词.mp3
    例如:
    🇺🇸 美式：http://media.shanbay.com/audio/us/hello.mp3
    🇬🇧 英式：http://media.shanbay.com/audio/uk/hello.mp3

    uk 代表United Kingdom， us 代表United States

    谷歌单词发音
    🇺🇸 美式：http://ssl.gstatic.com/dictionary/static/sounds/oxford/单词--_us_1.mp3
    🇺🇸 美式：http://ssl.gstatic.com/dictionary/static/sounds/oxford/单词--_gb_1.mp3
    例如:
    🇺🇸 美式：http://ssl.gstatic.com/dictionary/static/sounds/oxford/ask--us_1.mp3
    🇬🇧 英式：http://ssl.gstatic.com/dictionary/static/sounds/oxford/ask--gb_1.mp3

    gb代表Great Britain .  us代表Uncle Sam


    词霸单词发音(返回json 文件)
    http://dict-co.iciba.com/api/dictionary.php?key=AA6C7429C3884C9E766C51187BD1D86F&type=json&w=单词
    例如:
    http://dict-co.iciba.com/api/dictionary.php?key=AA6C7429C3884C9E766C51187BD1D86F&type=json&w=hello
     */

    object VoiceUrl {
        const val youdaoVoiceUrl = "http://dict.youdao.com//dictvoice?type=2&audio=%s"
        const val shanbeiVoiceUrl = "https://media.shanbay.com/audio/us/%s.mp3"
        var cibaUrl =
            "http://dict-co.iciba.com/api/dictionary.php?key=AA6C7429C3884C9E766C51187BD1D86F&type=json&w=%s"

    }


}