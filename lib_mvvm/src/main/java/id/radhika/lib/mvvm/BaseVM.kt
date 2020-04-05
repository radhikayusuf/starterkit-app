package id.radhika.lib.mvvm

import androidx.annotation.StringRes
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import id.radhika.lib.data.source.UseCase
import id.radhika.lib.mvvm.util.LiveDao

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
abstract class BaseVM<D : BaseDao, U : UseCase> : ViewModel() {

    val contentData by lazy { getDao() }
    val useCase by lazy { createUseCase() }

    private val toastLiveData by lazy { LiveDao("") }
    private val toastResLiveData by lazy { LiveDao(-1) }

    abstract suspend fun onCreate()

    abstract fun getDao(): D

    abstract fun createUseCase(): U

    internal fun registerObserverToRender(
        screen: BaseScreen<*, *, D>,
        liveDatas: List<LiveDao<*>>
    ) {
        liveDatas.forEach {
            it.observe(screen, Observer {
                screen.render().invoke(contentData)
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

    fun showToast(message: String) {
        if (message.isNotEmpty())
            toastLiveData.content = message
    }

    fun showToast(@StringRes message: Int) {
        if (message != -1)
            toastResLiveData.content = message
    }

    fun registerToast(lifecycleOwner: LifecycleOwner, observer: (String, Int) -> Unit) {
        toastLiveData.observe(lifecycleOwner, Observer { observer(it, -1) })
        toastResLiveData.observe(lifecycleOwner, Observer { observer("", it) })
    }

    fun unRegisterMessage(lifecycleOwner: LifecycleOwner) {
        toastResLiveData.observe(lifecycleOwner, Observer { })
    }
}