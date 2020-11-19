package id.radhika.lib.ui.component

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.ContextThemeWrapper
import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import id.radhika.lib.ui.R
import android.R as AndroidR


/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
class ButtonLoadingComp @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val progressBar = ProgressBar(ContextThemeWrapper(context, R.style.ProgressMain), null, AndroidR.attr.progressBarStyleSmall)
    private val buttonContent = LabelComp(context)
    private var onClickListener: OnClickListener? = null

    init {
        renderComponent(context)
        attrs?.let { initAttrs(context, it) }
    }

    private fun initAttrs(context: Context, attrs: AttributeSet) {
        val text: String
        val textSize: Int
        val type: Int
        val allCaps: Boolean
        val disabled: Boolean
        val buttonBackground: Int
        val ta = context.obtainStyledAttributes(attrs, R.styleable.ButtonLoadingComp, 0, 0)
        try {
            text = ta.getString(R.styleable.ButtonLoadingComp_text) ?: ""
            textSize = ta.getInt(R.styleable.ButtonLoadingComp_textSize, 0)
            type = ta.getInt(R.styleable.ButtonLoadingComp_type, 0)
            allCaps = ta.getBoolean(R.styleable.ButtonLoadingComp_allCaps, false)
            disabled = ta.getBoolean(R.styleable.ButtonLoadingComp_disabled, false)
            buttonBackground = ta.getResourceId(R.styleable.ButtonLoadingComp_buttonBackground, 0)
        } finally {
            ta.recycle()
        }

        isEnabled = disabled.not()
        buttonContent.isEnabled = disabled.not()
        buttonContent.text = text
        buttonContent.textSize = textSize.toFloat()
        buttonContent.isAllCaps = allCaps


        if (type == 2) {
            buttonContent.setTextColor(ContextCompat.getColor(context, R.color.colorLibPrimary))
            background = ContextCompat.getDrawable(context, R.drawable.background_secondary_button)
        }

        if (buttonBackground != 0) {
            background = ContextCompat.getDrawable(context, buttonBackground)
        }

        progressBar.setPadding(
            resources.getDimension(R.dimen.dimens_2dp).toInt(),
            resources.getDimension(R.dimen.dimens_2dp).toInt(),
            resources.getDimension(R.dimen.dimens_2dp).toInt(),
            resources.getDimension(R.dimen.dimens_2dp).toInt()
        )
    }

    private fun renderComponent(context: Context) {
        buttonContent.run {
            id = R.id.text
            gravity = Gravity.CENTER
            setTextColor(Color.WHITE)
            setTextStyle(Typeface.DEFAULT_BOLD)
            textSize = 16f
            addView(this@run)
        }
        progressBar.run {
            id = R.id.progress
            visibility = GONE
            addView(this@run)
        }

        val outValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, outValue, true)
        isClickable = true
        isFocusable = true
        foreground = ContextCompat.getDrawable(context, outValue.resourceId)
        if (background == null) {
            background = ContextCompat.getDrawable(context, R.drawable.background_primary_button_layer)
        }
        setPadding(
            resources.getDimension(R.dimen.dimens_16dp).toInt(),
            resources.getDimension(R.dimen.dimens_8dp).toInt(),
            resources.getDimension(R.dimen.dimens_16dp).toInt(),
            resources.getDimension(R.dimen.dimens_8dp).toInt()
        )
    }

    override fun setOnClickListener(l: OnClickListener?) {
        if (l != null) onClickListener = l
        super.setOnClickListener(l)
    }

    fun setText(text: String) {
        buttonContent.text = text
    }

    fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            setOnClickListener(null)
            progressBar.visibility = View.VISIBLE
            buttonContent.visibility = View.INVISIBLE
        } else {
            setOnClickListener(onClickListener)
            progressBar.visibility = View.INVISIBLE
            buttonContent.visibility = View.VISIBLE
        }
    }
}