package id.radhika.lib.mvvm

import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import id.radhika.lib.mvvm.util.NullableLiveDao
import id.radhika.lib.mvvm.contract.ScreenContract
import id.radhika.lib.mvvm.util.GroupableLivedao
import id.radhika.lib.mvvm.util.LiveDao
import id.radhika.lib.mvvm.util.LiveListDao
import id.radhika.lib.ui.utils.ComponentLiveDao
import kotlinx.coroutines.*
import kotlinx.coroutines.launch as fire
import kotlinx.coroutines.async as await
import kotlin.coroutines.CoroutineContext

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
abstract class BaseVM<D : BaseDao>() : ViewModel(), CoroutineScope {

    private val job: Job = SupervisorJob()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    private val closeScreen by lazy { LiveDao(false) }
    private val toastLiveData by lazy { LiveDao("") }
    private val toastResLiveData by lazy { LiveDao(-1) }
    val dao: D by lazy { onCreateDao() }

    abstract suspend fun onCreate()

    abstract fun onCreateDao(): D

    internal fun registerObserverToRender(
        screen: Fragment
    ) {
        getLiveDatas()
            .forEach { liveData ->
                val isHasGroup = isLiveDao(liveData) && liveData is GroupableLivedao
                liveData.observe(screen, Observer {
                    if (screen is ScreenContract<*>) {
                        if (isHasGroup) {
                            (screen.render() as ((data: D, group: String) -> Unit)).invoke(dao, (liveData as GroupableLivedao).groupName)
                        } else {
                            (screen.render() as ((data: D, group: String) -> Unit)).invoke(dao, "")
                        }
                    }
                })
            }
    }

    internal fun unRegisterObserver(lifecycleOwner: LifecycleOwner) {
        getLiveDatas().forEach {
            it.removeObservers(lifecycleOwner)
        }
    }

    private fun isLiveDao(data: LiveData<*>) =
        data is LiveDao<*> ||
                data is ComponentLiveDao<*> ||
                data is NullableLiveDao<*> ||
                data is LiveListDao<*>

    private fun getLiveDatas(): List<LiveData<*>> {
        return try {
            dao.javaClass.declaredFields
                .filter {
                    LiveDao::class.java.isAssignableFrom(it.type) ||
                            ComponentLiveDao::class.java.isAssignableFrom(it.type) ||
                            NullableLiveDao::class.java.isAssignableFrom(it.type) ||
                            LiveListDao::class.java.isAssignableFrom(it.type)
                }
                .map { it.isAccessible = true; it.get(dao) as LiveData<*> }
        } catch (e: Exception) {
            emptyList()
        }
    }

    fun showToast(message: String) {
        if (message.isNotEmpty())
            toastLiveData.content = message
    }

    fun showToast(@StringRes messageRes: Int) {
        if (messageRes != -1)
            toastResLiveData.content = messageRes
    }

    fun finishScreen() {
        closeScreen.content = true
    }

    fun registerToast(lifecycleOwner: LifecycleOwner, observer: (String, Int) -> Unit) {
        toastLiveData.observe(lifecycleOwner, Observer { observer(it, -1) })
        toastResLiveData.observe(lifecycleOwner, Observer { observer("", it) })
    }

    fun unRegisterMessage(lifecycleOwner: LifecycleOwner) {
        toastLiveData.removeObservers(lifecycleOwner)
        toastResLiveData.removeObservers(lifecycleOwner)
        closeScreen.removeObservers(lifecycleOwner)
    }

    fun registerCloseScreen(lifecycleOwner: LifecycleOwner, observer: () -> Unit) {
        closeScreen.observe(lifecycleOwner, Observer { observer() })
    }

    fun launch(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        command: suspend () -> Unit
    ) =
        fire(dispatcher) {
            command.invoke()
        }

    fun <T> async(
        dispatcher: CoroutineDispatcher = Dispatchers.IO,
        command: suspend () -> T
    ) =
        await(dispatcher) {
            command.invoke()
        }
}