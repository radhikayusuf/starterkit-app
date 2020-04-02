package id.radhika.app.home

import id.radhika.lib.mvvm.BaseVM
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
class HomeVM : BaseVM<HomeDao>() {

    override fun getDao() = HomeDao()

    override suspend fun onCreate() {
        fetchData()
    }

    private suspend fun fetchData() {
        contentData.isLoading = true
        delay(5000)
        contentData.isLoading = false
    }

    fun calculate(inc: Boolean) {
        contentData.count += if (inc) 1 else -1
    }
}