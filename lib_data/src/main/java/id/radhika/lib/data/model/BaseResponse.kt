package id.radhika.lib.data.model

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
data class BaseResponse<T>(
    val code: Int,
    val data: T?,
    val message: String
)