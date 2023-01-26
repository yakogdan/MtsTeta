package com.yakogdan.data.mappers

import com.yakogdan.data.remote.entities.moviedetail.MovieDetailRemote
import com.yakogdan.domain.entities.moviedetail.MovieDetailDomain

object MovieDetailMapper {

    fun mapRemoteToDomain(movieDetail: MovieDetailRemote): MovieDetailDomain =
        MovieDetailDomain(
            adult = movieDetail.adult,
            genres = MovieGenreMapper.mapGenreRmToMovieGenreDmList(movieDetail.genres),
            id = movieDetail.id,
            overview = movieDetail.overview,
            poster_path = movieDetail.poster_path,
            release_date = movieDetail.release_date,
            title = movieDetail.title,
            vote_average = movieDetail.vote_average
        )
}