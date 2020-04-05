package id.radhika.feature.dummy.screen.home

import id.radhika.feature.dummy.data.usecases.HomeUseCase
import id.radhika.lib.mvvm.BaseVM
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
class HomeVM : BaseVM<HomeDao, HomeUseCase>() {

    override fun getDao() = HomeDao()
    override fun createUseCase() = HomeUseCase()

    override suspend fun onCreate() {
        fetchData()
    }

    private suspend fun fetchData() {
        contentData.isLoading = true

        delay(5000)

        useCase.getHomeData().let {
            if (it.isSuccess) {
                contentData.name = it.data ?: ""
                showToast("Successfully")
            } else {
                showToast(it.message)
            }
        }

        contentData.isLoading = false
    }

    fun calculate(inc: Boolean) {
        contentData.count += if (inc) 1 else -1
    }
}