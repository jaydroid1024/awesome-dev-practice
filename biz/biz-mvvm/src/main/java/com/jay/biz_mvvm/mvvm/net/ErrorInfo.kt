package com.jay.base_component.bean.net

/**
 * @author zhanghao
 * @version 1.0
 * @date 15/11/1下午5:17
 */
object ErrorInfo {
    /**
     * 200:成功
     */
    val REQUEST_OK = 200

    /**
     * 500:服务器端错误
     */
    val SERVICE_ERROR = 500

    /**
     * 502:BAD GATEWAY
     * 产生原因：连接超时。服务器当前链接太多导致服务器方面无法给予正常的响应
     */
    val BAD_GATEWAY = 502

    /**
     * 401:授权错误  需要升级/重新安装
     */
    val AUTH_ERROR = 401

    /**
     * 400:应用层错误|请求错误|客户端错误
     */
    val APP_ERROR = 400

    /**
     * 404:NOT FOUND
     * 客户端请求，服务器无法正常提供信息或服务器无法回应且不知原因
     */
    val APP_NOT_FOUND = 404

    /**
     * 1000：资源未找到
     */
    val OBJECT_NOT_FOUND = 1000

    /**
     * -1000:网络异常
     */
    val NETWORK_ERROR = -1000

    /**
     * -1001:数据解析错误
     */
    val DATA_PARSE_ERROR = -1001

    /**
     * -1002:空指针异常
     */
    const val NULl_POINT_ERROR = -1002

    /* ========================================================= */
    /* ==================== 400错误============================== */
    /* ========================================================= */

    /**
     * 账号不可用
     */
    val ACCOUNT_DISABLE = 407004

    /**
     * 无效access token或access token已过期
     */
    val ACCESS_TOKEN_INVALID = 415001

    /**
     * 无效request token或request token已过期
     */
    val REQUEST_TOKEN_INVALID = 415002

    /**
     * 无效refresh token或refresh token已过期
     */
    val REFRESH_TOKEN_INVALID = 415003

    /**
     * 账号没有找到
     */
    val ACCOUNT_NOT_FOUND = 408001

    /**
     * 该商户不使用消息系统，客户端需要做处理
     */
    const val NO_UMS_AUTHOR = 400403
}
