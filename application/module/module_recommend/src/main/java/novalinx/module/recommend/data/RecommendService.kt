package novalinx.module.recommend.data

import novalinx.module.recommend.data.entity.MoviesList
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * @ClassName RecommendService
 * @Author zwy
 * @Description TODD
 */
interface RecommendService {

    @GET("/api/v1/movies")
    @Headers("j-auth-token:j-auth-token")
    suspend fun getMovies(@Query("page") page: Int,
                          @Query("magnet") magnet:String,
                          @Query("filterType")filterType:String?,
                          @Query("filterValue")filterValue:String?,
                          @Query("type") type:String?): MoviesList
}