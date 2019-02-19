package com.rusili.superstreet.common.models.body

abstract class AbstractBodyModel(open val id: Int) : Comparable<AbstractBodyModel> {

    abstract fun getViewType(): Int

    override fun compareTo(other: AbstractBodyModel) =
            when {
                id < other.id -> -1
                id > other.id -> 1
                else -> 0
            }
}