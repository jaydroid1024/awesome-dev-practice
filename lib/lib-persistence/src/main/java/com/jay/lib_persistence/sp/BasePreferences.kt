package com.jay.lib_persistence.sp

import android.content.Context
import android.content.SharedPreferences
import com.f2prateek.rx.preferences2.RxSharedPreferences
import com.google.gson.Gson
import com.jay.lib_persistence.app.LibPersistenceApp
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * SP抽象基类，各自的组件需要通过扩展这个抽象类实现自己的SP
 * 封装了RxSharedPreferences，可返回一个Observable<T>
 * 封装 DataStore
 */
abstract class BasePreferences {

    private val gson: Gson = Gson()

    private var sharedPreferences: SharedPreferences? = null

    protected var rxSharedPreferences: RxSharedPreferences? = null

    abstract val preferencesGroup: String

    private fun initPreferences(context: Context, preferencesGroup: String) {
        sharedPreferences = context.getSharedPreferences(preferencesGroup, 0)
        rxSharedPreferences = RxSharedPreferences.create(sharedPreferences!!)
    }

    protected fun saveData(tag: String?, value: String?) {
        rxSharedPreferences?.getString(tag!!)?.set(value!!)
    }

    protected fun saveData(tag: String?, value: Long?) {
        rxSharedPreferences?.getLong(tag!!)?.set(value!!)
    }

    protected fun saveData(tag: String?, value: Boolean?) {
        rxSharedPreferences!!.getBoolean(tag!!).set(value!!)
    }

    protected fun saveData(tag: String?, value: Int?) {
        rxSharedPreferences!!.getInteger(tag!!).set(value!!)
    }

    protected fun saveData(tag: String?, value: Float?) {
        rxSharedPreferences!!.getFloat(tag!!).set(value!!)
    }

    protected fun <T> saveData(tag: String?, obj: T) {
        saveData(tag, gson.toJson(obj))
    }

    protected fun <T> getData(tag: String?, classOfT: Class<T>?): T {
        val rawData = rxSharedPreferences!!.getString(tag!!).get()
        return gson.fromJson(rawData, classOfT)
    }

    protected fun <T> getDataAsObservable(tag: String?, classOfT: Class<T>?): Observable<T> {
        return rxSharedPreferences!!.getString(tag!!)
            .asObservable()
            .map { s -> gson.fromJson(s, classOfT) }
    }

    fun clearData(tag: String?): Observable<Boolean> {
        return Observable.defer {
            if (sharedPreferences != null) {
                sharedPreferences!!.edit().remove(tag).apply()
                return@defer Observable.just(true)
            }
            Observable.just(false)
        }
    }

    fun clearAllData(): Observable<Boolean> {
        return Observable.defer {
            if (sharedPreferences != null) {
                sharedPreferences!!.edit().clear().apply()
                return@defer Observable.just(true)
            }
            Observable.just(false)
        }
    }

    protected fun getString(tag: String?): String {
        return rxSharedPreferences!!.getString(tag!!).get()
    }

    protected fun getStringAsObservable(tag: String?): Observable<String> {
        return rxSharedPreferences!!.getString(tag!!, "").asObservable()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    protected fun getStringAsObservable(tag: String?, defaultValue: String?): Observable<String> {
        return rxSharedPreferences!!.getString(tag!!, defaultValue!!).asObservable()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    protected fun getLong(tag: String?): Long {
        return rxSharedPreferences!!.getLong(tag!!).get() ?: return 0
    }

    protected fun getLongAsObservable(tag: String?): Observable<Long> {
        return rxSharedPreferences!!.getLong(tag!!)
            .asObservable()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    protected fun getBoolean(tag: String?): Boolean {
        return rxSharedPreferences!!.getBoolean(tag!!).get() ?: return false
    }

    protected fun getBooleanAsObservable(tag: String?): Observable<Boolean> {
        return rxSharedPreferences!!.getBoolean(tag!!)
            .asObservable()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    protected fun getInteger(tag: String?): Int {
        return rxSharedPreferences!!.getInteger(tag!!).get() ?: return 0
    }

    protected fun getIntegerAsObservable(tag: String?): Observable<Int> {
        return rxSharedPreferences!!.getInteger(tag!!).asObservable()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    protected fun getFloat(tag: String?): Float {
        return rxSharedPreferences!!.getFloat(tag!!).get() ?: return 0f
    }

    protected fun getFloatAsObservable(tag: String?): Observable<Float> {
        return rxSharedPreferences!!.getFloat(tag!!).asObservable()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
    }

    operator fun contains(preferenceKey: String?): Boolean {
        return sharedPreferences!!.contains(preferenceKey)
    }


    init {
        initPreferences(LibPersistenceApp.getApp(), preferencesGroup)
    }
}