package id.radhika.lib.mvvm.util

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.FragmentManager
import id.radhika.lib.mvvm.BaseScreen
import id.radhika.lib.mvvm.contract.ScreenContract
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 05/Apr/2020
 **/

fun String.isUsername(): Boolean =
    this.matches(Regex(StringConst.VALID_USERNAME_REGEX))

fun String.isEmail(): Boolean =
    this.matches(Regex(StringConst.VALID_EMAIL_ADDRESS_REGEX))

fun String.isPassword(): Boolean =
    this.matches(Regex(StringConst.VALID_PASSWORD_REGEX))

fun String.isBaktiPassword(): Boolean =
    this.length >= 6

fun Double.toRupiah(): String {
    val localeID = Locale("in", "ID")
    val numberFormat = NumberFormat.getCurrencyInstance(localeID)
    return numberFormat.format(this).toString()
}

fun ScreenContract<*>.showToast(value: String, length: Int = Toast.LENGTH_SHORT) {
    contextFragment().let { Toast.makeText(it, value, length).show() }
}

fun ScreenContract<*>.showToast(@StringRes value: Int, length: Int = Toast.LENGTH_SHORT) {
    contextFragment()?.let { Toast.makeText(it, value, length).show() }
}

fun FragmentManager.replaceScreen(
    screen: BaseScreen<*, *, *>, @IdRes id: Int,
    @AnimatorRes @AnimRes animIn: Int = -1,
    @AnimatorRes @AnimRes animOut: Int = -1,
    argument: (Bundle.() -> Unit)? = null
) {
    beginTransaction().also { transact ->
        if (animIn != -1 && animOut != -1)
            transact.setCustomAnimations(animIn, animOut)

        transact.replace(id, screen.also {
            val arg = Bundle()
            argument?.invoke(arg)
            it.setArguments(arg)
        })
    }.commit()
}

fun FragmentManager.hideAll() {
    val contents = fragments
    beginTransaction().also { transact ->
        contents.forEach { transact.hide(it) }
    }.commit()
}

fun Long.convertLongToTime(output: String? = null): String {
    val date = Date(this)
    val format = if (output != null) {
        SimpleDateFormat(output, Locale("in", "ID"))
    } else {
        SimpleDateFormat.getDateTimeInstance()
    }
    return format.format(date)
}

fun currentTimeToLong(): Long {
    return System.currentTimeMillis()
}

fun convertDateToLong(date: String): Long {
    val df = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return df.parse(date).time
}

fun String.reformatDate(inputFormat: String, output: String? = null): String {
    return try {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = Date(df.parse(this.replace("T", " ").replace("Z", " ")).time)
        val format = if (output != null) {
            SimpleDateFormat(output, Locale("in", "ID"))
        } else {
            SimpleDateFormat.getDateTimeInstance()
        }
        format.format(date)
    } catch (e: Exception) {
        this
    }
}

fun String.fullDateToSimple(output: String? = null): String {
    return try {
        val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = Date(df.parse(this.replace("T", " ").replace("Z", " ")).time)
        val format = if (output != null) {
            SimpleDateFormat(output, Locale("in", "ID"))
        } else {
            SimpleDateFormat.getDateTimeInstance()
        }
        format.format(date)
    } catch (e: Exception) {
        this
    }
}