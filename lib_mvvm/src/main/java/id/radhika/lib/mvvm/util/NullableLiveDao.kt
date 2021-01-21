package id.radhika.lib.mvvm.util

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 02/Apr/2020
 **/
class NullableLiveDao<T>(
    private val defValue: T?,
    override val groupName: String = ""
) : LiveData<T>(), GroupableLivedao {

    var hasValueBefore = false

    var content: T? = defValue
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