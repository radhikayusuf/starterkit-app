package id.radhika.lib.mvvm.util

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 02/Apr/2020
 **/

import kotlin.reflect.*

operator fun <R, T> KProperty0<T>.getValue(receiver: R, prop: KProperty<*>): T =
    get()

operator fun <R, T> KProperty1<R, T>.getValue(receiver: R, prop: KProperty<*>): T =
    get(receiver)

operator fun <R, T> KMutableProperty0<T>.setValue(receiver: R, prop: KProperty<*>, value: T) =
    set(value)

operator fun <R, T> KMutableProperty1<R, T>.setValue(receiver: R, prop: KProperty<*>, value: T) =
    set(receiver, value)