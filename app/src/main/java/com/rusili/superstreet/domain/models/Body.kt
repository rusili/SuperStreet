package com.rusili.superstreet.domain.models

import com.rusili.superstreet.domain.models.body.AbstractBodyModel
import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.domain.models.body.Paragraph
import java.util.*
import kotlin.collections.ArrayList

data class Body(val paragraphs: List<Paragraph>,
                val imagesSingles: List<ImageGallery>,
                val imagesGroups: List<ImageGroup>) {

    fun combineLists(): MutableList<AbstractBodyModel> {
        val combinedList = ArrayList<AbstractBodyModel>()
        combinedList.addAll(paragraphs)
        combinedList.addAll(imagesSingles)
        combinedList.addAll(imagesGroups)
        Collections.sort(combinedList)
        return combinedList
    }
}