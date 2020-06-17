package id.radhika.lib.ui.utils.viewinvoker

import id.radhika.lib.ui.utils.ComponentLiveDao

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
interface ViewInvoker<T> {

    fun attach(data: ComponentLiveDao<T>)

    fun detach(data: ComponentLiveDao<T>)
}