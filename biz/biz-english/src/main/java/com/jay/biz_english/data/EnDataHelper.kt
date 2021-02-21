package com.jay.biz_english.data

import com.jay.biz_english.data.EnConstants.PhoneticType.TYPE_CONSONANT_1
import com.jay.biz_english.data.EnConstants.PhoneticType.TYPE_VOWEL_1


/**
 * @author wangxuejie
 * @version 1.0
 * @date 2020/8/13
 */
object EnDataHelper {


    /**
     * 音标48
     *
     * 元音 20
     * 单元音
     * 前元音 /iː/ /ɪ/ /e/ /æ/
     * 中元音 /ɜː/ /ə/ /ʌ/
     * 后元音 /uː/ /ʊ/ /ɔː/ /ɒ/ /ɑː/
     * 双元音
     * 开合双元音 /eɪ/ /aɪ/ /ɔɪ/ /aʊ/ /əʊ/
     * 集中双元音 /ɪə/ /eə/ /ʊə/
     *
     * 辅音 28
     * 爆破音
     * 清辅音 /p/ /t/ /k/
     * 浊辅音 /b/ /d/ /ɡ/
     * 摩擦音
     * 清辅音 /f/ /s/ /ʃ/ /θ/ /h/
     * 浊辅音 /v/ /z/ /ʒ/ /ð/ /r/
     * 破擦音
     * 清辅音 /tʃ/ /tr/ /ts/
     * 浊辅音 /dʒ/ /dr/ /dz/
     * 鼻音
     * （浊辅音） /m/ /n/ /ŋ/
     * 舌则音
     * （浊辅音） /l/
     * 半元音
     * （浊辅音） /j/ /w/
     * 注：不同词典有不同的音标符号系统，本表为最新DJ英语国际音标表符号
     */
    fun getPhoneticList(): MutableList<PhoneticItem>? {
        val phoneticList = mutableListOf<PhoneticItem>()


        phoneticList.add(
            PhoneticItem(
                name = "/iː/",
                chineseName = "衣(长音)",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/i-sound2.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ɪ/",
                chineseName = "衣",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/i-sound.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/e/",
                chineseName = "挨(小口)",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/e-sound.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/æ/",
                chineseName = "挨(大口)",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/an-sound.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ɜː/",
                chineseName = "鹅(长音) ",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/er-sound.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ə/",
                chineseName = "鹅",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/e%5E-sound.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ʌ/",
                chineseName = "阿",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/an-sound.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/uː/",
                chineseName = "乌(长音)",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/u-sound2.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ʊ/",
                chineseName = "呜",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/u-sound.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ɔː/",
                chineseName = "奥(长音)",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/o-sound2.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ɒ/",
                chineseName = "哦",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/o-sound.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ɑː/",
                chineseName = "阿(长音)",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/a-sound2.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/eɪ/ ",
                chineseName = "Aa(字母)",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/ei.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/aɪ/ ",
                chineseName = "Ii(字母)",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/ai.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ɔɪ/",
                chineseName = "奥一",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/oi.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/aʊ/",
                chineseName = "欧(长音)",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/ao.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/əʊ/",
                chineseName = "奥(长音)",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/eu.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ɪə/",
                chineseName = "衣鹅",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/ir.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/eə/",
                chineseName = "挨鹅",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/er.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ʊə/",
                chineseName = "呜鹅",
                type1 = TYPE_VOWEL_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/uer.mp3"
            )
        )

        //辅音
        phoneticList.add(
            PhoneticItem(
                name = "/p/ ",
                chineseName = "泼",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/p.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/t/",
                chineseName = "特",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/t.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/k/",
                chineseName = "科",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/k.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/b/",
                chineseName = "播",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/b.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/d/",
                chineseName = "的",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/d.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ɡ/",
                chineseName = "哥",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/g.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/f/",
                chineseName = "夫",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/f.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/s/ ",
                chineseName = "思(咬齿)",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/s.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ʃ/ ",
                chineseName = "诗",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/ss.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/θ/",
                chineseName = "思(咬舌)",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/si.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/h/",
                chineseName = "喝",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/h.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/v/",
                chineseName = "物",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/v.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/z/",
                chineseName = "自",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/z.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ʒ/",
                chineseName = "日",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/n3.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ð/",
                chineseName = "字(咬舌)",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/qq.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/r/",
                chineseName = "热",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/r.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/tʃ/",
                chineseName = "吃",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/tss.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/tr/",
                chineseName = "缺",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/tr.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ts/",
                chineseName = "刺",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/ts.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/dʒ/",
                chineseName = "之",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/d3.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/dr/",
                chineseName = "拙",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/dr.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/dz/",
                chineseName = "自",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/dz.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/m/ ",
                chineseName = "木",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/m.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/n/",
                chineseName = "呢",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/n.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/ŋ/",
                chineseName = "嗯",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/ng.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/l/",
                chineseName = "了",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/l.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/j/",
                chineseName = "也",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/j.mp3"
            )
        )
        phoneticList.add(
            PhoneticItem(
                name = "/w/",
                chineseName = "我",
                type1 = TYPE_CONSONANT_1,
                url = "https://i.xiao84.com/en-yinbiao/mp3/48yinbiaomp3/w.mp3"
            )
        )
        return phoneticList


    }

    /**
     * 元音
     */
    fun getVPhoneticList(): MutableList<PhoneticItem>? {
        val phoneticList = mutableListOf<PhoneticItem>()
        val temp = getPhoneticList()
        temp!!.forEach {
            if (it.type1 == TYPE_VOWEL_1) {
                phoneticList.add(it)
            }
        }
        return phoneticList
    }

    /**
     * 辅音
     */
    fun getCPhoneticList(): MutableList<PhoneticItem>? {
        val phoneticList = mutableListOf<PhoneticItem>()
        val temp = getPhoneticList()
        temp!!.forEach {
            if (it.type1 == TYPE_CONSONANT_1) {
                phoneticList.add(it)
            }
        }
        return phoneticList
    }


}