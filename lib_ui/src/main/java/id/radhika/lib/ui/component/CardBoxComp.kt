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
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import id.radhika.lib.ui.R
import android.R as AndroidR


/**
 * Created by Radhika Yusuf Alifiansyah
 * on 22/May/2020
 **/
class CardBoxComp @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : CardView(context, attrs, defStyle) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth;
        setMeasuredDimension(width, width);
    }
}