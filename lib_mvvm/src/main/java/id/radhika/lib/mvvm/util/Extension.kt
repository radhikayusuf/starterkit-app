package id.radhika.lib.mvvm.util

import android.widget.Toast
import androidx.annotation.StringRes
import id.radhika.lib.mvvm.BaseScreen

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 05/Apr/2020
 **/

fun BaseScreen<*, *, *>.showToast(value: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), value, length).show()
}

fun BaseScreen<*, *, *>.showToast(@StringRes value: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(requireContext(), value, length).show()
}