package com.qlife.biz_setting.mvp


import com.qlife.base_component.base.mvp.MvpBaseView

/**
 * 登录界面交互回调接口
 *
 * @author gaoxiaoduo
 * @version 1.0
 * @date 17/12/6下午4:46
 */

interface TemplateView : MvpBaseView {
    /**
     * 退出成功
     */
    fun exitSuccess()

    /**
     * 退出失败
     */
    fun exitFailure()
}
