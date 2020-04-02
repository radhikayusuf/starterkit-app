package id.radhika.app.home

import id.radhika.lib.mvvm.BaseDao
import id.radhika.lib.mvvm.util.LiveDao
import id.radhika.lib.mvvm.util.getValue
import id.radhika.lib.mvvm.util.setValue

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Mar/2020
 **/
class HomeDao : BaseDao() {

    private val loadingData = LiveDao(false)
    var isLoading by loadingData::content

    private val nameData = LiveDao("")
    var name by nameData::content

    private val countData = LiveDao(0)
    var count by countData::content

}
