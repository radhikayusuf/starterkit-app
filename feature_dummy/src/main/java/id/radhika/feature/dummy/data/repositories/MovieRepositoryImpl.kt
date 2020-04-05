package id.radhika.feature.dummy.data.repositories

import id.radhika.lib.data.model.BaseResponse
import kotlin.random.Random

/**
 * Created by Radhika Yusuf Alifiansyah
 * on 04/Apr/2020
 **/
class MovieRepositoryImpl : MovieRepository() {

    override suspend fun fetchData(): BaseResponse<String> {
        val random = Random(100)
        return if (random.nextInt() % 2 == 0) {
            BaseResponse(200, "Avengers", "success")
        } else {
            BaseResponse(422, null, "error fetch data from the server")
        }

    }
}