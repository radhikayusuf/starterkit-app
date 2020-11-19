package id.radhika.lib.mvvm.contract

import android.content.Context
import android.os.Bundle
import id.radhika.lib.mvvm.BaseDao
import id.radhika.lib.mvvm.BaseScreen

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Jun/2020
 **/
interface ScreenContract<D :BaseDao> {

    fun contextFragment(): Context

    fun render(): (data: D) -> Unit

    fun openScreen(screen: BaseScreen<*, *, *>, extras: Bundle? = null)

    fun openFreshScreen(screen: BaseScreen<*, *, *>, extras: Bundle? = null)

    fun finishScreen(extras: Bundle? = null)

}