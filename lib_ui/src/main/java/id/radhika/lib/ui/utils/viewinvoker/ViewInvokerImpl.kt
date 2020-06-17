package id.radhika.lib.ui.utils.viewinvoker

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import id.radhika.lib.ui.utils.ComponentLiveDao

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
class ViewInvokerImpl<T>(
    private val componentLiveDao: ComponentDataCallback<T>
) : ViewInvoker<T> {
    private val lifecycle = object : Lifecycle() {
        override fun addObserver(observer: LifecycleObserver) {

        }

        override fun removeObserver(observer: LifecycleObserver) {

        }

        override fun getCurrentState(): State =
            when {
                else -> {
                    State.DESTROYED
                }
            }
        }

    override fun attach(data: ComponentLiveDao<T>) {
        data.observe({ lifecycle }, {
            componentLiveDao.render(data)
        })
    }

    override fun detach(data: ComponentLiveDao<T>) {
        data.removeObservers { lifecycle }
    }
}