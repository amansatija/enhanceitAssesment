package com.example.enhanceittechincaltest

import com.example.enhanceittechincaltest.data.remote.TvShowRemoteDataSource
import com.example.enhanceittechincaltest.data.remote.tvshow.ModelResTvShows
import com.example.enhanceittechincaltest.utils.core.Resource
import javax.inject.Inject

class RepoTvShows @Inject constructor(
    private val remoteDataSource: TvShowRemoteDataSource
//    ,
//    private val localDataSource: TvShowLocalDataSource
) {

    suspend fun searchTvShows(query: String): Resource<ModelResTvShows> {
        return remoteDataSource.searchTvShows(query);
    }


    var listOfTvShows = ArrayList<ModelResTvShows>();
}