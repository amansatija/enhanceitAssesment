package com.example.enhanceittechincaltest.data.remote
import com.example.enhanceittechincaltest.utils.api.ApiService
import javax.inject.Inject

class TvShowRemoteDataSource @Inject constructor(
    private val apiService: ApiService
): BaseDataSource() {

    suspend fun searchTvShows(query: String) = executeRemoteReq { apiService.searchTvShows(query) }
}