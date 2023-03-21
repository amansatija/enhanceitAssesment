package com.example.enhanceittechincaltest.data.remote
import com.example.enhanceittechincaltest.data.remote.tvshow.ModelResTvShows
import com.example.enhanceittechincaltest.utils.api.ApiService
import com.example.enhanceittechincaltest.utils.core.Resource
import javax.inject.Inject

class TvShowRemoteDataSource @Inject constructor(
    private val apiService: ApiService
): BaseDataSource() {

    var mHmCacheOfResponses = HashMap<String, Resource<ModelResTvShows>>()
    var boolIsCacheDirty = false;
    suspend fun searchTvShows(query: String) : Resource<ModelResTvShows> {
        if(boolIsCacheDirty ||
            !mHmCacheOfResponses.containsKey(query)){
            val result = executeRemoteReq { apiService.searchTvShows(query) }
            mHmCacheOfResponses.set(query,result)
            return   result;
        }else{
            return mHmCacheOfResponses[query]!!
        }

    }
}