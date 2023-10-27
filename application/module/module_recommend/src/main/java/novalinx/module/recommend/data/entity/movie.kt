package novalinx.module.recommend.data.entity

import java.sql.Time

/**
 * @ClassName movies
 * @Author zwy
 * @Description TODD
 */

data class MoviesList(
    var movies: List<MovieItem>,
    var pagination: Pagination
)

data class MovieItem(
    var data: Time,
    var id: Int,
    var img: String,
    var title: String,
    var tag:List<String>
)

data class Pagination(
    var currentPage: Int,
    var hasNextPage: Boolean,
    var nextPage: Int,
    var pages: List<Int>
)