package id.radhika.lib.data

import android.text.Editable
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 03/Jul/2020
 **/

fun String?.toNullableRequestBody(): RequestBody? {
    this?.let {
        return RequestBody.create(MediaType.parse("text/plain"), it)
    }
    return null
}

fun Double?.toNullableRequestBody(): RequestBody? {
    this?.let {
        return RequestBody.create(MediaType.parse("text/plain"), it.toString())
    }
    return null
}

fun Int?.toNullableRequestBody(): RequestBody? {
    this?.let {
        return RequestBody.create(MediaType.parse("text/plain"), it.toString())
    }
    return null
}

fun String?.toEditable() = Editable.Factory.getInstance().newEditable(this.orEmpty())

fun String?.toHeaderAuth(): String {
    return "Bearer ${this.orEmpty()}"
}

fun String?.toRequestBody(): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), this ?: "")
}

fun Int?.toRequestBody(): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), (this ?: 0).toString())
}

fun File?.toRequestBody(): RequestBody {
    return RequestBody.create(MediaType.parse("image/*"), this ?: File(""))
}

fun File?.nullableRequestBody(): RequestBody? {
    return if (this == null) {
        null
    } else {
        RequestBody.create(MediaType.parse("image/*"), this)
    }
}

fun Double?.toRequestBody(): RequestBody {
    return RequestBody.create(MediaType.parse("text/plain"), (this ?: 0.0).toString())
}