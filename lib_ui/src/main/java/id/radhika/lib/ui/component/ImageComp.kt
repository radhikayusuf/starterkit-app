package id.radhika.lib.ui.component

import android.content.Context
import android.util.AttributeSet
import com.bumptech.glide.Glide
import com.makeramen.roundedimageview.RoundedImageView
import java.io.File

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 05/Jul/2020
 **/
open class ImageComp @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : RoundedImageView(context, attrs, defStyle) {

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