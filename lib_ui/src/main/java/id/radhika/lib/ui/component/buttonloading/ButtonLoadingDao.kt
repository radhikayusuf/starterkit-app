package id.radhika.lib.ui.component.buttonloading

import id.radhika.lib.ui.utils.ComponentLiveDao
import id.lesprivate.lib.ui.utils.getValue
import id.lesprivate.lib.ui.utils.setValue

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
class ButtonLoadingDao {

    private val loadingData = ComponentLiveDao(false)
    var isLoading by loadingData::content

}