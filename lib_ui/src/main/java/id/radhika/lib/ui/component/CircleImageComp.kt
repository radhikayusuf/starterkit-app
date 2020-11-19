package id.radhika.lib.ui.component

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 05/Jul/2020
 **/
open class CircleImageComp @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : CircleImageView(context, attrs, defStyle) {

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth;
        setMeasuredDimension(width, width);
    }

    open fun setImageUrl(url: String) {
        if (url.isBlank()) return
        val finalUrl =
            if (url.startsWith("http://") || url.startsWith("https://")) url else "https://${url}"
        Glide.with(this)
            .load(finalUrl)
            .into(this)
    }

    open fun setImageFile(url: File?) {
        Glide.with(this)
            .load(url)
            .into(this)
    }
}