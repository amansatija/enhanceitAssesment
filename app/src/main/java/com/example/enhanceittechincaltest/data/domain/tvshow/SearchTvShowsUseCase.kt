package com.example.enhanceittechincaltest.data.domain.tvshow

import com.example.enhanceittechincaltest.data.domain.tvshow.model.ModelTvShow
import com.example.enhanceittechincaltest.data.domain.tvshow.model.toDomainModel
import com.example.enhanceittechincaltest.utils.core.Resource
import com.example.enhanceittechincaltest.utils.core.usecase.ResourceUseCase
import kotlinx.coroutines.flow.FlowCollector
import javax.inject.Inject

class SearchTvShowsUseCase @Inject constructor(internal val repository: RepoTvShows) :
    ResourceUseCase<SearchTvShowsUseCase.Params,ModelTvShow>()  {

    data class Params(val query: String)

    override suspend fun FlowCollector<Resource<ModelTvShow>>.execute(params: Params) {
        val result = repository.searchTvShows(params.query)
        emit(result.toDomainModel())
    }
}