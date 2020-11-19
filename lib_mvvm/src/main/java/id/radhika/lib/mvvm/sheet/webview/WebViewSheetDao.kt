package id.radhika.lib.mvvm.sheet.webview

import id.radhika.lib.mvvm.BaseDao
import id.radhika.lib.mvvm.util.LiveDao
import id.radhika.lib.mvvm.util.getValue
import id.radhika.lib.mvvm.util.setValue

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Jun/2020
 **/
class WebViewSheetDao : BaseDao() {

    private val isLoadingData = LiveDao(false)
    var isLoading by isLoadingData::content

    private val progressData = LiveDao(-1)
    var progress by progressData::content

    private val htmlContentData = LiveDao("url" to "")
    var htmlContent by htmlContentData::content
}