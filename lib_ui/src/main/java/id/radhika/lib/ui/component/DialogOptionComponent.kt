package id.radhika.lib.ui.component

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.annotation.DrawableRes
import id.radhika.lib.ui.databinding.DialogOptionComponentBinding

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 03/Jul/2020
 **/
class DialogOptionComponent(
    context: Context,
    private val title: String,
    private val subTitle: String,
    @DrawableRes private val icon: Int? = null,
    private var onCloseListener: ((Int) -> Unit)? = null
) : AlertDialog(context) {

    private val binding by lazy { DialogOptionComponentBinding.inflate(LayoutInflater.from(context)) }

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
        setOnCancelListener {
            onCloseListener?.invoke(RESULT_CLOSE)
            onCloseListener = null
        }
        setOnDismissListener {
            onCloseListener?.invoke(RESULT_CLOSE)
            onCloseListener = null
        }
        binding.buttonPositive.setOnClickListener {
            onCloseListener?.invoke(RESULT_POSITIVE)
            onCloseListener = null
            dismiss()
        }
        binding.buttonNegative.setOnClickListener {
            onCloseListener?.invoke(RESULT_NEGATIVE)
            onCloseListener = null
            dismiss()
        }
        binding.buttonClose.setOnClickListener {
            onCloseListener?.invoke(RESULT_CLOSE)
            onCloseListener = null
            dismiss()
        }
    }

    companion object {

        const val RESULT_CLOSE = 0
        const val RESULT_POSITIVE = 1
        const val RESULT_NEGATIVE = 2

        fun show(
            context: Context,
            title: String,
            subTitle: String,
            @DrawableRes icon: Int? = null,
            onCloseListener: ((Int) -> Unit)? = null
        ) {
            DialogOptionComponent(
                context,
                title,
                subTitle,
                icon,
                onCloseListener
            )
                .show()
        }


    }
}