package id.radhika.lib.data.model

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 08/Jun/2020
 **/
data class SimpleResult<T>(
    val isSuccess: Boolean,
    val data: T?,
    val message: String
)