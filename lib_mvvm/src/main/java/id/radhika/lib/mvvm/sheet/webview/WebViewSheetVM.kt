package id.radhika.lib.mvvm.sheet.webview

import id.radhika.lib.mvvm.BaseVM
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Jun/2020
 **/
class WebViewSheetVM : BaseVM<WebViewSheetDao>() {

    var job: Job? = null

    override suspend fun onCreate() {
        delay(3000)
        dao.htmlContent = "url" to "https://www.google.com"
        dao.isLoading = true
    }

    override fun onCreateDao() = WebViewSheetDao()
}