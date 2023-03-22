package com.example.enhanceittechincaltest.data.domain.tvshow.model

import com.example.enhanceittechincaltest.data.remote.tvshow.ModelResTvShowImage
import com.example.enhanceittechincaltest.data.remote.tvshow.ModelResTvShows

fun ModelResTvShowImage.toDomainModel()= ModelTvShowImage(
    medium,
    original
)