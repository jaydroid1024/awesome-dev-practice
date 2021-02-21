package com.qlife.biz_setting.mvp

import com.okeyun.util.L
import com.qlife.base_component.base.mvp.rx.MvpRxPresenter
import com.qlife.base_component.bean.bean.Ok
import com.qlife.base_component.net.callback.ApiCallback
import com.qlife.base_component.net.callback.NetCallBack
import com.qlife.biz_setting.R
import com.qlife.biz_setting.net.BizSettingNetFactory

/**
 * 设置页面业务处理层
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 18/3/19下午4:53
 */

class TemplatePresenter(view: TemplateView) : MvpRxPresenter<TemplateView>() {

    init {
        attachView(view)
    }

    /**
     * 发送退出请求
     */
    fun getExitRequest() {
        view?.showLoadingView()
        val observable = BizTemplateNetFactory.network().logout()
        onSubscribe(
            observable, ApiCallback(object : NetCallBack<Ok> {
                override fun onSuccess(response: Ok) {

                    if (!response.ok) {
                        view?.showSuccessToast(R.string.biz_setting_exit_failure)
                        return
                    }
                    L.d(TAG, "onSuccess exitSuccess:")
                    view?.exitSuccess()
                }

                override fun onFailure(errorCode: Int, errMsg: String) {

                    L.d(
                        TAG,
                        "setting onFailure errorCode:$errorCode;errMsg: $errMsg"
                    )
                    view?.showWaringToast(errMsg)
                    view?.exitFailure()
                }

                override fun onCompleted() {

                    view?.hideLoadingView()
                }
            })
        )
    }

    companion object {
        private val TAG = TemplatePresenter::class.java.simpleName
    }
}
