package github.abbasbanisaeed.digitoon_test.api

import github.abbasbanisaeed.digitoon_test.model.ResponseMoviesTop
import github.abbasbanisaeed.digitoon_test.model.detail.ResponseMovies
import github.abbasbanisaeed.digitoon_test.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {
    //topmovies
    @GET("?apikey=${Constants.API_KEY}&")
    suspend fun moviesTopList(@Query("s")query: String): Response<ResponseMoviesTop>
    // last movies
    @GET("?apikey=${Constants.API_KEY}&s=batman")
    suspend fun moviesLastList(): Response<ResponseMoviesTop>
    //detail movies
    @GET("?apikey=${Constants.API_KEY}&")
    suspend fun detailMoviesList(@Query("i")imdbID: String): Response<ResponseMovies>

}