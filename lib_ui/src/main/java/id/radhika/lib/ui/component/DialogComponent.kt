package id.radhika.lib.ui.component

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.annotation.DrawableRes
import id.radhika.lib.ui.databinding.DialogComponentBinding

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 03/Jul/2020
 **/
class DialogComponent(
    context: Context,
    private val title: String,
    private val subTitle: String,
    @DrawableRes private val icon: Int? = null,
    private var onCloseListener: ((Boolean) -> Unit)? = null,
    private val clickableContent: ClickableBody?
) : AlertDialog(context) {

    private val binding by lazy { DialogComponentBinding.inflate(LayoutInflater.from(context)) }

    override fun onCreate(savedInstanceState: Bundle?) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        icon?.let {
            binding.imageContent.visibility = View.VISIBLE
            binding.imageContent.setImageResource(it)
        }

        binding.title.text = title
        binding.subTitle.text = subTitle

        if (clickableContent != null) {
            binding.labelClick.visibility = View.VISIBLE
            binding.buttonOk.visibility = View.GONE
            binding.labelClick.text = clickableContent.text
            binding.labelClick.setClickWords(listOf(clickableContent.clickableText)) {
                clickableContent.callback.invoke()
                if (clickableContent.dismissAfterClick) {
                    dismiss()
                }
            }
        } else {
            binding.labelClick.visibility = View.GONE
            binding.buttonOk.visibility = View.VISIBLE
        }

        setOnCancelListener {
            onCloseListener?.invoke(false)
            onCloseListener = null
        }
        setOnDismissListener {
            onCloseListener?.invoke(false)
            onCloseListener = null
        }
        binding.buttonOk.setOnClickListener {
            onCloseListener?.invoke(true)
            onCloseListener = null
            dismiss()
        }
        binding.buttonClose.setOnClickListener {
            onCloseListener?.invoke(false)
            onCloseListener = null
            dismiss()
        }
    }

    companion object {

        fun show(
            context: Context,
            title: String,
            subTitle: String,
            @DrawableRes icon: Int? = null,
            content: ClickableBody? = null,
            onCloseListener: ((Boolean) -> Unit)? = null
        ) {
            DialogComponent(
                context,
                title,
                subTitle,
                icon,
                onCloseListener,
                content
            ).show()
        }


    }

    data class ClickableBody(
        val text: String,
        val clickableText: String,
        val dismissAfterClick: Boolean = true,
        val callback: () -> Unit
    )
}