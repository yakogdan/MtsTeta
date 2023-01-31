package com.yakogdan.data.mappers

import com.yakogdan.data.remote.entities.moviedetails.MovieDetailsRemote
import com.yakogdan.domain.entities.moviedetails.MovieDetailsDomain

object MovieDetailMapper {

    fun mapRemoteToDomain(movieDetail: MovieDetailsRemote): MovieDetailsDomain =
        MovieDetailsDomain(
            id = movieDetail.id,
            title = movieDetail.title,
            description = movieDetail.description,
            adult = movieDetail.adult,
            genres = MovieGenreMapper.mapGenreRmToMovieGenreDmList(movieDetail.genres),
            posterPath = movieDetail.posterPath,
            releaseDate = movieDetail.releaseDate,
            voteAverage = movieDetail.voteAverage
        )
}