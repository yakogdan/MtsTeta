package com.yakogdan.data.repositories

import com.yakogdan.data.database.room.dao.MovieCardDao
import com.yakogdan.data.database.room.dao.MovieGenreDao
import com.yakogdan.data.mappers.MovieActorMapper
import com.yakogdan.data.mappers.MovieCardMapper
import com.yakogdan.data.mappers.MovieDetailsMapper
import com.yakogdan.data.mappers.MovieGenreMapper
import com.yakogdan.data.remote.api.TheMovieDbApi
import com.yakogdan.data.remote.entities.movieactors.MovieActorsRemote
import com.yakogdan.data.remote.entities.moviecards.MovieCardsRemote
import com.yakogdan.data.remote.entities.moviedetails.MovieDetailsRemote
import com.yakogdan.domain.entities.movieactors.MovieActorDomain
import com.yakogdan.domain.entities.moviecards.MovieCardDomain
import com.yakogdan.domain.entities.moviedetails.MovieDetailsDomain
import com.yakogdan.domain.entities.moviegenres.MovieGenreDomain
import com.yakogdan.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextInt

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
        val response = theMovieDbApi.getPopularMovies(
            apiKey = API_KEY,
            language = LANGUAGE,
            page = Random.nextInt(1..9)
        ).body()
            ?: MovieCardsRemote()

        return flowOf(MovieCardMapper.mapRemoteToDomainList(response.results))
    }

    override suspend fun getMovieDetailsFromApi(movieId: Long): Flow<MovieDetailsDomain> {
        val response = theMovieDbApi.getMovieDetails(
            movieId = movieId,
            apiKey = API_KEY,
            language = LANGUAGE
        ).body()
            ?: MovieDetailsRemote()
        return flowOf(MovieDetailsMapper.mapRemoteToDomain(response))
    }

    override suspend fun getMovieActorsFromApi(movieId: Long): Flow<List<MovieActorDomain>> {
        val response = theMovieDbApi.getMovieActors(
            movieId = movieId,
            apiKey = API_KEY,
            language = LANGUAGE
        ).body()
            ?: MovieActorsRemote()
        return flowOf(MovieActorMapper.mapActorRemoteToDomainList(response.actors))
    }

    override suspend fun addMovieCard(movieCard: MovieCardDomain) {
        movieCardDao.addMovieCard(MovieCardMapper.mapDomainToDb(movieCard))
    }

    override suspend fun deleteMovieCard(movieCard: MovieCardDomain) {
        movieCardDao.deleteMovieCard(MovieCardMapper.mapDomainToDb(movieCard))
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

    override suspend fun addMovieGenres(movieGenres: List<MovieGenreDomain>) {
        movieGenreDao.addMovieGenres(MovieGenreMapper.mapDomainToDbList(movieGenres))
    }

    override suspend fun movieGenresDbIsEmpty() =
        movieGenreDao.isEmpty()

    companion object {
        private const val API_KEY = "dc2f4ef0c7ea7f640e6cf723c2585a92"
        private const val LANGUAGE = "ru-RU"
    }
}
