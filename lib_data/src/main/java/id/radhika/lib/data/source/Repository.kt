package id.radhika.lib.data.source

import id.radhika.lib.data.model.BaseResponse

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
abstract class Repository<T> {

    abstract suspend fun fetchData(): BaseResponse<T>

}