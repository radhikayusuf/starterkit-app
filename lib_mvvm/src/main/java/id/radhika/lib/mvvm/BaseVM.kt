package id.radhika.lib.mvvm

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import id.radhika.lib.mvvm.util.LiveDao

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
abstract class BaseVM<D : BaseDao> : ViewModel() {

    val contentData by lazy { getDao() }

    abstract suspend fun onCreate()

    abstract fun getDao(): D

    internal fun registerObserverToRender(
        fragment: BaseFragment<*, *, D>,
        liveDatas: List<LiveDao<*>>
    ) {
        liveDatas.forEach {
            it.observe(fragment, Observer {
                fragment.render().invoke(contentData)
            })
        }
    }

    internal fun getLiveDatas(): List<LiveDao<*>> {
        return try {
            contentData.javaClass.declaredFields
                .filter { LiveDao::class.java.isAssignableFrom(it.type) }
                .map { it.isAccessible = true; it.get(contentData) as LiveDao<*> }
        } catch (e: Exception) {
            emptyList()
        }
    }
}