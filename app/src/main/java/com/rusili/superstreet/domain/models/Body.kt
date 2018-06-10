package com.rusili.superstreet.domain.models

import com.rusili.superstreet.domain.models.body.ImageGallery
import com.rusili.superstreet.domain.models.body.ImageGroup
import com.rusili.superstreet.domain.models.body.Paragraph

data class Body(val paragraphs: List<Paragraph>,
                val imagesSingles: List<ImageGallery>,
                val imagesGroups: List<ImageGroup>) {

//    fun combineLists(): List<BaseBody> {
//        val list = emptyList<BaseBody>()
//
//        paragraphs.sortedBy { pa }
//    }
}