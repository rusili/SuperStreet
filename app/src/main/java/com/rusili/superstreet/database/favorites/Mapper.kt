package com.rusili.superstreet.database.favorites

interface ModelAdapter<T, R> {

    fun from(t: T): R

    fun to(r: R): T
}
