package novalinx.module.recommend.data

import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @ClassName RecommendService
 * @Author zwy
 * @Description TODD
 */
interface RecommendService {

    @GET("/api/v1/movies")
    suspend fun getMovies(@Query("page") page: Int,
                          @Query("magnet") magnet:String,
                          @Query("filterType")filterType:String,
                          @Query("filterValue")filterValue:String,
                          @Query("type") type:String): List<String>
}