package com.example.enhanceittechincaltest.data.domain.tvshow.model


import com.example.enhanceittechincaltest.data.remote.tvshow.ModelResTvShows
import com.example.enhanceittechincaltest.utils.core.Resource
import retrofit2.Response
import java.util.*

fun ModelResTvShows.toDomainModel()=ModelTvShow(
    id,
    name,
    premiered,
    image
)

fun Resource<ModelResTvShows>.toDomainModel()=Resource<ModelTvShow>(
    status,
    data?.toDomainModel(),
    message)


fun ModelTvShow.getNoOfDaysSincePriemere() = run {
    ""+(((((Date().time-premiered.time)/1000)/60)/60)/24)+" Days"
}