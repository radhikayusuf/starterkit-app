package id.radhika.lib.mvvm

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
abstract class BaseModule {

    lateinit var application: (() -> Context)

    fun createPref(): SharedPreferences =
        application.invoke().getSharedPreferences(BuildConfig.LIBRARY_PACKAGE_NAME, Context.MODE_PRIVATE)
}