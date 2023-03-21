package com.example.enhanceittechincaltest.data.domain.tvshow.model

import com.example.enhanceittechincaltest.data.remote.tvshow.ModelResTvShowImage
import java.util.*
import kotlin.collections.ArrayList

data class ModelTvShow(var id:Int,
                       var name:String,
                       var premiered: Date,
                       var image:ModelResTvShowImage) {
}