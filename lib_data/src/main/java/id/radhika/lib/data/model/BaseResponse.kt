package id.radhika.lib.data.model

import com.google.gson.Gson
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
data class BaseResponse<T>(
    val code: Int,
    val data: T?,
    val message: String
)

fun <T> getExceptionResponse(e: Exception): ApiResult<T> {
    e.printStackTrace()
    when (e) {
        is HttpException -> {
            val code = e.code()
            var msg = e.message()
            val baseDao: BaseResponse<T?>?
            try {
                val body = e.response()?.errorBody()
                baseDao = Gson().fromJson<BaseResponse<T?>>(body!!.string(), BaseResponse::class.java)
                msg = baseDao?.message ?: ""
            } catch (exception: java.lang.Exception) {
                return when (exception) {
                    is UnknownHostException -> ApiResult(false, message = "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}", data = null)
                    is SocketTimeoutException -> ApiResult(false, message = "Telah terjadi kesalahan ketika koneksi ke server: ${e.message}", data =null)
                    else -> ApiResult(false, message = "Terjadi kesalahan pada server.", data = null)
                }
            }

            when (code) {
                504 -> {
                    msg = baseDao?.message ?: "Error Response"
                }
                502, 404 -> {
                    msg = baseDao?.message ?: "Error Connect or Resource Not Found"
                }
                400 -> {
                    msg = baseDao?.message ?: "Bad Request"
                }
            }

            return ApiResult( false, null, msg)
        }
        is UnknownHostException -> return ApiResult(false,  null,"Telah terjadi kesalahan ketika koneksi ke server: ${e.message}")
        is SocketTimeoutException -> return ApiResult(false, null,"Telah terjadi kesalahan ketika koneksi ke server: ${e.message}")
        is ConnectException -> return ApiResult(false, null,"Telah terjadi kesalahan ketika koneksi ke server: ${e.message}")
        else -> return ApiResult(false, null, "Unknown error occured")
    }
}