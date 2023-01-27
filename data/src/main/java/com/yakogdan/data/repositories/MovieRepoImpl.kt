package com.yakogdan.data.repositories

import android.util.Log
import com.yakogdan.data.database.room.dao.MovieCardDao
import com.yakogdan.data.database.room.dao.MovieGenreDao
import com.yakogdan.data.mappers.*
import com.yakogdan.data.remote.api.TheMovieDbApi
import com.yakogdan.data.remote.entities.movieactors.MovieActorsRemote
import com.yakogdan.data.remote.entities.moviedetail.MovieDetailRemote
import com.yakogdan.data.remote.entities.popularmovies.PopularMoviesRemote
import com.yakogdan.domain.entities.MovieCardDomain
import com.yakogdan.domain.entities.MovieGenreDomain
import com.yakogdan.domain.entities.movieactors.MovieActorsDomain
import com.yakogdan.domain.entities.moviedetail.MovieDetailDomain
import com.yakogdan.domain.entities.popularmovies.PopularMoviesDomain
import com.yakogdan.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepoImpl @Inject constructor(
    private val movieCardDao: MovieCardDao,
    private val movieGenreDao: MovieGenreDao,
    private val theMovieDbApi: TheMovieDbApi
) : MovieRepository {

    // MovieCard

    override suspend fun getMovieCardsFromDB(): Flow<List<MovieCardDomain>> {
        return movieCardDao.getMovieCards()
            .map { movieCards -> movieCards.map { MovieCardMapper.mapDbToDomain(it) } }
    }

    override suspend fun getMovieCardsFromApi(): Flow<List<MovieCardDomain>> {
        val listPopularMovies = getPopularMovieApi().results
        return flowOf(listPopularMovies.map { PopularMovieMapper.mapToMovieCard(it) })
    }

    override suspend fun getPopularMovieApi(): PopularMoviesDomain {
        val popularMovies = theMovieDbApi.getPopularMovies(
            apiKey = API_KEY,
            language = LANGUAGE,
            page = 1
        ).body()
            ?: PopularMoviesRemote()

        return PopularMovieMapper.mapRemoteToDomain(popularMovies)
    }

    override suspend fun getMovieDetailsApi(movieId: Long): Flow<MovieDetailDomain> {
        val movieDetails = theMovieDbApi.getMovieDetails(
            movieId = movieId,
            apiKey = API_KEY,
            language = LANGUAGE
        ).body()
            ?: MovieDetailRemote()
        Log.d("myTAG", movieDetails.title)
        return flowOf(MovieDetailMapper.mapRemoteToDomain(movieDetails))
    }

    override suspend fun getMovieActors(movieId: Long): Flow<MovieActorsDomain> {
        val movieActors = theMovieDbApi.getMovieActors(
            movieId = movieId,
            apiKey = API_KEY,
            language = LANGUAGE
        ).body()
            ?: MovieActorsRemote()
//        Log.d("myTAG", movieActors.id.toString())
        return flowOf(MovieActorMapper.mapActorsRemoteToDomain(movieActors))
    }

    override suspend fun addMovieCards(movieCards: List<MovieCardDomain>) {
        movieCardDao.addMovieCards(MovieCardMapper.mapDomainToDbList(movieCards))
    }

    override suspend fun clearMovieCardsDB() {
        movieCardDao.clearAllDB()
    }

    override suspend fun movieCardsDbIsEmpty() =
        movieCardDao.isEmpty()


    // MovieGenre

    override suspend fun getMovieGenresFromRepo(): Flow<List<MovieGenreDomain>> = flowOf(
        listOf(
            MovieGenreDomain(id = 0, title = "боевики"),
            MovieGenreDomain(id = 1, title = "драмы"),
            MovieGenreDomain(id = 2, title = "комедии"),
            MovieGenreDomain(id = 3, title = "артхаус"),
            MovieGenreDomain(id = 4, title = "мелодрамы"),
            MovieGenreDomain(id = 5, title = "ужасы"),
            MovieGenreDomain(id = 6, title = "фантастика"),
            MovieGenreDomain(id = 7, title = "документальные")
        )
    )

    override suspend fun getMovieGenresFromDB(): Flow<List<MovieGenreDomain>> {
        return movieGenreDao.getMovieGenres()
            .map { movieGenres -> movieGenres.map { MovieGenreMapper.mapDbToDomain(it) } }
    }

    override suspend fun addMovieGenre(movieGenre: MovieGenreDomain) {
        movieGenreDao.addMovieGenre(MovieGenreMapper.mapDomainToDb(movieGenre))
    }

    override suspend fun addMovieGenres(movieGenres: List<MovieGenreDomain>) {
        movieGenreDao.addMovieGenres(MovieGenreMapper.mapDomainToDbList(movieGenres))
    }

    override suspend fun clearMovieGenresDB() {
        movieGenreDao.clearAllDB()
    }

    override suspend fun movieGenresDbIsEmpty() =
        movieGenreDao.isEmpty()

    companion object {
        private const val API_KEY = "dc2f4ef0c7ea7f640e6cf723c2585a92"
        private const val LANGUAGE = "ru-RU"
    }
}
