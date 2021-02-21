package com.jay.base_component.base.mvvm

/**
 *
 * @Description
 * @date 12/21/20 10:50 AM
 * @author BryceCui
 * @Version 1.0
 */
class Error {
    var errorMessage: String? = null
    var errorCode: Int? = null
    constructor(message:String?,code:Int?){
        this.errorMessage=message
        this.errorCode=code
    }

}