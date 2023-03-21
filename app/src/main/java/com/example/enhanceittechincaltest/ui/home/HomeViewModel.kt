package com.example.enhanceittechincaltest.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.enhanceittechincaltest.RepoTvShows
import com.example.enhanceittechincaltest.data.domain.tvshow.SearchTvShowsUseCase
import com.example.enhanceittechincaltest.data.domain.tvshow.model.ModelTvShow
import com.example.enhanceittechincaltest.data.remote.tvshow.ModelResTvShows
import com.example.enhanceittechincaltest.di.providers.ResourceProvider
import com.example.enhanceittechincaltest.utils.core.base.mvi.MviViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val searchTvShowsUseCase: SearchTvShowsUseCase,
    private val resourceProvider: ResourceProvider
) : MviViewModel<HomePageContract.State, HomePageContract.Event>() {

    private var tvShow: ModelTvShow? = null

    override fun onTriggerEvent(eventType: HomePageContract.Event) {
        when (eventType) {
            is HomePageContract.Event.SearchTvShow -> searchTvShow(eventType.query)
        }
    }

    private fun searchTvShow(argQuery: String) = safeLaunch{
        val params = SearchTvShowsUseCase.Params(query = argQuery)

        executeWithProgress(searchTvShowsUseCase(params)) { argTvShow ->
            this@HomeViewModel.tvShow = argTvShow
            setState(HomePageContract.State.HomePageData(argTvShow))
        }
    }
}