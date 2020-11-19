package id.radhika.lib.mvvm

import android.app.Application
import android.content.Context
import com.google.gson.Gson
import id.radhika.lib.mvvm.util.StringConst
import id.radhika.lib.mvvm.util.StringConst.KEY_TOKEN
import id.radhika.lib.mvvm.util.StringConst.KEY_USER_DATA
import java.lang.Exception

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 13/Jun/2020
 **/
abstract class BaseApplication : Application() {

    private val pref by lazy {
        applicationContext.getSharedPreferences(
            BuildConfig.LIBRARY_PACKAGE_NAME,
            Context.MODE_PRIVATE
        )
    }
    val isDevelopment by lazy {
        pref.getBoolean("DEVELOPMENT", false)
    }

    abstract fun appendModule(): List<BaseModule>

    override fun onCreate() {
        super.onCreate()
        appendModule().forEach {
            it.application = { this@BaseApplication }
            it.tokenCaptor = { pref.getString(KEY_TOKEN, "")?.replace("\"", "") }
            it.userDataCaptor = {
                try {
                    Gson().fromJson(pref.getString(KEY_USER_DATA, null), Any::class.java)
                } catch (e: Exception) {
                    null
                }
            }
        }
    }

    fun logout() {
        pref.edit().apply {
            putString(StringConst.KEY_USER_DATA, null)
            putString(StringConst.KEY_TOKEN, null)
        }.apply()
    }
}