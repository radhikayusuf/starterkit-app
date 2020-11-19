package id.radhika.lib.mvvm.sheet.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import id.radhika.lib.mvvm.BaseScreen
import id.radhika.lib.mvvm.databinding.ScreenSheetWebviewBinding
import id.radhika.lib.mvvm.sheet.BaseSheetScreen
import kotlinx.android.synthetic.main.screen_sheet_webview.*


/**
 * Created by Radhika Yusuf Alifiansyah
 * on 21/Jun/2020
 **/
class WebViewSheetScreen :
    BaseSheetScreen<ScreenSheetWebviewBinding, WebViewSheetVM, WebViewSheetDao>(
        ScreenSheetWebviewBinding::inflate
    ) {

    private val title by lazy { arguments?.getString("title", "") ?: "" }
    private val subTitle by lazy { arguments?.getString("subTitle", "") ?: "" }
    private val image by lazy { arguments?.getString("image", "") ?: "" }
    private val content by lazy { arguments?.getString("content", "") ?: "" }
    private val isWebView by lazy { arguments?.getBoolean("webview", false) ?: false }
    private val isUrl by lazy { arguments?.getBoolean("url", false) ?: false }

    override fun onViewReady() {
        binding.toolbarWebView.title = title
        binding.toolbarWebView.showNotification(false)
        binding.toolbarWebView.showBackIcon(true) { dismiss() }

        if (isWebView) {
            renderInitiateWebView()
        } else {
            renderRichText()
        }
    }

    private fun renderRichText() {
        if (image.isNotBlank()) {
            binding.imageContent.visibility = View.VISIBLE
            binding.imageContent.setImageUrl(image)
        }
        binding.labelTitle.text = title
        binding.labelSubTitle.text = subTitle
        if (subTitle.isEmpty()) binding.labelSubTitle.visibility = View.GONE
        binding.frameTextRich.visibility = View.VISIBLE
        binding.frameBrowser.visibility = View.GONE
        binding.labelSheet.setHtmlText(content)
    }

    private fun renderInitiateWebView() {
        binding.frameBrowser.visibility = View.VISIBLE
        binding.frameTextRich.visibility = View.GONE
        binding.webViewContent.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                title?.let { binding.toolbarWebView.title = it }
            }

            override fun onProgressChanged(view: WebView, progress: Int) {
                binding.progressWeb.progress = progress.takeIf { it != -1 } ?: 0
            }
        }
        binding.webViewContent.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView, url: String) {
                binding.progressWeb.visibility = View.GONE
            }
        }
        binding.webViewContent.settings.javaScriptEnabled = true

        if (isUrl) {
            binding.webViewContent.loadUrl(content)
        } else {
            binding.webViewContent.loadData(content, "text/html", null)
        }
    }

    override fun render() = { data: WebViewSheetDao -> }

    override fun getViewModel() = WebViewSheetVM::class.java

    companion object {

        fun openSheet(
            screen: Fragment,
            title: String,
            content: String,
            isWebview: Boolean,
            subTitle: String = "",
            image: String = ""
        ) {
            screen.apply {
                WebViewSheetScreen().apply {
                    arguments = Bundle().also {
                        it.putString("title", title)
                        it.putString("subTitle", subTitle)
                        it.putString("image", image)
                        it.putString("content", content)
                        it.putBoolean("webview", isWebview)
                        it.putBoolean("url", content.startsWith("http"))
                    }
                }.show(this@apply.childFragmentManager, "")
            }
        }
    }
}