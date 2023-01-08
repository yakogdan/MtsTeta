package com.yakogdan.data.repositories

import com.yakogdan.data.database.room.dao.MovieCardDao
import com.yakogdan.data.database.room.dao.MovieGenreDao
import com.yakogdan.data.mappers.MovieCardMapper
import com.yakogdan.data.mappers.MovieGenreMapper
import com.yakogdan.domain.entities.MovieCardDomainEntity
import com.yakogdan.domain.entities.MovieGenreDomainEntity
import com.yakogdan.domain.repositories.MovieListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieListRepoImpl @Inject constructor(
    private val movieCardDao: MovieCardDao,
    private val movieGenreDao: MovieGenreDao
) : MovieListRepository {

    // MovieCard

    override suspend fun getMovieCardsFromRepo(): Flow<List<MovieCardDomainEntity>> = flowOf(
        listOf(
            // Почему-то изображения стали открываться только с vpn
            MovieCardDomainEntity(
                id = 0,
                title = "Гнев человеческий",
                description = "Эйч — загадочный и холодный на вид джентльмен, но внутри него пылает жажда справедливости. Преследуя...",
                rateScore = 3.0,
                ageRestriction = 18,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5JP9X5tCZ6qz7DYMabLmrQirlWh.jpg"
            ),
            MovieCardDomainEntity(
                id = 1,
                title = "Мортал Комбат",
                description = "Боец смешанных единоборств Коул Янг не раз соглашался проиграть за деньги. Он не знает о своем наследии...",
                rateScore = 5.0,
                ageRestriction = 18,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/pMIixvHwsD5RZxbvgsDSNkpKy0R.jpg"
            ),
            MovieCardDomainEntity(
                id = 2,
                title = "Упс... Приплыли!",
                description = "От Великого потопа зверей спас ковчег. Но спустя полгода скитаний они готовы сбежать с него куда угодно...",
                rateScore = 5.0,
                ageRestriction = 6,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/546RNYy9Wi5wgboQ7EtD6i0DY5D.jpg"
            ),
            MovieCardDomainEntity(
                id = 3,
                title = "The Box",
                description = "Уличный музыкант знакомится с музыкальным продюсером, и они вдвоём отправляются в путешествие...",
                rateScore = 4.0,
                ageRestriction = 12,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/fq3DSw74fAodrbLiSv0BW1Ya4Ae.jpg"
            ),
            MovieCardDomainEntity(
                id = 4,
                title = "Сага о Дэнни Эрнандесе",
                description = "Tekashi69 или Сикснайн — знаменитый бруклинский рэпер с радужными волосами — прогремел...",
                rateScore = 2.0,
                ageRestriction = 18,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/5xXGQLVtTAExHY92DHD9ewGmKxf.jpg"
            ),
            MovieCardDomainEntity(
                id = 5,
                title = "Пчелка Майя",
                description = "Когда упрямая пчелка Майя и ее лучший друг Вилли спасают принцессу-муравьишку, начинается сказочное...",
                rateScore = 4.0,
                ageRestriction = 0,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/xltjMeLlxywym14NEizl0metO10.jpg"
            ),
            MovieCardDomainEntity(
                id = 6,
                title = "Круэлла",
                description = "Невероятно одаренная мошенница по имени Эстелла решает сделать себе имя в мире моды...",
                rateScore = 4.0,
                ageRestriction = 12,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/hUfyYGP9Xf6cHF9y44JXJV3NxZM.jpg"
            ),
            MovieCardDomainEntity(
                id = 7,
                title = "Чёрная вдова",
                description = "Чёрной Вдове придется вспомнить о том, что было в её жизни задолго до присоединения к команде Мстителей",
                rateScore = 3.0,
                ageRestriction = 16,
                posterUrl = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/mbtN6V6y5kdawvAkzqN4ohi576a.jpg"
            )
        )
    )

    override suspend fun getMovieCardsFromDB(): Flow<List<MovieCardDomainEntity>> {
        return movieCardDao.getMovieCards()
            .map { movieCards -> movieCards.map { MovieCardMapper.mapDbToDomain(it) } }
    }

    override suspend fun addMovieCard(movieCard: MovieCardDomainEntity) {
        movieCardDao.addMovieCard(MovieCardMapper.mapDomainToDbWithoutId(movieCard))
    }

    override suspend fun addMovieCards(movieCards: List<MovieCardDomainEntity>) {
        movieCardDao.addMovieCards(MovieCardMapper.mapDomainToDbWithoutIdList(movieCards))
    }

    // MovieGenre

    override suspend fun getMovieGenresFromRepo(): Flow<List<MovieGenreDomainEntity>> = flowOf(
        listOf(
            MovieGenreDomainEntity(id = 0, title = "боевики"),
            MovieGenreDomainEntity(id = 1, title = "драмы"),
            MovieGenreDomainEntity(id = 2, title = "комедии"),
            MovieGenreDomainEntity(id = 3, title = "артхаус"),
            MovieGenreDomainEntity(id = 4, title = "мелодрамы"),
            MovieGenreDomainEntity(id = 5, title = "ужасы"),
            MovieGenreDomainEntity(id = 6, title = "фантастика"),
            MovieGenreDomainEntity(id = 7, title = "документальные")
        )
    )

    override suspend fun getMovieGenresFromDB(): Flow<List<MovieGenreDomainEntity>> {
        return movieGenreDao.getMovieGenres()
            .map { movieGenres -> movieGenres.map { MovieGenreMapper.mapDbToDomain(it) } }
    }

    override suspend fun addMovieGenre(movieGenre: MovieGenreDomainEntity) {
        movieGenreDao.addMovieGenre(MovieGenreMapper.mapDomainToDbDbWithoutId(movieGenre))
    }

    override suspend fun addMovieGenres(movieGenres: List<MovieGenreDomainEntity>) {
        movieGenreDao.addMovieGenres(MovieGenreMapper.mapDomainToDbWithoutIdList(movieGenres))
    }
}
