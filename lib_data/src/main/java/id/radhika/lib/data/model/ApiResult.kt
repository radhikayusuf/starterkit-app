package id.radhika.lib.data.model

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
data class ApiResult<T>(
    val isSuccess: Boolean,
    val data: T? = null,
    val message: String = "",
    val rawResponse: BaseResponse<*>? = null
)