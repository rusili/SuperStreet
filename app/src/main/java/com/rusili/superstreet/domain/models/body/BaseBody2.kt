package com.rusili.superstreet.domain.models.body

abstract class BaseBody2(open val id: Int) {

    abstract fun getViewType(): Int
}