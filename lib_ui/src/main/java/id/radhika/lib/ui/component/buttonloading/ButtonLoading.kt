package id.radhika.lib.ui.component.buttonloading

import android.content.Context
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import id.radhika.lib.ui.utils.ComponentLiveDao
import id.radhika.lib.ui.utils.viewinvoker.ComponentDataCallback
import id.radhika.lib.ui.utils.viewinvoker.ViewInvoker
import id.radhika.lib.ui.utils.viewinvoker.ViewInvokerImpl

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
class ButtonLoading(context: Context) : FrameLayout(context), ComponentDataCallback<ButtonLoadingDao> {

    private val contentData = ButtonLoadingDao()
    private val progressBar = ProgressBar(context)
    private val buttonContent = TextView(context)

    init {
        renderComponent()
        ViewInvokerImpl(this)
    }

    private fun renderComponent() {
        addView(buttonContent)
        addView(progressBar)
    }

    fun showLoading(isLoading: Boolean) {
        contentData.isLoading = isLoading
    }

    override fun render(data: ComponentLiveDao<ButtonLoadingDao>) {
        renderLoading(data.content)
    }

    private fun renderLoading(content: ButtonLoadingDao) {
        if (content.isLoading) {
            progressBar.visibility = View.VISIBLE
            buttonContent.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            buttonContent.visibility = View.VISIBLE
        }
    }
}