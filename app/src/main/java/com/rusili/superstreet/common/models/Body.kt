package com.rusili.superstreet.common.models

import com.rusili.superstreet.common.models.body.AbstractBodyModel
import com.rusili.superstreet.common.models.body.Image
import com.rusili.superstreet.common.models.body.ImageGroup
import com.rusili.superstreet.common.models.body.Paragraph
import java.util.Collections

data class Body(
    val paragraphs: List<Paragraph>,
    val imagesSingles: List<Image>,
    val imagesGroups: List<ImageGroup>
) {

    fun combineSections(): List<AbstractBodyModel> =
        ArrayList<AbstractBodyModel>().apply {
            addAll(paragraphs)
            addAll(imagesSingles)
            addAll(imagesGroups)
        }.also {
            Collections.sort(it)
        }
}
