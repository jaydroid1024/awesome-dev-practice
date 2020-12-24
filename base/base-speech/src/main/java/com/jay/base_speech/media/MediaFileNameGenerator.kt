package com.jay.base_speech.media

import android.net.Uri
import com.danikula.videocache.file.FileNameGenerator

/**
 * Created by Administrator on 2017/7/11.
 */
class MediaFileNameGenerator : FileNameGenerator {
    // Urls contain mutable parts (parameter 'sessionToken') and stable video's id (parameter 'videoId').
    // e. g. http://example.com?videoId=abcqaz&sessionToken=xyz987
    override fun generate(url: String): String {
        val uri = Uri.parse(url)
        return uri.lastPathSegment
    }
}