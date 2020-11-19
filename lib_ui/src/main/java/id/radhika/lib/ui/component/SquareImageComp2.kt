package id.radhika.lib.ui.component

import android.content.Context
import android.util.AttributeSet

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 05/Jul/2020
 **/
class SquareImageComp2 @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ImageComp(context, attrs, defStyle) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val height = measuredHeight;
        setMeasuredDimension(height, height);
    }
}