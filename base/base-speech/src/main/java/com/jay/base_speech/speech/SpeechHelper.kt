package com.jay.base_speech.speech

import android.os.Bundle
import android.util.Log
import com.iflytek.cloud.*
import com.jay.base_speech.app.BaseSpeechApp

/**
 * @author wangxuejie
 * @version 1.0
 * @date 2020/10/5
 */
object SpeechHelper {

    // 语音合成对象
    private var mTts: SpeechSynthesizer? = null

    const val TAG = "SpeechHelper"

    fun initSpeech() {
        // 初始化合成对象
        mTts = SpeechSynthesizer.createSynthesizer(BaseSpeechApp.getApp(), mTtsInitListener)
    }

    fun startSpeech(text: String) {
        // 设置参数
        val code = mTts!!.startSpeaking(text, mTtsListener)
        //			/**
        //			 * 只保存音频不进行播放接口,调用此接口请注释startSpeaking接口
        //			 * text:要合成的文本，uri:需要保存的音频全路径，listener:回调接口
        //			*/
        //			String path = Environment.getExternalStorageDirectory()+"/tts.pcm";
        //			int code = mTts.synthesizeToUri(text, path, mTtsListener);
        if (code != ErrorCode.SUCCESS) {
            Log.d(TAG, "语音合成失败,错误码: $code,请点击网址https://www.xfyun.cn/document/error-code查询解决方案")
        }
    }

    /**
     * 合成回调监听。
     */
    private val mTtsListener: SynthesizerListener = object : SynthesizerListener {
        override fun onSpeakBegin() {
            //showTip("开始播放");
            Log.d(TAG, "开始播放：" + System.currentTimeMillis())
        }

        override fun onSpeakPaused() {
            Log.d(TAG, "暂停播放")
        }

        override fun onSpeakResumed() {
            Log.d(TAG, "继续播放")
        }

        override fun onBufferProgress(
            percent: Int, beginPos: Int, endPos: Int,
            info: String
        ) {
            Log.d(TAG, "onBufferProgress,percent: ] =${percent}")
        }

        override fun onSpeakProgress(
            percent: Int,
            beginPos: Int,
            endPos: Int
        ) {
            Log.d(
                TAG,
                "onSpeakProgress， percent=${percent}， beginPos=${beginPos}， endPos=${endPos}"
            )

        }

        override fun onCompleted(error: SpeechError) {
            Log.d(TAG, "onCompleted, error ] =${error.errorDescription}")
            if (error == null) {
                Log.d(TAG, "播放完成")
            } else if (error != null) {
                Log.d(TAG, error.getPlainDescription(true))
            }
        }

        override fun onEvent(
            eventType: Int,
            arg1: Int,
            arg2: Int,
            obj: Bundle?
        ) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            if (SpeechEvent.EVENT_SESSION_ID == eventType) {
                val sid = obj?.getString(SpeechEvent.KEY_EVENT_AUDIO_URL)
                Log.d(TAG, "session id =$sid")
            }

            //实时音频流输出参考
            /*if (SpeechEvent.EVENT_TTS_BUFFER == eventType) {
            byte[] buf = obj.getByteArray(SpeechEvent.KEY_EVENT_TTS_BUFFER);
            Log.e("MscSpeechLog", "buf is =" + buf);
        }*/
        }
    }

    /**
     * 初始化监听。
     */
    private val mTtsInitListener = InitListener { code ->
        Log.d(TAG, "InitListener init() code = $code")
        if (code != ErrorCode.SUCCESS) {
            Log.d(TAG, "初始化失败,错误码：$code,请点击网址https://www.xfyun.cn/document/error-code查询解决方案")
        } else {
            Log.d(TAG, "初始化成功：")
        }
    }
}