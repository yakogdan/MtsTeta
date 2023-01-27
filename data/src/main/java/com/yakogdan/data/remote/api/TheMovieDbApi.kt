package com.yakogdan.data.remote.api

import com.yakogdan.data.remote.entities.movieactors.MovieActorsRemote
import com.yakogdan.data.remote.entities.moviedetail.MovieDetailRemote
import com.yakogdan.data.remote.entities.popularmovies.PopularMoviesRemote
import com.yakogdan.data.remote.utils.RetrofitExtensions.Companion.addJsonConverter
import com.yakogdan.data.remote.utils.RetrofitExtensions.Companion.setClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDbApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Response<PopularMoviesRemote>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<MovieDetailRemote>

    @GET("movie/{movie_id}/credits")
    suspend fun getMovieActors(
        @Path("movie_id") movieId: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String
    ): Response<MovieActorsRemote>

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"

        fun create(): TheMovieDbApi {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .setClient()
                .addJsonConverter()
                .build()
                .create(TheMovieDbApi::class.java)
        }
    }
}