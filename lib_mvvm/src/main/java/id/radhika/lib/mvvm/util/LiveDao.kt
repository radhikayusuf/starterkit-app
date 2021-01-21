package id.radhika.lib.mvvm.util

import androidx.lifecycle.LiveData

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 02/Apr/2020
 **/
class LiveDao<T>(private val defValue: T, override val groupName: String = "") : LiveData<T>(), GroupableLivedao {

    var content: T = defValue
        set(param) {
            param?.let {
                field = it
                postValue(it)
            }
        }

    override fun postValue(value: T) {
        super.postValue(value)
    }

    override fun setValue(value: T) {
        super.setValue(value)
    }
}