package id.lesprivate.lib.ui.utils

import androidx.lifecycle.LiveData

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 02/Apr/2020
 **/
class NullableLiveDao<T>(private val defValue: T?) : LiveData<T>() {

    var hasValueBefore = false

    var content: T? = defValue
        get() = value ?: defValue
        set(param) {
            hasValueBefore = true
            field = param
            postValue(param)
        }

    override fun postValue(value: T?) {
        super.postValue(value)
    }

    override fun setValue(value: T?) {
        super.setValue(value)
    }
}