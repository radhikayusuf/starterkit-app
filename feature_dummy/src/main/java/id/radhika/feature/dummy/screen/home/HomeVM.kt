package id.radhika.feature.dummy.screen.home

import id.radhika.feature.dummy.data.usecases.HomeUseCase
import id.radhika.lib.mvvm.BaseVM
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
class HomeVM(
    private val homeUseCase: HomeUseCase = HomeUseCase()
) : BaseVM<HomeDao>() {

    override suspend fun onCreate() {
        fetchData()
    }

    override fun onCreateDao() = HomeDao()

    private suspend fun fetchData() {
        dao.isLoading = true

        delay(5000)

        homeUseCase.getHomeData().let {
            if (it.isSuccess) {
                dao.name = it.data ?: ""
                showToast("Successfully")
            } else {
                showToast(it.message)
            }
        }

        dao.isLoading = false
    }

    fun calculate(inc: Boolean) = launch {
        delay(1000)
        dao.count += if (inc) 1 else -1
    }
}