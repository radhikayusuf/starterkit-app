package id.radhika.lib.ui.utils.viewinvoker

import id.radhika.lib.ui.utils.ComponentLiveDao

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 12/Jun/2020
 **/
interface ComponentDataCallback<T> {
    fun render(data: ComponentLiveDao<T>)
}