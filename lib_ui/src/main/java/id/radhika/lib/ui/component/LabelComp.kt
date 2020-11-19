package id.radhika.lib.ui.component

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.os.Build
import android.text.Html
import android.text.Layout
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.util.Linkify
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import id.radhika.lib.ui.R
import java.text.NumberFormat
import java.util.*


/**
 * Created by Radhika Yusuf Alifiansyah
 * on 18/Jun/2020
 **/
class LabelComp @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : AppCompatTextView(context, attrs, defStyle) {

    init {
        renderFontStyle()
        attrs?.let { initAttrs(context, it) }
    }

    private fun initAttrs(context: Context, attrs: AttributeSet) {
        val selectedType: Int
        val isHtml: Boolean
        val ta = context.obtainStyledAttributes(attrs, R.styleable.LabelComp, 0, 0)
        try {
            selectedType = ta.getInt(R.styleable.LabelComp_labelType, 0)
            isHtml = ta.getBoolean(R.styleable.LabelComp_ishtml, false)
        } finally {
            ta.recycle()
        }

        when (selectedType) {
            1 -> setTextLink(isLink = true)
            2 -> setTextLink(isLink = true, withUnderline = true)
//            3 -> TODO
        }

        if (isHtml) {
            setHtmlContentText()
        }
        setLineSpacing(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                5.0f,
                resources.displayMetrics
            ), 1.0f
        )
    }

    fun setTextStyle(tf: Typeface?) {
        typeface = tf
        renderFontStyle()
    }

    private fun renderFontStyle() {
        val isItalic = typeface.isItalic
        val isBold = typeface.isBold

        val typeface = when {
            isItalic && !isBold -> {
                ResourcesCompat.getFont(context, R.font.gotham_regular_italic)
            }
            !isItalic && isBold -> {
                ResourcesCompat.getFont(context, R.font.gotham_bold)
            }
            isItalic && isBold -> {
                ResourcesCompat.getFont(context, R.font.gotham_bold_italic)
            }
            else -> {
                ResourcesCompat.getFont(context, R.font.gotham_regular)
            }
        }
        setTypeface(typeface)
    }

    private fun setHtmlContentText(content: String? = null) {
        text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(content ?: text.toString(), Html.FROM_HTML_MODE_COMPACT);
        } else {
            Html.fromHtml(content ?: text.toString());
        }
    }

    fun setHtmlText(text: String) {
        setHtmlContentText(text)
    }

    fun setRupiahFormat(price: Double, showRp: Boolean = true, extras: String = "") {
        val localeID = Locale("in", "ID")
        val numberFormat = NumberFormat.getCurrencyInstance(localeID)
        var result = numberFormat.format(price).toString()
        result = if (result.contains(",")) (result.split(",").getOrNull(0) ?: result) else result
        if (!showRp) result = result.toLowerCase().replace("rp", "")
        text = result + extras
    }

    fun setTextLink(isLink: Boolean, withUnderline: Boolean = false) {
        setTextColor(if (!isLink) 0 else ContextCompat.getColor(context, R.color.colorLibAccent))
        isFocusable = isLink
        isEnabled = isLink

        if (isLink && Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val outValue = TypedValue()
            context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
            ContextCompat.getDrawable(context, outValue.resourceId)
        }

        if (isLink && withUnderline)
            paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
        else renderFontStyle()
    }

    fun setClickWordsRes(texts: List<Int>, callback: (Int) -> Unit) {
        setClickWords(texts.map { context.getString(it) }, callback)
    }

    fun setClickWords(texts: List<String>, callback: (Int) -> Unit) {
        isClickable = true
        isEnabled = true
        val content = SpannableString(this.text.toString())
        texts.forEachIndexed { index, textContent ->
            val textIndex = content.indexOf(textContent)
            content.setSpan(
                ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorLibPrimary)),
                textIndex,
                textIndex + textContent.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            content.setSpan(
                object : ClickableSpan() {
                    override fun onClick(widget: View) {
                        callback.invoke(index)
                    }
                },
                textIndex,
                textIndex + textContent.length,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
            )
        }
        text = content
        movementMethod = LinkMovementMethod.getInstance()
        Linkify.addLinks(this, Linkify.WEB_URLS)
    }

    fun isEllipsized() =
        !(if (layout != null) {
            val linez = layout.lineCount
            (linez > 0 && layout.getEllipsisCount(linez - 1) > 0)
        } else false)
}