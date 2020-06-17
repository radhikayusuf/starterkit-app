package id.lesprivate.lib.ui.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/
object EditTextHelper {

    fun addTypingListener(vararg editTexts: EditText, callback: (Int, String) -> Unit) {
        editTexts.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                    callback.invoke(index, s?.toString().orEmpty())
                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            })
        }
    }

    fun resetAllError(vararg editTexts: EditText) {
        editTexts.forEach { it.error = null }
    }

    fun resetAllError(vararg editTexts: TextInputLayout) {
        editTexts.forEach { it.error = null }
    }
}