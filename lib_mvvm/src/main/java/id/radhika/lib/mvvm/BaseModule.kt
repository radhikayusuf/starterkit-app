package id.radhika.lib.mvvm

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
abstract class BaseModule {

    lateinit var application: (() -> Context)
    lateinit var baseUrl: (() -> String)
    lateinit var tokenCaptor: (() -> String?)
    lateinit var userDataCaptor: (() -> Any?) // TODO Change into your userModel

    val token get() = if (::tokenCaptor.isInitialized) tokenCaptor.invoke() else null
    val userData get() = if (::userDataCaptor.isInitialized) userDataCaptor.invoke() else null
    val gson by lazy { Gson() }

    fun createPref(): SharedPreferences =
        application.invoke()
            .getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, Context.MODE_PRIVATE)
}