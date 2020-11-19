package id.radhika.lib.ui.component

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import id.radhika.lib.ui.R
import kotlin.math.ceil


/**
 * Created by Radhika Yusuf Alifiansyah
 * on 05/Jul/2020
 **/
open class BannerComp @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : ImageComp(context, attrs, defStyle) {

    var colorCallback: ((color: Int) -> Unit)? = null

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        val width = measuredWidth;
        val ration = (7.0 / 3.0)

        val expectedHeight = width / ration
        val a = ceil(expectedHeight).toInt()
        setMeasuredDimension(width, a)
    }

    override fun setImageUrl(url: String) {
        val finalUrl = if (url.startsWith("http://") || url.startsWith("https://")) url else "https://${url}"
        Glide.with(this)
            .load(finalUrl)
            .addListener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {

                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    try {
                        resource?.let { res ->
                            val bitmap = res.toBitmap()
                            val pixel = bitmap.getPixel(1, measuredHeight / 2)

                            val redValue: Int = Color.red(pixel)
                            val blueValue: Int = Color.blue(pixel)
                            val greenValue: Int = Color.green(pixel)

                            val color = Color.rgb(redValue, greenValue, blueValue)
                            colorCallback?.invoke(color)
                        }
                    } catch (e: Exception) {
                        colorCallback?.invoke(ContextCompat.getColor(context, R.color.colorLibPrimary))
                    }
                    return false
                }
            })
            .into(this)

    }
}