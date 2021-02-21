package com.jay.base_component.bean.net

/**
 * 网络请求错误码
 *
 * @author gaoxiaoiduo
 * @date 2019-12-09 17:34
 * @version 1.0
 */
object ErrorCode {
    /**
     * 200:成功
     */
    const val REQUEST_OK = 200

    /**
     * 500:服务器端错误
     */
    const val SERVICE_ERROR = 500

    /**
     * 502:BAD GATEWAY
     * 产生原因：连接超时。服务器当前链接太多导致服务器方面无法给予正常的响应
     */
    const val BAD_GATEWAY = 502

    /**
     * 401:授权错误  需要升级/重新安装
     */
    const val AUTH_ERROR = 401

    /**
     * 400:应用层错误|请求错误|客户端错误
     */
    const val APP_ERROR = 400

    /**
     * -1000:网络异常
     */
    const val NETWORK_ERROR = -1000

    /**
     * -1001:数据解析错误
     */
    const val DATA_PARSE_ERROR = -1001

    /**
     * -1002:空指针异常
     */
    const val NULl_POINT_ERROR = -1002

    /* ========================================================= */
    /* ==================== 400错误============================== */
    /* ========================================================= */

    /**
     * 参数缺失或错误
     */
    const val INVALID_ARGUMENT = 401001

    /**
     * 验证码错误
     */
    const val VERIFY_CODE_ERR = 401008

    /**
     * 验证码发送过于频繁
     */
    const val VERIFY_CODE_FREQUENT_ERR = 401002

    /**
     * 账号不存在
     */
    const val ACCOUNT_NOT_FOUND = 408003

    /**
     * 账号不可用（已离职）
     */
    const val ACCOUNT_DISABLE = 408002

    /**
     * 无效access token或access token已过期
     */
    const val ACCESS_TOKEN_INVALID = 415001

    /**
     * 发送验证码失败
     */
    const val SEND_SMS_FAILED = 416001

    /**
     * 创建七牛Token失败
     */
    const val QINIU_CREATE_TOKEN_FAILED = 416002

    /**
     * 未知异常
     */
    const val UNKNOWN_ERROR = 499999
}
