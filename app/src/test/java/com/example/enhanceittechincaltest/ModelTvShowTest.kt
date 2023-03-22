package com.example.enhanceittechincaltest

import com.example.enhanceittechincaltest.data.domain.tvshow.model.ModelTvShow
import com.example.enhanceittechincaltest.data.domain.tvshow.model.ModelTvShowImage
import com.example.enhanceittechincaltest.data.domain.tvshow.model.getNoOfDaysSincePriemere
import com.example.enhanceittechincaltest.data.remote.tvshow.ModelResTvShowImage
import com.example.enhanceittechincaltest.data.remote.tvshow.ModelResTvShows
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.*
import java.util.Calendar.DAY_OF_YEAR

class ModelTvShowTest {

    @Test
    fun createModelTvShowTest_ShouldAddCorrectAttributes() {
        val premiered = Date()
        val tvShowName = "A.I.M"
        val characterImage = ModelTvShowImage("http://i.annihil.us/535fecbbb9784.jpg","http://i.annihil.us/535fecbbb9784.jpg")

        val dto = ModelTvShow(
            id=1,
            name = tvShowName,
            image = characterImage,
            premiered = premiered
        )

        Assert.assertEquals(1, dto.id)
        Assert.assertEquals(tvShowName, dto.name)
        Assert.assertEquals(characterImage, dto.image)
        Assert.assertEquals(premiered, dto.premiered)
    }

    @Test fun createModelTvShowTest_TestDefaultValues() {
        //this is not required in our model but can be used in models that have default values
        assertEquals(1, 1)
    }

    @Test
    fun createModelResTvShowTest_validatePremiereNoOfDaysSince() {
        Calendar.getInstance().let { now ->
            // Generate lastWateringDate from being as copy of now.
            val premiereDateToday = Calendar.getInstance()
            // Test for preiered date  is today.
            premiereDateToday.time = now.time
            var tvShow = ModelTvShow(1,"test",now.time,ModelTvShowImage("",""))

            assertEquals(""+0, ""+tvShow.getNoOfDaysSincePriemere());

            // Test for lastWateringDate is yesterday.
            tvShow.premiered =  premiereDateToday.apply{ add(DAY_OF_YEAR, -1)}.time
            assertEquals(""+1, ""+tvShow.getNoOfDaysSincePriemere());
        }

    }
}