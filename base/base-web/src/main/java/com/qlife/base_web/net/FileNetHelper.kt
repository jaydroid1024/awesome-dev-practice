package com.qlife.base_web.net

import com.qlife.base_web.app.BaseWebApp

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2020/4/14
 */
object FileNetHelper {

    const val pdfTestUrl = "https://css4.pub/2015/textbook/somatosensory.pdf"

    /**
     * Network
     */
    private var fileNetwork: FileNetwork? = null

    @Synchronized
    fun getFileNet(): FileNetwork {
        if (fileNetwork == null) {
            fileNetwork = FileNetwork(BaseWebApp.getApp())
        }
        return fileNetwork!!
    }


}


