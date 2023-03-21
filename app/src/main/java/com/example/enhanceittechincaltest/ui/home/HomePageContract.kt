package com.example.enhanceittechincaltest.ui.home

import com.example.enhanceittechincaltest.data.domain.tvshow.model.ModelTvShow

class HomePageContract {
    sealed class Event {
        data class SearchTvShow(val query: String = "girls") : Event()
    }

    sealed class State {
        data class HomePageData(val detail: ModelTvShow) : State()
    }
}